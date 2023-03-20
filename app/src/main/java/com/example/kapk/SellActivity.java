package com.example.kapk;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.kapk.fragments.ProductDetails;
import com.example.kapk.fragments.SellFragment;
public class SellActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Book book=(Book) getIntent().getExtras().getSerializable("Book");


                //new SellFragment()).commit();
        String caller=getIntent().getStringExtra("CALLER");
        if(caller.matches("Sell")){
            getSupportFragmentManager().beginTransaction().add(R.id.sellplace_holder,new SellFragment()).commit();

        }else{
            ProductDetails fragment=ProductDetails.newInstance(book);
            getSupportFragmentManager().beginTransaction().add(R.id.sellplace_holder,fragment).commit();
        }

    }
}