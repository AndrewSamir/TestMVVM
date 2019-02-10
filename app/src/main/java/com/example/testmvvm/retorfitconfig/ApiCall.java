package com.example.testmvvm.retorfitconfig;


import com.example.testmvvm.data.ModelCommenResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiCall
{

    @GET("categories")
    Observable<ModelCommenResponse> callcategories();

    @GET("cities")
    Observable<ModelCommenResponse> callCities();

}

