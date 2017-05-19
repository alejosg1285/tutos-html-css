package com.example.cliente.udemyandroidcero.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cliente.udemyandroidcero.Models.Board;
import com.example.cliente.udemyandroidcero.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by AlejoSaaG on 28/04/2017.
 */

public class BoardAdapter extends BaseAdapter {
    private Context context;
    private List<Board> list;
    private int layout;

    public BoardAdapter(Context context, List<Board> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Board getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.title = (TextView) view.findViewById(R.id.textBoardTitle);
            vh.notes = (TextView) view.findViewById(R.id.textBoardNotes);
            vh.createdAt = (TextView) view.findViewById(R.id.textBoardDate);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        Board board = list.get(position);

        vh.title.setText(board.getTitle());

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        vh.createdAt.setText(df.format(board.getCreatedAt()));
        int numNotes = board.getNotes().size();
        String textNotes = (numNotes == 1) ? numNotes + " note" : numNotes + " notes";
        vh.notes.setText(textNotes);

        return view;
    }

    public class ViewHolder {
        TextView title;
        TextView notes;
        TextView createdAt;
    }
}
