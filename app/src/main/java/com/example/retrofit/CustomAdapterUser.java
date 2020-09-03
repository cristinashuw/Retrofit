package com.example.retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class CustomAdapterUser extends RecyclerView.Adapter<CustomAdapterUser.CustomViewHolder> {
    private List<User> userList;
    private Context context;

    public CustomAdapterUser(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView fullName;
//        TextView lastName;
        TextView email;
        private ImageView imageView;
        private Button buttonDetailUser;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            fullName = mView.findViewById(R.id.fullName);
//            lastName = mView.findViewById(R.id.lastName);
            email = mView.findViewById(R.id.email);
            imageView = mView.findViewById(R.id.imageView);
            buttonDetailUser = mView.findViewById(R.id.detailUserButton);


        }
        
    }



//    private void openDetailUser() {
//        Intent intent = new Intent(this, DetailUser.class);
//        startActivity(intent);
//    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterUser.CustomViewHolder holder, int position) {

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(userList.get(position).getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.fullName.setText(userList.get(position).getFullName());
        holder.email.setText(userList.get(position).getEmail());
        //        this istodo
        holder.buttonDetailUser.setOnClickListener(null);

    }


    @Override
    public int getItemCount() {
        return userList.size();
    }
}