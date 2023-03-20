package com.example.kapk.fragments;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kapk.Book;
import com.example.kapk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private Book book;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductDetails() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProductDetails newInstance(Book book) {
        ProductDetails fragment = new ProductDetails();
        Bundle args = new Bundle();
        args.putSerializable("Book",book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          //  mParam1 = getArguments().getString(ARG_PARAM1);
            book = (Book) getArguments().getSerializable("Book");
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_product_details, container, false);
        final TextView name=view.findViewById(R.id.pd_name);
        name.setText(book.getName());
        final TextView sellprice=view.findViewById(R.id.pd_sellprice);
        final TextView mrp=view.findViewById(R.id.pd_MRP);
        final TextView discount=view.findViewById(R.id.pd_discount);
        sellprice.setText("₹"+book.getSell_price());
        mrp.setText("₹"+book.getMrp());
       // int dicount=(Integer.parseInt(book.getMrp()-Integer.parseInt(book.getSell_price())))/Integer.parseInt(book.getMrp())


        int mrpInt=Integer.parseInt(book.getMrp());
        int sellInt =Integer.parseInt(book.getSell_price());
        int difference =mrpInt-sellInt;
        int discountInt =(difference*100)/mrpInt;
        discount.setText(discountInt+"%");
        /*final TextView sellPrice = view.findViewById(R.id.pd_sellprice);
        final TextView mrp = view.findViewById(R.id.pd_MRP);
        final TextView discount = view.findViewById(R.id.pd_discount);
        sellPrice.setText("₹"+book.getSell_price());
        mrp.setText("₹"+book.getMrp());
        int mrpInt = Integer.parseInt(book.getMrp());
        int sellInt = Integer.parseInt(book.getSell_price());
        int diffrence = mrpInt-sellInt;
        int discountInt = (diffrence*100)/mrpInt;
        discount.setText(discountInt+"%"); */

        final TextView replace =view.findViewById(R.id.pd_return);
        final TextView cod =view.findViewById(R.id.pd_cod);
        String replaceString=book.isReturn_available()?
                "Replace in 7 days":"Non Returnable";
        replace.setText(replaceString);
        String codString =book.isCod()?"COD Available":"COD not Available";
        cod.setText(codString);

        return view;
    }
}