package com.example.martin.github;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface followersinterface {
    @GET("{username}/followers")
    Call<ArrayList<pojoclassforfollower>> getfollowers(@Path( "username" ) String username);
}
