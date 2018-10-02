package com.himanshu.martin.github;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface reposearchinterface {
    @GET("https://api.github.com/users/{username}/{repos}")
    Call<ArrayList<pojoclassforrepo>> getreporesult(@Path( "username" ) String username, @Path( "repos" ) String repos);
}
