package com.hussain.progosteach.retrofitandrxtestingwithdummyapi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Hussain Sherwani on 15-May-17
 * hussain.ahmed@progos.org .
 */

public interface UserInterface {
    @GET("posts/")
    Observable<List<ModelClass>> showUser();
}
