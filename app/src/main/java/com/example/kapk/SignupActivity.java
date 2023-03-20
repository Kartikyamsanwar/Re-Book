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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        final EditText user_name_edittext=findViewById(R.id.signup_username1);
        final EditText email_edittext=findViewById(R.id.signup_email);
        final EditText passward_edittext=findViewById(R.id.signup_password);
        final TextView Signup_button=findViewById(R.id.signup_signup);
        final TextView gotoSignin=findViewById(R.id.signup_signin2);
        mAuth=FirebaseAuth.getInstance();
        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name_edittext.setError(null);
                email_edittext.setError(null);
                passward_edittext.setError(null);
                String name = user_name_edittext.getText().toString();
                String email = email_edittext.getText().toString();
                String password = passward_edittext.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    user_name_edittext.setError("please enter name");
                    return;

                }
                if (TextUtils.isEmpty(email)) {
                    email_edittext.setError("please enter email");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    passward_edittext.setError("please enter password");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    UserProfileChangeRequest profileUpdates=new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                                    .build();
                                    user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                           if (task.isSuccessful()){
                                                          //   Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT);
                                                            }
                                                        }
                                                    });
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                        } else {

                               Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                          }




                      }
                  });
               }
           });
        gotoSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}