package com.example.martin.github;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface usersearchinterface {
    @GET("users/{userid}")
    Call<userinformationclass> getresult(@Path( "userid" ) String name);
}
