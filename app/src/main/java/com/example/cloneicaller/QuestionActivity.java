package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.cloneicaller.Models.Question;
import com.example.cloneicaller.adapter.QuestionAdapter;
import com.example.cloneicaller.databinding.ActivityQuestionBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    //Thanhnv
    QuestionAdapter questionAdapter;
    List<Question> questionList;

    ActivityQuestionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        questionList = new ArrayList<>();

        questionAdapter = new QuestionAdapter(this, questionList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rcQuestion.setLayoutManager(linearLayoutManager);
        binding.rcQuestion.setAdapter(questionAdapter);

        addItemsFromJSON();

        binding.imgBackQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnDisplayOnOtherApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!Settings.canDrawOverlays(QuestionActivity.this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, 0);
//                }
            }
        });
    }

    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String question = itemObj.getString("question");
                String answer = itemObj.getString("answer");

                Question question1 = new Question(question, answer);
                questionList.add(question1);
            }

        } catch (JSONException | IOException e) {
            Log.d("ABC", "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.faq_vn);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}