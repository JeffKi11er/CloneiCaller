package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cloneicaller.common.AppConstants;
import com.example.cloneicaller.databinding.ActivityDetailContactBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailContact extends AppCompatActivity implements AppConstants {
    private String name;
    private String number;
    ActivityDetailContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (getIntent().getBooleanExtra(INTENT_BLOCK,false)==false ) {
            binding.imgPersonDetail.setBorderColorResource(R.color.colorPurpleBG);
            binding.lnearDetail.setBackgroundResource(R.drawable.bg_update_data_success);
            binding.lnearSave.setVisibility(View.GONE);
            binding.imgEditDetailContact.setVisibility(View.VISIBLE);
            binding.rltBlockType.setVisibility(View.GONE);
            binding.tvStatus.setText("CHẶN");
        }else {
            binding.imgPersonDetail.setBorderColorResource(R.color.colorRed);
            binding.imgEditDetailContact.setVisibility(View.GONE);
            binding.lnearDetail.setBackgroundResource(R.drawable.bg_data_update);
            binding.lnearSave.setVisibility(View.VISIBLE);
            binding.rltBlockType.setVisibility(View.VISIBLE);
            binding.tvBlockType.setText(getIntent().getStringExtra(INTENT_BLOCK_TYPE));
            binding.tvStatus.setText("BỎ CHẶN");
        }
        name = getIntent().getStringExtra(INTENT_NAME);
        number = getIntent().getStringExtra(INTENT_NUMBER);
        binding.tvNameDetailContact.setText(name);
        binding.tvNumberDetailContact.setText(number);
        binding.tvDetailContactPhoneNum.setText(number);

        //Gọi điện
        binding.imgDetailContactCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(DetailContact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) DetailContact.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
                startActivity(intent);
            }
        });

        //Gửi tin nhắn
        binding.imgDetailContactMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", number);
                smsIntent.putExtra("sms_body", "");
                startActivity(smsIntent);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}