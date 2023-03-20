package com.example.kapk.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kapk.Book;
import com.example.kapk.ItemAdapter;
import com.example.kapk.R;
import com.example.kapk.SellActivity;

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView recyclerView =view.findViewById(R.id.recycleview_home);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        Query query= FirebaseDatabase.getInstance().getReference().child("Products").child("Books");

        PagingConfig config = new PagingConfig(10,5,false);
        DatabasePagingOptions<Book> options= new DatabasePagingOptions.Builder<Book>()
                .setLifecycleOwner(this)
                .setQuery(query,config,Book.class)
                .build();

        ItemAdapter adapter=new ItemAdapter(options, new ItemAdapter.ClickListner() {
            @Override
            public void onItemClick(int position, Book book) {
                Intent intent=new Intent(getActivity(),SellActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("Book",book);
                intent.putExtra("CALLER","product Details");
               // intent.putExtra("Book",book);
                intent.putExtras(b);
                getActivity().startActivity(intent);
                //Toast.makeText(getActivity(), "Name:"+book.getName(), Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adapter);

        final TextView sellbutton =view.findViewById(R.id.home_sell);
        sellbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SellActivity.class);
                intent.putExtra("CALLER","Sell");
                startActivity(intent);
            }


        });
      return view;

    }
}