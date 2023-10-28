package com.example.onlinevotingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.onlinevotingapp.R;

import java.util.Objects;

public class RegisterUserProfile extends AppCompatActivity {

    private EditText fullname,email,password,dob,mobile;
    private ProgressBar progressBar;
    private RadioGroup radioGroupGender,radioGroupRole;
    private RadioButton radioButtonGender,radioButtonRole;
    private Button Submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_profile);

        fullname=findViewById(R.id.editText_register_full_name);
        email=findViewById(R.id.editText_register_Email);
        password=findViewById(R.id.editText_register_Password);
        dob=findViewById(R.id.editText_register_dob);
        mobile=findViewById(R.id.editText_register_mobile);
        Submitbtn=findViewById(R.id.button_Submit);

    }
    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            Submitbtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            Submitbtn.setVisibility(View.VISIBLE);
        }
    }
}