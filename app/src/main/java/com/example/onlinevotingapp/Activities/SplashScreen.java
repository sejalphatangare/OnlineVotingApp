package com.example.onlinevotingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.onlinevotingapp.MainActivity;
import com.example.onlinevotingapp.R;
import com.example.onlinevotingapp.Utils.FirebaseUtil;

public class SplashScreen extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottie=findViewById(R.id.lottie);


//        lottie.animate().translationX(3000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(FirebaseUtil.isLoggedIn()){
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                }else{
                    Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(i);

                }
                finish();
            }
        },3000);
    }
}