package com.example.kapk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        final TextView Login_Button=findViewById(R.id.login_key);
        final EditText email_edittext=findViewById(R.id.email_edit_login);
        final EditText passward_edittext=findViewById(R.id.passward_edit_login);
        final TextView gotoSignup=findViewById(R.id.signup_signin);
        final TextView forgotpassword=findViewById(R.id.forgot_passward);
        mAuth=FirebaseAuth.getInstance();
         Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_edittext.setError(null);
                passward_edittext.setError(null);
                String emailText = email_edittext.getText().toString();
                String passwardText = passward_edittext.getText().toString();
                if (TextUtils.isEmpty(emailText)) {


                    email_edittext.setError("please Enter an email");
                    return;

                }

                if (TextUtils.isEmpty(passwardText)) {

                    passward_edittext.setError("please Enter a password");
                    return;

                }
            mAuth.signInWithEmailAndPassword(emailText, passwardText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                } else {

                                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                                    }
                                });
                            }
                        });
               gotoSignup.setOnClickListener(new View.OnClickListener() {
                   @Override

                    public void onClick(View v) {
                       Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                       startActivity(intent);
                   }

        });

                forgotpassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this,Reset_password.class);
                        startActivity(intent);
                    }
                });

    }

}













