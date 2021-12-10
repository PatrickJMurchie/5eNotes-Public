package com.example.a5ENotes;

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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link items#newInstance} factory method to
 * create an instance of this fragment.
 */
public class items extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<lst> lists;
    RecyclerView.Adapter adapter;
    public items() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Items.
     */
    // TODO: Rename and change types and number of parameters
    public static items newInstance(String param1, String param2) {
        items fragment = new items();
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
        View view =  inflater.inflate(R.layout.fragment_items, container, false);
        Button btnHomeItem = view.findViewById(R.id.btnHomeItem);
        btnHomeItem.setOnClickListener(this);

        ItemDatabase db = ItemDatabase.getDatabase(getContext());

        itemDAO ItemDao = db.itemDAO();

        lists = ItemDao.getAllTrue();

        // get the RecycylerView on the UI
        RecyclerView rv = view.findViewById(R.id.itemRecMy);

        adapter = new itemRecAdapt(getContext(), lists);

        // set the recycler view's adapter
        rv.setAdapter(adapter);
        // setup the layout manager on the recycler view
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnHomeItem)
            Navigation.findNavController(v).navigate(R.id.ItemsToMain);

    }
}