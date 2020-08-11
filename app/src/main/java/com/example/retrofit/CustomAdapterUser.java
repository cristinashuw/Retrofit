package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.User;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterUser extends RecyclerView.Adapter<CustomAdapterUser.CustomViewHolder> {
    private List<User> userList;
    private Context context;

    public CustomAdapterUser(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

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
        builder.build().load(userList.get(position).getEmail())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}