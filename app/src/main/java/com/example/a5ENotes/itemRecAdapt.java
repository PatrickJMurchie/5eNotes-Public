package com.example.a5ENotes;

import android.content.Context;
import android.content.Intent;
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

public class itemRecAdapt extends RecyclerView.Adapter<itemRecAdapt.NoteViewHolder>{

    private static final String TAG = "ITEMREC";

    private Context context;

    private List<lst> lists;


    public itemRecAdapt(Context context, List<lst> lists){
        super();
        // initialise the member variables
        this.context = context;
        this.lists = lists;
    }


    @NonNull
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout file for the row
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.items_layout, parent, false);
        // store it in a ViewHolder
        NoteViewHolder viewHolder = new NoteViewHolder(itemView, this);
        return viewHolder;
    }


    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // get the task at position
        lst itItem = this.lists.get(position);
        View itemView = holder.itemView;

        // update the item name
        TextView itemsName = itemView.findViewById(R.id.itemsName);
        itemsName.setText(itItem.getLstname());

        //Update Item weight
        TextView itemsWeight = itemView.findViewById(R.id.itemsWeights);
        itemsWeight.setText(itItem.getLstweight());

        //Update Item weight
        TextView itemsValue = itemView.findViewById(R.id.itemsValue);
        itemsValue.setText(itItem.getLstValue());

        ImageButton removeItem = itemView.findViewById(R.id.removeItem);

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.removeItem){
                    ItemDatabase db = ItemDatabase.getDatabase(v.getContext());
                    itemDAO id = db.itemDAO();
                    int UID = itItem.getUID();
                    Log.d(TAG,String.valueOf(UID));
                    id.UpdateAdded(false, UID);
                    Toast.makeText(context.getApplicationContext(), itItem.getLstname() + " was removed", Toast.LENGTH_LONG).show();
                    Intent refresh = new Intent(v.getContext(), MainActivity.class);
                    context.startActivity(refresh);
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
        class NoteViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private itemRecAdapt adapter;


        public NoteViewHolder(@NonNull View itemView, itemRecAdapt adapter) {
            super(itemView);
            this.itemView = itemView;
            this.adapter = adapter;

        }

    }

    }

