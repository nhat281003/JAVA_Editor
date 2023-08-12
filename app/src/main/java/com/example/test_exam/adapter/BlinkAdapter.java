package com.example.test_exam.adapter;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_exam.MainActivity;
import com.example.test_exam.R;
import com.example.test_exam.api.ConnectApi;
import com.example.test_exam.api.LoadApi;
import com.example.test_exam.model.BlinkData;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlinkAdapter extends RecyclerView.Adapter<BlinkAdapter.BlinkViewholder> {
    List<BlinkData> blinkDataList;
    LoadApi api;

   public void LoadApi(LoadApi api){
       this.api = api;

   }
    public void setmData(List<BlinkData> mData){
        this.blinkDataList = mData;
        notifyDataSetChanged();
    }

    public BlinkAdapter(List<BlinkData> blinkDataList) {
        this.blinkDataList = blinkDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BlinkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new BlinkViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlinkViewholder holder, int position) {
        BlinkData blinkData = blinkDataList.get(position);
        holder.name.setText(blinkData.getName());
        holder.age.setText(String.valueOf(blinkData.getAge()));
        holder.height.setText(blinkData.getHeight());
        holder.tall.setText(blinkData.getTall());
        Picasso.get().load(blinkData.getAvatar()).into(holder.avt);
        holder.dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete Member");
                builder.setMessage("Do you want delete this member ???");
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConnectApi.apiservice.deleteData(blinkData.getId()).enqueue(new Callback<List<BlinkData>>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(Call<List<BlinkData>> call, Response<List<BlinkData>> response) {
                                blinkDataList= response.body();
                                Toast.makeText(v.getContext(), "Delete Success!", Toast.LENGTH_SHORT).show();
                                api.Reload();// thêm cái này -------------------------
                                notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<List<BlinkData>> call, Throwable t) {

                            }
                        });


                        ConnectApi.apiservice.getdata().enqueue(new Callback<List<BlinkData>>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(Call<List<BlinkData>> call, Response<List<BlinkData>> response) {
                                blinkDataList= response.body();
                                notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<List<BlinkData>> call, Throwable t) {

                            }
                        });
                    }

                });

                builder.show();


            }
        });

        holder.edit.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_update);
            EditText edname = dialog.findViewById(R.id.dialog_edtName);
            EditText edheight = dialog.findViewById(R.id.dialog_edtHeight);
            EditText edimage = dialog.findViewById(R.id.dialog_edtImage);
            EditText edtall = dialog.findViewById(R.id.dialog_edtTall);
            EditText edage = dialog.findViewById(R.id.dialog_edtAge);
            Button btnUp = dialog.findViewById(R.id.confirm_button);
            edname.setText(blinkData.getName());
            edheight.setText(blinkData.getHeight());
            edtall.setText(blinkData.getTall());
            edage.setText(String.valueOf(blinkData.getAge()));
            edimage.setText(blinkData.getAvatar());


            btnUp.setOnClickListener(v1 -> {
                BlinkData blinkData1 = new BlinkData();
                blinkData1.setName(edname.getText().toString().trim());
                blinkData1.setAge(Integer.parseInt(edage.getText().toString().trim()));
                blinkData1.setAvatar(edimage.getText().toString().trim());
                blinkData1.setHeight(edheight.getText().toString().trim());
                blinkData1.setTall(edtall.getText().toString().trim());


                ConnectApi.apiservice.editData(blinkData.getId(), blinkData1).enqueue(new Callback<Void>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(v.getContext(), "Update thanh cong!", Toast.LENGTH_SHORT).show();
                            api.Reload();// thêm cái này --------------------------
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


                ConnectApi.apiservice.getdata().enqueue(new Callback<List<BlinkData>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<List<BlinkData>> call, Response<List<BlinkData>> response) {
                        blinkDataList= response.body();
                        notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<List<BlinkData>> call, Throwable t) {

                    }
                });

            });

            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
       if(blinkDataList != null){
           return blinkDataList.size();
       }else {
           return  0;
       }
    }

    public  static class BlinkViewholder extends  RecyclerView.ViewHolder{
        TextView name, age , tall, height;
        ImageView avt, dele, edit;
        public BlinkViewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            tall = itemView.findViewById(R.id.tall);
            height = itemView.findViewById(R.id.height);
            avt = itemView.findViewById(R.id.avt);
            dele = itemView.findViewById(R.id.xoa);
            edit = itemView.findViewById(R.id.sua);
        }

    }

}
