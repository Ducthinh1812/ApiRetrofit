package com.example.apiretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Cat> arrayList;
    AdapterCat adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rclview);
        arrayList = new ArrayList<>();
        adapters = new AdapterCat(this, arrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(adapters);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/images/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiCat retrofitInterface = retrofit.create(ApiCat.class);
        Call<List<Cat>> listCall = retrofitInterface.getListCat("10", "beng", "REPLACE_ME");
        listCall.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                List<Cat> list1 = response.body();
                Log.e("dddddddddd", "onResponse: " + response.body());
                for (int i = 0; i < list1.size(); i++) {
                    Log.d("TAG", "onResponse: " + list1.get(i).getId());
                    arrayList.add(list1.get(i));
                }
                adapters.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {

            }
        });
    }
}