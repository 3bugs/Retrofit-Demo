package com.promlert.retrofitdemo.net;

import com.promlert.retrofitdemo.model.Questions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Promlert on 3/22/2016.
 */
public interface WebServices {

    @GET("get_questions.php")
    Call<Questions> getQuestions(@Query("quiz_id") int quizId);

}
