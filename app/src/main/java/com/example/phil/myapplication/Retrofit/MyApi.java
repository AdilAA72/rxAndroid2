package com.example.phil.myapplication.Retrofit;

import com.example.phil.myapplication.model.Model;
import com.example.phil.myapplication.model.Details;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface MyApi {

    @GET("json.php?")
    Observable<Details> searchRepos(@Query("name") String name);
    @GET("json.php")
    Observable<List<Model>> getModelList();

}
