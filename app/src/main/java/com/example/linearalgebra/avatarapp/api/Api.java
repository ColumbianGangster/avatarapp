package com.example.linearalgebra.avatarapp.api;

import com.example.linearalgebra.avatarapp.model.Model;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public interface Api {
    @GET("/Joe886/testfiles/master/people.json")
    Observable<Model> fetchPeople();
}
