package com.example.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUser {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("job")
        @Expose
        private String job;

        public String getName() {
            return name;
        }

        public String getJob() {
            return job;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setJob(String job) {
                this.job = job;
        }
}
