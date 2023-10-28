package com.example.onlinevotingapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinevotingapp.MainActivity;
import com.example.onlinevotingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail,signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;
    private ProgressBar progressBar;


//    @Override
//    public void onStart(){
//        super.onStart();
////        check if user is signed in (non-null) and the update UI accordingly.
//        FirebaseUser currentUser=auth.getCurrentUser();
//        if(currentUser!=null){
//            if(currentUser.isEmailVerified()) {
//                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
//                finish();
//            }else{
////                showEmailVerificationPrompt();
//
//            }
//        }else{
////            Intent intent=new Intent(this,LoginActivity.class);
////            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth=FirebaseAuth.getInstance();
        signupEmail=findViewById(R.id.user_Email);
        signupPassword=findViewById(R.id.user_Password);
        signupButton=findViewById(R.id.signup_btn);
        loginRedirectText=findViewById((R.id.loginredirectText));
        progressBar=findViewById(R.id.progressBar);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String user=signupEmail.getText().toString().trim();
                String pass=signupPassword.getText().toString().trim();

                if(user.isEmpty()){
                    signupEmail.setError("Email cannot be empty");
                }
                if(pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty");
                }else{
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SignUpActivity.this, "Verify you Email ID", Toast.LENGTH_SHORT).show();
                                            if(auth.getCurrentUser().isEmailVerified()){
                                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                            }else{
                                                Toast.makeText(SignUpActivity.this, "Please Verify:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(SignUpActivity.this, "SignUp Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }else{
                                Toast.makeText(SignUpActivity.this, "SignUp Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}