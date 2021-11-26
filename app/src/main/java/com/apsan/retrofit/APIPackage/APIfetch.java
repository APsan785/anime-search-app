package com.apsan.retrofit.APIPackage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIfetch {

    @GET("search/{searchGroups}")
    Call<ResultFromAPI> getResult(
        @Path("searchGroups") String search_group,
        @Query("q") String search_query
    );

}
