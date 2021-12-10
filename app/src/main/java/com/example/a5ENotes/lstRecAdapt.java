package com.example.a5ENotes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5ENotes.data.ItemDatabase;
import com.example.a5ENotes.data.itemDAO;
import com.example.a5ENotes.data.lst;

import java.util.List;

public class lstRecAdapt extends RecyclerView.Adapter<lstRecAdapt.NoteViewHolder> {

    private final String TAG = "lstAdapt";

    private Context context;

    private List<lst> lists;


    public lstRecAdapt(Context context, List<lst> lists){
        super();
        // initialise the member variables
        this.context = context;
        this.lists = lists;
    }


    @NonNull
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout file for the row
        View listView = LayoutInflater.from(this.context).inflate(R.layout.lst_layout, parent, false);
        // store it in a ViewHolder
        NoteViewHolder viewHolder = new NoteViewHolder(listView, this);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // get the task at position
        lst lstItem = this.lists.get(position);
        View listView = holder.listView;

        // update the item name
        TextView itemsName = listView.findViewById(R.id.lstName);
        itemsName.setText(lstItem.getLstname());

        //Update Item weight
        TextView itemsWeight = listView.findViewById(R.id.lstWeights);
        itemsWeight.setText(lstItem.getLstweight());

        //Update Item weight
        TextView itemsValue = listView.findViewById(R.id.lstValue);
        itemsValue.setText(lstItem.getLstValue());

        ImageButton addItem = listView.findViewById(R.id.addItem);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.addItem){
                    ItemDatabase db = ItemDatabase.getDatabase(v.getContext());
                    itemDAO id = db.itemDAO();
                    int UID = lstItem.getUID();
                    Log.d(TAG,String.valueOf(UID));
                    id.UpdateAdded(true, UID);
                    Toast.makeText(context.getApplicationContext(), lstItem.getLstname() + " was saved", Toast.LENGTH_LONG).show();

                }}
        });

    }
    @Override
    public int getItemCount() {
        return this.lists.size();
    }

    /**
         * ViewHolder for the View that's going to display Tasks
         */
        class NoteViewHolder extends RecyclerView.ViewHolder{

            private View listView;
            private lstRecAdapt adapter;

            public NoteViewHolder(@NonNull View listView, lstRecAdapt adapter) {
                super(listView);
                this.listView = listView;
                this.adapter = adapter;

            }

        }
    }

