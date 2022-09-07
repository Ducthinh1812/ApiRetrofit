package com.example.apiretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCat {
    //https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng&api_key=REPLACE_ME
    @GET("search")
    Call<List<Cat>> getListCat(@Query("limit") String limit ,
                               @Query("breed_ids") String breed_ids,
                               @Query("api_key") String api_key);
    @GET("{position}")
    Call<Detail> getDetails(@Path(value = "position") String position );
}
