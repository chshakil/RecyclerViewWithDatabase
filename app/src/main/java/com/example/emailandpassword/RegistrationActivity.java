package com.example.emailandpassword;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailET, passwordET;
    private Button registerBtn;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();

        initializeUI();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (email.equals("")) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
        }
        if (password.equals("")) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initializeUI() {
        emailET = findViewById(R.id.emailEt);
        passwordET = findViewById(R.id.passwordEt);
        registerBtn = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);
    }
}
