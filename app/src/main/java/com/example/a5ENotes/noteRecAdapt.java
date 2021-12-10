package com.example.a5ENotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5ENotes.data.ItemDatabase;
import com.example.a5ENotes.data.myDAO;
import com.example.a5ENotes.data.nt;

import java.util.List;

public class noteRecAdapt extends RecyclerView.Adapter<noteRecAdapt.NoteViewHolder> {

    private Context context;

    private List<nt> nts;
    private final String TAG = "noteAdapt";

    public noteRecAdapt(Context context, List<nt> nts){
        super();
        // initialise the member variables
        this.context = context;
        this.nts = nts;
    }


    @NonNull
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout file for the row
        View noteView = LayoutInflater.from(this.context).inflate(R.layout.notes_item_layout, parent, false);
        // store it in a ViewHolder
        NoteViewHolder viewHolder = new NoteViewHolder(noteView, this);
        return viewHolder;
    }

    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // get the task at position
        nt ntItem = this.nts.get(position);
        View noteView = holder.noteView;

        // update the note name
        TextView txtNoteName = holder.noteView.findViewById(R.id.txtNoteName);
        txtNoteName.setText(ntItem.getName());

        // update the note name
        TextView txtDisc = holder.noteView.findViewById(R.id.noteDisc);
        txtDisc.setText(ntItem.getDisc());

        Button btnNoteExp = noteView.findViewById(R.id.btnNoteExp);

        btnNoteExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnNoteExp){
                    String noteName = String.valueOf(((EditText)holder.noteView.findViewById(R.id.txtNoteName)).getText());
                    String noteDesc = String.valueOf(((EditText)holder.noteView.findViewById(R.id.noteDisc)).getText());

                    ItemDatabase db = ItemDatabase.getDatabase(v.getContext());
                    myDAO id = db.myDAO();
                    int UID = ntItem.getUID();
                    Log.d(TAG,String.valueOf(UID));
                    Log.d(TAG,noteName);
                    Log.d(TAG,noteDesc);
                    id.UpdateName(noteName,UID);
                    id.UpdateDisc(noteDesc,UID);
                    Toast.makeText(context.getApplicationContext(), noteName + " was updated", Toast.LENGTH_LONG).show();
                    Intent refresh = new Intent(v.getContext(), MainActivity.class);
                    context.startActivity(refresh);
                }
            }

        });

        Button btnNoteDelete = noteView.findViewById(R.id.btnNoteDelete);
        btnNoteDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ItemDatabase db = ItemDatabase.getDatabase(v.getContext());
                myDAO id = db.myDAO();
                int UID = ntItem.getUID();
                String name = ntItem.getName();
                id.DeleteNote(UID);
                Toast.makeText(context.getApplicationContext(), name + " was deleted", Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(v.getContext(), MainActivity.class);
                context.startActivity(refresh);
            }

        });
    }
    @Override
    public int getItemCount() {
        return this.nts.size();
    }

    /**
         * ViewHolder for the View that's going to display Tasks
         */
        class NoteViewHolder extends RecyclerView.ViewHolder{

            private View noteView;
            private noteRecAdapt adapter;

            public NoteViewHolder(@NonNull View noteView, noteRecAdapt adapter) {
                super(noteView);
                this.noteView = noteView;
                this.adapter = adapter;

            }
        }
    }

