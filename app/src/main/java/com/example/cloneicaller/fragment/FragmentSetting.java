package com.example.cloneicaller.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.cloneicaller.DialogBeforeCallActivity;
import com.example.cloneicaller.IntroduceActivity;
import com.example.cloneicaller.LoginActivity;
import com.example.cloneicaller.QuestionActivity;
import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.FragmentSettingBinding;

import static com.example.cloneicaller.CallStateReceiver.OVERLAY_PERMISSION_REQ_CODE;

public class FragmentSetting extends Fragment {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 1234;
    private CardView btnIntroduce;
    private CardView btnTermsSec;
    private CardView btnTermsUse;
    private CardView btnQuestion;
    private CardView btnLogout;

    FragmentSettingBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext())) {
            askPermission();
        }

        binding.btnIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), IntroduceActivity.class));
            }
        });

        binding.btnTermsSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnTermsUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), QuestionActivity.class));
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
                SharedPreferences.Editor spE = sharedPreferences.edit();
                spE.clear();
                spE.apply();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        return view;
    }


    @TargetApi(23)
    public void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getContext().getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(getContext())) {
                // SYSTEM_ALERT_WINDOW permission not granted...
            }
        }
    }
}
