package com.kiwi.runranker;

import retrofit2.Call;
import retrofit2.http.GET;

interface RunnerAPIInterface {

    //Request the runner data from online
    @GET("mobile/runners.json")
    Call<RaceInfo> getRunners();
}
