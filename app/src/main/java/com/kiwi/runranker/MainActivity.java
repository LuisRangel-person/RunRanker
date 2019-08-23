package com.kiwi.runranker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //UI Elements
    RecyclerView rv_raceResults;//The results list
    TextView tv_raceTitle;//Name of the race
    TextView tv_racerNumber;//Number of racers



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set up UI
        rv_raceResults = findViewById(R.id.resultsList);
        tv_raceTitle = findViewById(R.id.raceTitle);
        tv_racerNumber = findViewById(R.id.racerNumber);
        rv_raceResults.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        RunnerAPIInterface runnerService = retrofit.create(RunnerAPIInterface.class);
        Call<RaceInfo> call = runnerService.getRunners();
        call.enqueue(new Callback<RaceInfo>() {
            @Override
            public void onResponse(Call<RaceInfo> call, Response<RaceInfo> response) {
               RaceInfo list = response.body();
               List<Runner> runners = list.getRunners();
               setTitle(list.getRaceName());
//               tv_raceTitle.setText(list.getRaceName());
//               tv_racerNumber.setText(list.getNumberofRacers());
               RacerListAdapter adapter = new RacerListAdapter(MainActivity.this, runners);
                rv_raceResults.setAdapter(adapter);
                int stop  = 0;
            }

            @Override
            public void onFailure(Call<RaceInfo> call, Throwable t) {
                int fail = 0;
            }
        });
    }
}
