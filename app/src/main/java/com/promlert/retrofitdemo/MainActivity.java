package com.promlert.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.promlert.retrofitdemo.model.Question;
import com.promlert.retrofitdemo.model.Questions;
import com.promlert.retrofitdemo.net.WebServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<Question> adapter = new ArrayAdapter<Question>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<Question>()
        );
        mListView.setAdapter(adapter);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuestionData();
            }
        });
    }

    private void loadQuestionData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2/online_quiz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServices services = retrofit.create(WebServices.class);

        // โหลดคำถามชุดที่ 1
        Call<Questions> call = services.getQuestions(1);
        call.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                if (response.body() != null) {
                    ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) mListView.getAdapter();
                    adapter.clear();
                    adapter.addAll(response.body().questionList);

                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "body() returns null!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
