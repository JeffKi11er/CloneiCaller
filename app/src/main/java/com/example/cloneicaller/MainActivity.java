package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.cloneicaller.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String g_token = sharedPreferences.getString("g_token","");
        Log.e("g_token",g_token);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (g_token.length()>0){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), WellcomeActivity.class));
                    finish();
                }
            }
        }, 2500);
    }
}