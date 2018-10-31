package com.example.phil.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.phil.myapplication.Retrofit.MyApi;
import com.example.phil.myapplication.Retrofit.RetrofitClient;
import com.example.phil.myapplication.model.Details;
import com.squareup.picasso.Picasso;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import retrofit2.Retrofit;

import java.util.List;

public class SecondeActivity extends AppCompatActivity {
    MyApi iMyApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // initialiser l'API
        Retrofit retrofit = RetrofitClient.getInstance();
        iMyApi = retrofit.create(MyApi.class);

        getVarIntent();
    }
    private void getVarIntent(){
        if(getIntent().hasExtra("image")&& getIntent().hasExtra("name")){
           // String imageUrl = getIntent().getStringExtra("image");
            String imageName = getIntent().getStringExtra("name");
            fetchData(imageName);

        }
    }
    private void setImage(String imageUrl , String imageName){
        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);
        ImageView image = findViewById(R.id.image);
        Picasso.with(this)
                .load(imageUrl)
                .resize(50,50)
                .into(image);
    }
    private void fetchData(final String name){
        compositeDisposable.add(iMyApi.searchRepos(name)
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Details>() {
                               @Override
                               public void accept(Details details) throws Exception {
                                   setImage(details.getImage(),details.text);

                               }
                           }));

    }

}
