package com.example.onlinevotingapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.onlinevotingapp.MainActivity;
import com.example.onlinevotingapp.Model.User;
import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.Utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Objects;

public class RegisterUserProfile extends AppCompatActivity {

    private EditText fullname,Email,pwd,dob,mobile;
    private ProgressBar progressBar;
    private RadioGroup radioGroupGender,radioGroupRole;
    private RadioButton radioButtonGender,radioButtonRole;
    private Button Submitbtn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_profile);

        fullname=findViewById(R.id.editText_register_full_name);
        Email=findViewById(R.id.editText_register_Email);
        pwd=findViewById(R.id.editText_register_Password);
        dob=findViewById(R.id.editText_register_dob);
        mobile=findViewById(R.id.editText_register_mobile);
        Submitbtn=findViewById(R.id.button_Submit);
        radioGroupGender=findViewById(R.id.radio_group_register_gender);
        radioGroupGender.clearCheck();
        radioGroupRole=findViewById(R.id.radio_group_register_Role);
        radioGroupRole.clearCheck();
        progressBar=findViewById(R.id.progressBar);
        getUserEmail();
        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserEmail_password();
            }
        });



    }

    void setUserEmail_password(){
        setInProgress(true);
        if(user!=null){
            Email.setText(user.getEmail());
            pwd.setText(user.getPassword());
            user.setEmail(Email.getText().toString());
            user.setPassword(pwd.getText().toString());
        }else{
            int selectedGengerId=radioGroupGender.getCheckedRadioButtonId();
            radioButtonGender=findViewById(selectedGengerId);
            int selectedRoleId=radioGroupRole.getCheckedRadioButtonId();
            radioButtonRole=findViewById(selectedRoleId);
            user=new User(fullname.getText().toString(),Email.getText().toString(),pwd.getText().toString(),dob.getText().toString(),radioButtonGender.getText().toString(),radioButtonRole.getText().toString(),mobile.getText().toString(), Timestamp.now());

        }
        FirebaseUtil.currentUserDetails().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    Intent intent=new Intent(RegisterUserProfile.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

    }
    void getUserEmail(){
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    user=task.getResult().toObject(User.class);
                    if(user!=null){
                      Email.setText(user.getEmail());
                      pwd.setText(user.getPassword());
                    }
                }

            }
        });
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