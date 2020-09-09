package com.example.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUserResponse {
    // Ini property utama, paling luar di kelas Example tadi
    /* Udah sama dengan ini kan.. */

    @SerializedName("page")
    @Expose
    private long page;
    @SerializedName("per_page")
    @Expose
    private long perPage;
    @SerializedName("total")
    @Expose
    private long total;
    @SerializedName("total_pages")
    @Expose
    private long totalPages;
    @SerializedName("data")
    @Expose
    private List<User> data = null;

    public long getPage() {
        return page;
    }

    public long getPerPage() {
        return perPage;
    }

    public long getTotal() {
        return total;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public List<User> getData() {
        return data;
    }
}
