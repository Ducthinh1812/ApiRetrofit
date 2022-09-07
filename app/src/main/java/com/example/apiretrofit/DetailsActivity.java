package com.example.apiretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
    TextView textView,textView1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        textView=findViewById(R.id.textView2);
        textView1=findViewById(R.id.textView4);
        imageView=findViewById(R.id.textView3);
        Intent intent=getIntent();
        String value1 = intent.getStringExtra("id");
        getApiDetails(value1);
    }
    public void getApiDetails(String id){
        Gson gson = new GsonBuilder()
                .setLenient ()
                .create ();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/images/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiCat retrofitInterface = retrofit.create(ApiCat.class);

        Call<Detail> call = retrofitInterface.getDetails(id);
        call.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Log.d("hunghk", "onResponse: "+ response.body().toString());
                List<Detail> list = (List<Detail>) response.body();
                for(int i = 0 ;  i < list.size();  i++ ){
                    textView.setText(list.get(0).getId());
                    textView1.setText("Width: "+list.get(0).getWidth()+"    Height"+list.get(0).getHeight());
                    Picasso.get().load(list.get(0).getUrl())
                            .into(imageView);
                }
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t);
            }
        });
    }
}