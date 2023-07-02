package com.mirea.kt.android.kyrsovaya_dronov;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



import java.util.List;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class CalcInfoResponse {
    @SerializedName("result_code")
    @Expose
    private int result;


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }




}