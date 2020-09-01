package com.example.retrofit.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateUserResponse {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("job")
        @Expose
        public String job;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("createdAt")
        @Expose
        public String createdAt;
        private List<User> data = null;


    public String getName() {
            return name;
        }
        public String getJob() {
            return job;
        }
        public String getId() {
            return id;
        }
        public String getCreatedAt() {
            return createdAt;
        }

        public List<User> postData() {return data;}
}
