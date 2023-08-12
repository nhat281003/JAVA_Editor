package com.example.test_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.test_exam.adapter.BlinkAdapter;
import com.example.test_exam.api.ConnectApi;
import com.example.test_exam.api.LoadApi;
import com.example.test_exam.model.BlinkData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoadApi {
    RecyclerView recyclerView;
    List<BlinkData> blinkDataList = new ArrayList<>();
    BlinkAdapter blinkAdapter;
    public  static  ProgressBar progressBar;
    LinearLayout linearLayout;
    Button add;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blinkAdapter = new BlinkAdapter(blinkDataList);
        blinkAdapter.LoadApi(MainActivity.this::Reload);
        recyclerView= findViewById(R.id.recyview);
        progressBar= findViewById(R.id.progess);
        add = findViewById(R.id.add);
        linearLayout= findViewById(R.id.linerpro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
        //Tôi thích ăn cứt -------------------------
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMember.class);
                startActivity(intent);
            }
        });

    }

    public void getData(){

        ConnectApi.apiservice.getdata().enqueue(new Callback<List<BlinkData>>() {
            @Override
            public void onResponse(Call<List<BlinkData>> call, Response<List<BlinkData>> response) {
                blinkDataList= response.body();
                blinkAdapter = new BlinkAdapter(blinkDataList);
                blinkAdapter.setmData(blinkDataList);
                blinkAdapter.LoadApi(MainActivity.this::Reload);//thêm cái này------------- phải thêm trong đây mới chạy được
                recyclerView.setAdapter(blinkAdapter);
                blinkAdapter.notifyDataSetChanged();
                linearLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call<List<BlinkData>> call, Throwable t) {

            }
        });
    }

    @Override
    public void Reload() {
        getData();
    }

    @Override
    protected void onResume() {
        getData();
        blinkAdapter.setmData(blinkDataList);
        super.onResume();
    }
}