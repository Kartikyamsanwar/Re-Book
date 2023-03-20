package com.example.kapk;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressbar = findViewById(R.id.welcome_progressbar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final TextView name = findViewById(R.id.Welcome_name);
        if (user != null) {
            progressbar.setVisibility(View.GONE);
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);

            name.setText("Welcome back- " + user.getDisplayName());
        } else {
            progressbar.setVisibility(View.GONE);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);


            getSupportActionBar().hide();



        }

    }

}


