package com.example.cloneicaller.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.FragmentCallKeyboardBinding;

import java.util.ArrayList;

public class FragmentCallKeyboard extends Fragment implements View.OnClickListener {
    //thanhnv
    public static String numberDislayed = "";

    FragmentCallKeyboardBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCallKeyboardBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }

        binding.tvEnterPhoneNum.setText(numberDislayed);

        binding.btnNum0.setOnClickListener(this);
        binding.btnNum1.setOnClickListener(this);
        binding.btnNum2.setOnClickListener(this);
        binding.btnNum3.setOnClickListener(this);
        binding.btnNum4.setOnClickListener(this);
        binding.btnNum5.setOnClickListener(this);
        binding.btnNum6.setOnClickListener(this);
        binding.btnNum7.setOnClickListener(this);
        binding.btnNum8.setOnClickListener(this);
        binding.btnNum9.setOnClickListener(this);
        binding.btnNumstar.setOnClickListener(this);
        binding.btnNumthang.setOnClickListener(this);
        binding.btnNumundo.setOnClickListener(this);
        binding.btnCall.setOnClickListener(this);

        binding.btnNumundo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                numberDislayed = "";
                binding.tvEnterPhoneNum.setText(numberDislayed);
                return true;
            }
        });

        binding.btnNum0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " +";
                } else {
                    numberDislayed = numberDislayed + "+";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                return true;
            }
        });
        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_num0:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 0";
                } else {
                    numberDislayed = numberDislayed + "0";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num1:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 1";
                } else {
                    numberDislayed = numberDislayed + "1";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num2:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 2";
                } else {
                    numberDislayed = numberDislayed + "2";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num3:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 3";
                } else {
                    numberDislayed = numberDislayed + "3";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num4:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 4";
                } else {
                    numberDislayed = numberDislayed + "4";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num5:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 5";
                } else {
                    numberDislayed = numberDislayed + "5";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num6:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 6";
                } else {
                    numberDislayed = numberDislayed + "6";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num7:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 7";
                } else {
                    numberDislayed = numberDislayed + "7";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num8:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 8";
                } else {
                    numberDislayed = numberDislayed + "8";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_num9:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " 9";
                } else {
                    numberDislayed = numberDislayed + "9";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_numthang:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " #";
                } else {
                    numberDislayed = numberDislayed + "#";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_numstar:
                if (checkFormat(numberDislayed)) {
                    numberDislayed = numberDislayed + " *";
                } else {
                    numberDislayed = numberDislayed + "*";
                }
                binding.tvEnterPhoneNum.setText(numberDislayed);
                break;
            case R.id.btn_numundo:
                if (numberDislayed.length() >= 1) {
                    char text[] = numberDislayed.toCharArray();
                    int n = text.length;
                    int i = n - 1;
                    if (numberDislayed.charAt(i) != ' ') {
                        numberDislayed = removeCharAtString(numberDislayed, i);
                    } else {
                        numberDislayed = removeCharAtString(numberDislayed, i);
                        numberDislayed = removeCharAtString(numberDislayed, i - 1);
                    }
                    binding.tvEnterPhoneNum.setText(numberDislayed);
                }
                break;
            case R.id.btn_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + numberDislayed.trim()));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                getContext().startActivity(intent);
                break;
        }
    }
    public static String removeCharAtString(String text, int p){
        if(text.length()<1){
            return "";
        }
        return text.substring(0,p)+ text.substring(p+1);
    }
    private boolean checkFormat(String text){
        boolean check = false;
        char textString[] = text.toCharArray();
        if (text.length()==3||text.length()==7||text.length()==10){
            check = true;
        }
        return check;
    }

}
