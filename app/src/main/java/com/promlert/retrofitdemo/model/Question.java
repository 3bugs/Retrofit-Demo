package com.promlert.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Promlert on 3/22/2016.
 */
public class Question {

    @SerializedName("question_id")
    public int questionId;
    @SerializedName("title")
    public String title;
    @SerializedName("detail")
    public String detail;
    @SerializedName("picture")
    public String picture;

    @Override
    public String toString() {
        return title;
    }
}
