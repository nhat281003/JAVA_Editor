package com.example.test_exam;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.test_exam.adapter.BlinkAdapter;
import com.example.test_exam.api.ConnectApi;
import com.example.test_exam.api.LoadApi;
import com.example.test_exam.model.BlinkData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMember extends AppCompatActivity {
    EditText edtImg, edtname, edtAge, edtHeight, edtTall;
    private Button btnAdd;
    List<BlinkData> blinkData;
    BlinkAdapter adapter;
    LoadApi api;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member);
        initUi();
        adapter = new BlinkAdapter(blinkData);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember();

                ConnectApi.apiservice.getdata().enqueue(new Callback<List<BlinkData>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<List<BlinkData>> call, Response<List<BlinkData>> response) {
                        blinkData= response.body();
                        adapter.setmData(blinkData);
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<List<BlinkData>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void addMember() {
        String name, img, height, tall, age;
        name = edtname.getText().toString();
        img = edtImg.getText().toString();
        height = edtHeight.getText().toString();
        tall = edtTall.getText().toString();
        age = edtAge.getText().toString();

        BlinkData blinkData1 = new BlinkData();
        blinkData1.setCreatedAt("12");
        blinkData1.setName(name);
        blinkData1.setAvatar(img);
        blinkData1.setHeight(height);
        blinkData1.setTall(tall);
        blinkData1.setAge(Integer.parseInt(age));

        ConnectApi.apiservice.addData(blinkData1).enqueue(new Callback<List<BlinkData>>() {
            @Override
            public void onResponse(Call<List<BlinkData>> call, Response<List<BlinkData>> response) {
                blinkData = response.body();
                adapter.setmData(blinkData);
                adapter.LoadApi(AddMember.this::addMember);
                adapter.notifyDataSetChanged();
                finish();
            }

            @Override
            public void onFailure(Call<List<BlinkData>> call, Throwable t) {

            }
        });
        finish();
    }


    private void initUi() {
        edtname = findViewById(R.id.addname);
        edtImg = findViewById(R.id.addImg);
        edtAge = findViewById(R.id.addAge);
        edtHeight = findViewById(R.id.addheight);
        btnAdd = findViewById(R.id.confirm_button);
        edtTall = findViewById(R.id.addtall);
    }

}
