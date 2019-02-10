package com.example.testmvvm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelCommenResponse
{


    @Expose
    @SerializedName("data")
    private List<Data> data;
    @Expose
    @SerializedName("code")
    private int code;
    @Expose
    @SerializedName("status")
    private String status;


    public List<Data> getData()
    {
        return data;
    }

    public int getCode()
    {
        return code;
    }

    public String getStatus()
    {
        return status;
    }
}
