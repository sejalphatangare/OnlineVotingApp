package com.example.onlinevotingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.onlinevotingapp.R;

public class SplashScreen extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottie=findViewById(R.id.lottie);


        lottie.animate().translationX(3000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(() -> {
            Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(i);
            finish();
        },5000);
    }
}