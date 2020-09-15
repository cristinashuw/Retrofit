package com.example.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("job")
        @Expose
        private String job;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public UpdateUserResponse withName(String name) {
            this.name = name;
            return this;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public UpdateUserResponse withJob(String job) {
            this.job = job;
            return this;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public UpdateUserResponse withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }


}
