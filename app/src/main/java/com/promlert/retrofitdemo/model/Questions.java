package com.promlert.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Promlert on 3/22/2016.
 */
public class Questions {

    @SerializedName("question_data")
    public List<Question> questionList;

}
