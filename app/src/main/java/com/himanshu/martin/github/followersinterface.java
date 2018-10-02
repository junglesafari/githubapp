package com.himanshu.martin.github;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface  followersinterface {
    @GET
    Call<ArrayList<pojoclassforfollower>> getfollowers(@Url String url);
}
