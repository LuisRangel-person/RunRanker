package com.kiwi.runranker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RunnerAPIInterface {

    //Request the runner data from online
    @GET("mobile/runners.json")
    Call<RaceInfo> getRunners();
}
