package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.model.User;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterUser extends RecyclerView.Adapter<CustomAdapterUser.CustomViewHolder> {
    private List<User> userList;
    private Context context;
    private ClickListener clickListener;

    public CustomAdapterUser(Context context, List<User> userList, ClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.clickListener = listener;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

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
            buttonDetailUser = itemView.findViewById(R.id.detailUserButton);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, User user);
    }

//    private void openDetailUser() {
//        Intent intent = new Intent(this, DetailUserActivity.class);
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
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(userList.get(position).getAvatar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.fullName.setText(userList.get(position).getFullName());
        holder.email.setText(userList.get(position).getEmail());
        holder.buttonDetailUser.setOnClickListener(view -> clickListener.onItemClick(position, userList.get(position)));
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }
}