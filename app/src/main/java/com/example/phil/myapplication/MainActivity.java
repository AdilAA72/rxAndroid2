package com.example.phil.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import android.widget.Toast;
import com.example.phil.myapplication.Retrofit.MyApi;
import com.example.phil.myapplication.Retrofit.RetrofitClient;
import com.example.phil.myapplication.adapter.RecycleViewAdapter;
import com.example.phil.myapplication.model.Model;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerPosts;
    MyApi iMyApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialiser l'API
        Retrofit retrofit = RetrofitClient.getInstance();
        iMyApi = retrofit.create(MyApi.class);
        //View
        recyclerPosts = (RecyclerView) findViewById(R.id.recycler_posts);
        recyclerPosts.setHasFixedSize(true);
        recyclerPosts.setLayoutManager(new LinearLayoutManager(this));
        checkConnection();


    }

    private void fetchData() {
        compositeDisposable.add(iMyApi.getModelList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Model>>() {
                    @Override
                    public void accept(List<Model> models) throws Exception {

                        displayData(models);

                    }
                }));

    }

    private void displayData(List<Model> models) {
        RecycleViewAdapter postAdapter = new RecycleViewAdapter(this, models);
        recyclerPosts.setAdapter(postAdapter);
    }

    @Override
    protected  void onStop(){
        compositeDisposable.clear();
        super.onStop();
    }

    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkConnection(){
        if(isOnline()){
            Toast.makeText(MainActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
            fetchData();

        }else{
            Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();

        }

    }
}
