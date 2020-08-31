package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cloneicaller.databinding.ActivityWellcomeBinding;

public class WellcomeActivity extends AppCompatActivity {

    ActivityWellcomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWellcomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WellcomeActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });

    }
}