package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.User;

import java.util.List;

public class CustomCreateUser {
    private List<User> userList;
    private Context context;

    public CustomCreateUser(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }
    static class CustomViewHolder {
        public final View mView;

        TextView firstName;
        TextView lastName;
        TextView email;

        CustomViewHolder(View itemView) {
            mView = itemView;

            firstName = mView.findViewById(R.id.firstName);
            lastName = mView.findViewById(R.id.lastName);
            email = mView.findViewById(R.id.email);
        }
    }


    public void onBindViewHolder(@NonNull CustomCreateUser.CustomViewHolder holder, int position) {
        holder.firstName.setText(userList.get(position).getFirstName());
        holder.lastName.setText(userList.get(position).getLastName());
        holder.email.setText(userList.get(position).getEmail());

    }


}
