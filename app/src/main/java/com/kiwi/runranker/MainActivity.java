package com.kiwi.runranker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //UI Elements
    TextView tv_raceTitle;
    TextView tv_racerNumber;//Number of racers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set up UI
        tv_raceTitle = findViewById(R.id.raceTitle);
        tv_racerNumber = findViewById(R.id.racerNumber);

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        RunnerAPIInterface runnerService = retrofit.create(RunnerAPIInterface.class);
        Call<RaceInfo> call = runnerService.getRunners();
        call.enqueue(new Callback<RaceInfo>() {
            @Override
            public void onResponse(Call<RaceInfo> call, Response<RaceInfo> response) {
               RaceInfo list = response.body();
               List<Runner> runners = list.getRunners();
               tv_raceTitle.setText(list.getRaceName());
               tv_racerNumber.setText(list.getNumberofRacers());
                int stop  = 0;
            }

            @Override
            public void onFailure(Call<RaceInfo> call, Throwable t) {
                int fail = 0;
            }
        });
    }
}
