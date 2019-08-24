package com.kiwi.runranker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //UI Elements
    private RecyclerView rv_raceResults;//The results list
    //Error Display, this will be populated by a drawable depending on what goes wrong
    private ImageView iv_imageDisplay;
    private ListCache cache;
    private Button bu_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set up UI
        rv_raceResults = findViewById(R.id.resultsList);
        rv_raceResults.setLayoutManager(new LinearLayoutManager(this));
        iv_imageDisplay = findViewById(R.id.error_display);
        bu_retry = findViewById(R.id.button_retry);
        //Check if a list already exists to avoid call, for rotation
        cache = ListCache.getInstance();
        if(cache.getList() == null) {//If there is no list in the cache, make the call
            makeNetworkCall();
        }//If there was no list in the cache
        else{//Avoids making another call just because of rotation
            setTitle(cache.getList().getRaceName());
            setUpListUI(cache.getList().getRunners());
        }
    }//On Create

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }//Create the Menu

    public boolean onOptionsItemSelected(MenuItem item){//This handles the pressing of the back button
        if(item.getItemId() == R.id.action_settings){
            new AboutDialog(this);
        }
        else {
            finish();
        }
        return true;
    }//Make the Menu Clickable

    private boolean isConnected(){//This checks if a phone is connected to the internet
        //Check if there is internet, can't load nothin' if there isn't any internet
        ConnectivityManager connectManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        return networkInfo.isConnectedOrConnecting();
    }//Is The Internet Connected

    private void setUpListUI(List<Runner> list){
        if (list.size() > 0) {// Only set the adapter if there are any racers
            RacerListAdapter adapter = new RacerListAdapter(list);
            rv_raceResults.setAdapter(adapter);
            iv_imageDisplay.setVisibility(View.GONE);
        } else {//Empty racer list, show the no results display
            iv_imageDisplay.setVisibility(View.VISIBLE);
            iv_imageDisplay.setImageDrawable(getResources().getDrawable(R.drawable.error_no_results));
        }
    }

    private void makeNetworkCall(){
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        RunnerAPIInterface runnerService = retrofit.create(RunnerAPIInterface.class);
        if (isConnected()) {//Check if there is an internet connection, can't guarantee service
            Call<RaceInfo> call = runnerService.getRunners();
            call.enqueue(new Callback<RaceInfo>() {
                @Override
                public void onResponse(Call<RaceInfo> call, Response<RaceInfo> response) {
                    RaceInfo list = response.body();
                    List<Runner> runners = list.getRunners();
                    setTitle(list.getRaceName());
                    cache.setList(list);//Cache the list for later
                    setUpListUI(runners);
                }
                @Override
                public void onFailure(Call<RaceInfo> call, Throwable t) {//Server Error, show dialog with more details
                    iv_imageDisplay.setVisibility(View.VISIBLE);
                    iv_imageDisplay.setImageDrawable(getResources().getDrawable(R.drawable.error_server_error));
                    new CustomDialog(MainActivity.this, "Error", t.toString(), true, false);
                }
            });
        }//If connected to internet
        else {//Display the No Internet image
            iv_imageDisplay.setVisibility(View.VISIBLE);
            iv_imageDisplay.setImageDrawable(getResources().getDrawable(R.drawable.error_no_internet));
            bu_retry.setVisibility(View.VISIBLE);//Connect to internet and try again
        }
    }

    public void retryNetworkCall(View target){//Retry the call when it fails
        bu_retry.setVisibility(View.GONE);
        iv_imageDisplay.setImageDrawable(this.getResources().getDrawable(R.drawable.display_loading));
        iv_imageDisplay.setVisibility(View.VISIBLE);
        makeNetworkCall();
    }
}
