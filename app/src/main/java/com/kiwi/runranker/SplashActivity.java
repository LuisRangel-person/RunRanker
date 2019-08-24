package com.kiwi.runranker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler mDelayHandler = null;
    private Long splashDelay = 3000L;
    private String shownSplash = "shownSplashScreen";
    //This is used so the splash screen is open longer on the first launch, and then its regular time every other launch
    private SharedPreferences prefs = null;

    private Runnable mRunnable = new Runnable(){
        @Override
        public void run() {
            if(!isFinishing()){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean(shownSplash, true);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = getSharedPreferences("com.kiwi.runranker.prefs", Context.MODE_PRIVATE);
        boolean shownAlready = prefs.getBoolean(shownSplash, false);
        if(shownAlready){
            splashDelay = 500L;
        }
        mDelayHandler = new Handler();
        //Navigate with delay
        mDelayHandler.postDelayed(mRunnable, splashDelay);
    }

    @Override
    protected void onDestroy(){
        if(mDelayHandler != null){
            mDelayHandler.removeCallbacks(mRunnable);
        }
        super.onDestroy();
    }
}
