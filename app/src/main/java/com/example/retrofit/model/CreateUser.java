package com.example.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUser {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("job")
        @Expose
        public String job;

        public String getName() {
            return name;
        }

        public String getJob() {
            return job;
        }

}
