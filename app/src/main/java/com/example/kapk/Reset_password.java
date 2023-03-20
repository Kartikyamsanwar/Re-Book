package com.example.kapk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_password extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        final EditText email=findViewById(R.id.email_resetPass_email);
        final TextView sendemail=findViewById(R.id.rsetpass_sendEmail);
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setError(null);
                String emailText=email.getText().toString();
                if(TextUtils.isEmpty(emailText)){
                    email.setError("Please Enter a n Email");
                    return;
                }else{
                    FirebaseAuth mAuth =FirebaseAuth.getInstance();
                    mAuth.sendPasswordResetEmail(emailText)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Reset_password.this, "password reset email sent successfully", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Reset_password.this, "something went wrong!Try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }

        });
    }
    }