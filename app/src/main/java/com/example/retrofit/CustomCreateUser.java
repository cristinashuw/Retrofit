package com.example.retrofit;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.retrofit.model.CreateUser;
import com.example.retrofit.model.User;

import java.util.List;

public class CustomCreateUser {
    private List<CreateUser> userList;
    private Context context;

    public CustomCreateUser(Context context, List<CreateUser> userList){
        this.context = context;
        this.userList = userList;
    }
    static class CustomViewHolder {
        public final View mView;

        TextView name;
        TextView job;

        CustomViewHolder(View itemView) {
            mView = itemView;

            name = mView.findViewById(R.id.name);
            job = mView.findViewById(R.id.job);
        }
    }


    public void onBindViewHolder(@NonNull CustomCreateUser.CustomViewHolder holder, int position) {
        holder.name.setText(userList.get(position).getName());
        holder.job.setText(userList.get(position).getJob());

    }


}
