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

public class CustomCreateUser extends RecyclerView.Adapter<CustomCreateUser.CustomViewHolder> {
    private List<User> userList;
    private Context context;

    public CustomCreateUser(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }
    static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView firstName;
        TextView lastName;
        TextView email;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            firstName = mView.findViewById(R.id.firstName);
            lastName = mView.findViewById(R.id.lastName);
            email = mView.findViewById(R.id.email);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_add_user, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCreateUser.CustomViewHolder holder, int position) {
        holder.firstName.setText(userList.get(position).getFirstName());
        holder.lastName.setText(userList.get(position).getLastName());
        holder.email.setText(userList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
