package com.hussain.progosteach.retrofitandrxtestingwithdummyapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Show_Data extends AppCompatActivity {

    private static final String TAG = Show_Data.class.getSimpleName();
    private RecyclerView mrecyclerView;
    private UserInterface muserInterface;
    private UserAdapter muserAdapter;
    private CompositeDisposable mcompositeDisposable;
    private ArrayList<ModelClass> user;
    private ModelClass modelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__data);
        mcompositeDisposable = new CompositeDisposable();
        config();
        jSonParsing();
    }
    void config() {
        mrecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mrecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerView.setLayoutManager(layoutManager);
    }
    void jSonParsing() {
        muserInterface = new Retrofit.Builder()
                .baseUrl(Constant.http.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UserInterface.class);
        mcompositeDisposable.add(muserInterface.showUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }
    private void handleResponse(List<ModelClass> users) {
        user = new ArrayList<>(users);
        muserAdapter = new UserAdapter(user);
        mrecyclerView.setAdapter(muserAdapter);
        Log.d(TAG, modelClass.toString());
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(this, "Error " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        throwable.printStackTrace();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mcompositeDisposable.clear();
    }
}
