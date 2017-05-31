package com.example.cliente.udemyandroidcero.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.example.cliente.udemyandroidcero.Adapters.NoteAdapter;
import com.example.cliente.udemyandroidcero.Models.Board;
import com.example.cliente.udemyandroidcero.Models.Note;
import com.example.cliente.udemyandroidcero.R;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NoteActivity extends AppCompatActivity implements RealmChangeListener<Board>
{
    private Realm realm;
    private ListView listView;
    private FloatingActionButton fab;

    private NoteAdapter adapter;
    private RealmList<Note> notes;

    private int boardId;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        realm = Realm.getDefaultInstance();

        //Recuperar el id enviado.
        if(getIntent().getExtras() != null)
        {
            boardId = getIntent().getExtras().getInt("board");
        }

        board = realm.where(Board.class).equalTo("id", boardId).findFirst();
        //Obtiene las notas del tablero.
        notes = board.getNotes();
        //Cambia el titulo de la actividad.
        this.setTitle(board.getTitle());

        //Adiciona un evento para cuando se modifica la entidad en el bd.
        board.addChangeListener(this);

        adapter = new NoteAdapter(this, notes, R.layout.list_view_note_item);
        listView = (ListView) findViewById(R.id.listNote);
        listView.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fabAddNote);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showAlertForCreatingNote("Add New Note", "Type a note for " + board.getTitle() + ".");
            }
        });

        registerForContextMenu(listView);
    }

    /* Dialogs */
    private void showAlertForCreatingNote(String title, String message)
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

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editNewNote);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String note = input.getText().toString().trim();
                if(note.length() > 0)
                {
                    createdNewNote(note);
                } else
                {
                    Toast.makeText(getApplicationContext(), "The note cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });

        //builder.create().show();
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertForEditingNote(String title, String message, final Note note)
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

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editNewNote);
        input.setText(note.getDescripcion());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String noteName = input.getText().toString().trim();
                if(noteName.length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "The description is required to edit the current note", Toast.LENGTH_LONG).show();
                } else if(noteName.equals(board.getTitle()))
                {
                    Toast.makeText(getApplicationContext(), "The description is the same that it was before", Toast.LENGTH_LONG).show();
                } else
                {
                    editNote(noteName, note);
                }
            }
        });

        //builder.create().show();
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /* Crud realm */
    private void createdNewNote(String note)
    {
        realm.beginTransaction();
        Note _note = new Note(note);
        realm.copyToRealm(_note);
        board.getNotes().add(_note);
        realm.commitTransaction();
    }

    private void editNote(String newNote, Note note)
    {
        realm.beginTransaction();
        note.setDescripcion(newNote);
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    private void deleteNote(Note note)
    {
        realm.beginTransaction();
        note.deleteFromRealm();
        realm.commitTransaction();
    }

    private void deleteAll()
    {
        realm.beginTransaction();
        board.getNotes().deleteAllFromRealm();
        realm.commitTransaction();
    }

    /*Sobrecarga de metodos*/

    @Override
    public void onChange(Board board)
    {
        adapter.notifyDataSetChanged();
    }

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
        menu.setHeaderTitle(notes.get(info.position).getDescripcion());
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
                deleteNote(notes.get(info.position));
                return true;
            case R.id.edit_board:
                showAlertForEditingNote("Edit Note", "Change the note's description", notes.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
