package com.example.testmvvm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data
{
    @Expose
    @SerializedName("category")
    private String category;
    @Expose
    @SerializedName("category_id")
    private int categoryId;

    public String getCategory()
    {
        return category;
    }

    public String getCategoryId()
    {
        return categoryId + "";
    }
}
