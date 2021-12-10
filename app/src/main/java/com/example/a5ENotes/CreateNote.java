package com.example.a5ENotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a5ENotes.data.ItemDatabase;
import com.example.a5ENotes.data.itemDAO;
import com.example.a5ENotes.data.myDAO;
import com.example.a5ENotes.data.nt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNote extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateNote() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateNote.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNote newInstance(String param1, String param2) {
        CreateNote fragment = new CreateNote();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
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
        View view =  inflater.inflate(R.layout.fragment_create_note, container, false);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        Button btnCreate = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCancel){
            Navigation.findNavController(v).navigate(R.id.createToNotes);
        }
        if (v.getId() == R.id.btnCreate){
            String noteName = String.valueOf(((EditText)getView().findViewById(R.id.txtSetName)).getText());
            String noteDesc = String.valueOf(((EditText)getView().findViewById(R.id.txtSetDesc)).getText());

            ItemDatabase db = ItemDatabase.getDatabase(getContext());

            myDAO myDAO = db.myDAO();

            nt note = new nt();
            note.setName(noteName);
            note.setDisc(noteDesc);
            note.setCurrent(false);

            myDAO.insert(note);



            Navigation.findNavController(v).navigate(R.id.createToNotes);
        }

    }
}