package com.example.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {
    @SerializedName("data")
    @Expose
    private Data data;

    public class Data {

        @SerializedName("id")
        @Expose
        private long id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("avatar")
        @Expose
        private String avatar;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Data withId(long id) {
            this.id = id;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Data withEmail(String email) {
            this.email = email;
            return this;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public Data withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Data withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Data withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public UpdateUserResponse withData(Data data) {
        this.data = data;
        return this;
    }



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
