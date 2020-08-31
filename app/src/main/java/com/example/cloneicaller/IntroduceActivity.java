package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.cloneicaller.databinding.ActivityIntroduceBinding;

public class IntroduceActivity extends AppCompatActivity {
    ActivityIntroduceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroduceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.imgBackIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.wvIntroduce.getSettings().getJavaScriptEnabled();
        binding.wvIntroduce.loadUrl("file:///android_res/raw/about_vn.html");
    }
}