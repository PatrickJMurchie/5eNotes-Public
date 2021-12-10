package com.example.a5ENotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a5ENotes.data.ItemDatabase;
import com.example.a5ENotes.data.itemDAO;
import com.example.a5ENotes.data.lst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link itemlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemlist extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private final String TAG = "ITEMLIST";
    List<lst> lists;
    RecyclerView.Adapter adapter;
    public itemlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment itemlist.
     */
    // TODO: Rename and change types and number of parameters
    public static itemlist newInstance(String param1, String param2) {
        itemlist fragment = new itemlist();
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
        View view = inflater.inflate(R.layout.fragment_itemlist, container, false);
        Button btnHomeList = view.findViewById(R.id.btnHomeItemList);
        btnHomeList.setOnClickListener(this);

        ItemDatabase db = ItemDatabase.getDatabase(getContext());

        itemDAO ItemDao = db.itemDAO();

        lists = ItemDao.getAllTasks();

        // get the RecycylerView on the UI
        RecyclerView rv = view.findViewById(R.id.recItemList);

        // create a new Adapter for the list of items
        adapter = new lstRecAdapt(getContext(), lists);
        // set the recycler view's adapter
        rv.setAdapter(adapter);

        // setup the layout manager on the recycler view
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        downloadItems();

        return view;
    }
    private void downloadItems() {
        String url = "https://api.open5e.com/weapons/?format=json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject rObj = jsonArray.getJSONObject(i);

                                String name = rObj.getString("name");
                                String weight = rObj.getString("weight");
                                String value = rObj.getString("cost");

                                Log.d(TAG, name);
                                Log.d(TAG, weight);
                                Log.d(TAG, value);
                                ItemDatabase db = ItemDatabase.getDatabase(getContext());

                                itemDAO ItemDao = db.itemDAO();
                                //Call setters setting values to correct values
                                lst nL = new lst();
                                nL.setLstname(name);
                                nL.setLstweight(weight);
                                nL.setLstValue(value);
                                nL.setLstAdded(false);

                                ItemDao.insert(nL);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("results", error.getLocalizedMessage());
                        }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnHomeItemList) {
            Navigation.findNavController(v).navigate(R.id.ItemListToMain);
        }

    }
}