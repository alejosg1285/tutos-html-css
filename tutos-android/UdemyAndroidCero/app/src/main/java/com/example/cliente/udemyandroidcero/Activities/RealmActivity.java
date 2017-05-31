package com.example.cliente.udemyandroidcero.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cliente.udemyandroidcero.Adapters.BoardAdapter;
import com.example.cliente.udemyandroidcero.Adapters.RecyclerAdapter;
import com.example.cliente.udemyandroidcero.Models.Board;
import com.example.cliente.udemyandroidcero.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>>, AdapterView.OnItemClickListener
{
    private Realm realm;
    private FloatingActionButton fab;
    private ListView listView;
    private BoardAdapter boardAdapter;
    private RealmResults<Board> boards;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        realm = Realm.getDefaultInstance();

        //Obtiene los datos de la base de datos.
        boards = realm.where(Board.class).findAll();
        //Adiciona un evento para cuando se modifica la entidad en el bd.
        boards.addChangeListener(this);

        boardAdapter = new BoardAdapter(this, boards, R.layout.list_view_board_item);
        listView = (ListView) findViewById(R.id.listBoard);
        listView.setAdapter(boardAdapter);
        listView.setOnItemClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showAlertForCreatingBoard("Add New Board", "Type a name for your new board");
            }
        });

        registerForContextMenu(listView);
    }

    /* Crud realm */
    private void createdNewBoard(String boardName)
    {
        realm.beginTransaction();
        Board board = new Board(boardName);
        realm.copyToRealm(board);
        realm.commitTransaction();

        //Alternativa para realizar transaccion en la bd.
        //La cadena que se envia debe ser final.
        /*realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Board board = new Board(boardName);
                realm.copyToRealm(board);
            }
        });*/
    }

    private void updateBoard(String newName, Board board)
    {
        realm.beginTransaction();
        board.setTitle(newName);
        realm.copyToRealmOrUpdate(board);
        realm.commitTransaction();
    }

    private void deleteBoard(Board board)
    {
        realm.beginTransaction();
        board.deleteFromRealm();
        realm.commitTransaction();
    }

    private void deleteAll()
    {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    /* Dialogs. */
    private void showAlertForCreatingBoard(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title != null)
        {
            builder.setTitle(title);
        }
        if(message != null)
        {
            builder.setMessage(message);
        }

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editNewBoard);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String boardName = input.getText().toString().trim();
                if(boardName.length() > 0)
                {
                    createdNewBoard(boardName);
                } else
                {
                    Toast.makeText(getApplicationContext(), "The title is required to create a new board", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //builder.create().show();
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertForEditingBoard(String title, String message, final Board board)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title != null)
        {
            builder.setTitle(title);
        }
        if(message != null)
        {
            builder.setMessage(message);
        }

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editNewBoard);
        input.setText(board.getTitle());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String boardName = input.getText().toString().trim();
                if(boardName.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "The name is required to edit the current board", Toast.LENGTH_SHORT).show();
                } else if(boardName.equals(board.getTitle()))
                {
                    Toast.makeText(getApplicationContext(), "The name is the same that it was before", Toast.LENGTH_SHORT).show();
                } else
                {
                    updateBoard(boardName, board);
                }
            }
        });

        //builder.create().show();
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*Sobrecarga de metodos*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_board_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.delete_all:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(boards.get(info.position).getTitle());
        getMenuInflater().inflate(R.menu.context_menu_board_activity, menu);
        //super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId())
        {
            case R.id.delete_board:
                deleteBoard(boards.get(info.position));
                return true;
            case R.id.edit_board:
                showAlertForEditingBoard("Edit Board", "Change the board's name", boards.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onChange(RealmResults<Board> boards)
    {
        boardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        Intent intent = new Intent(RealmActivity.this, NoteActivity.class);
        intent.putExtra("board", boards.get(position).getId());
        startActivity(intent);
    }
}
