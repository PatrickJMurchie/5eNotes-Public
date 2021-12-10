package com.example.a5ENotes;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.a5ENotes.data.ItemDatabase;
import com.example.a5ENotes.data.itemDAO;
import com.example.a5ENotes.data.lst;
import com.example.a5ENotes.data.myDAO;
import com.example.a5ENotes.data.nt;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link notes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notes extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public notes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notes.
     */
    // TODO: Rename and change types and number of parameters
    public static notes newInstance(String param1, String param2) {
        notes fragment = new notes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        Button btnHome = view.findViewById(R.id.btnHomeNote);
        Button btnNN = view.findViewById(R.id.btnNN);
        btnHome.setOnClickListener(this);
        btnNN.setOnClickListener(this);

        ItemDatabase db = ItemDatabase.getDatabase(getContext());

        myDAO NoteDao = db.myDAO();

        List<nt> nts = NoteDao.getAllNotes();

        // get the RecycylerView on the UI
        RecyclerView rv = view.findViewById(R.id.noteRecView);

        // create a new Adapter for the notes
        RecyclerView.Adapter adapter = new noteRecAdapt(getContext(), nts);

        // setup the layout manager on the recycler view
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // set the recycler view's adapter
        rv.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnHomeNote){
            Navigation.findNavController(v).navigate(R.id.NotesToMain);
        }

        if (v.getId() == R.id.btnNN){
            Navigation.findNavController(v).navigate(R.id.notesToCreate);


        }


    }
}
