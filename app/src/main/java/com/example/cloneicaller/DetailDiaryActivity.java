package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cloneicaller.databinding.ActivityDetailDiaryBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailDiaryActivity extends AppCompatActivity {

    String name, duration, date, number, type, country, numbertype;

    ActivityDetailDiaryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailDiaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        duration = intent.getStringExtra("duration");
        date = intent.getStringExtra("date");
        number = intent.getStringExtra("number");
        type = intent.getStringExtra("type");
        country = intent.getStringExtra("country");
        numbertype = intent.getStringExtra("numbertype");


        //Nếu là số lạ thì hiển thị chi tiết là số không thì hiển thị tên
        if (null == name) {
            binding.tvNameDetail.setText(number);
            binding.layoutSaveContact.setVisibility(View.VISIBLE);
        } else {
            binding.tvNameDetail.setText(name);
        }

        //Lưu số lạ
        binding.imgSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);
                startActivity(intent);
            }
        });

        binding.tvDateDetail.setText(date);

        binding.tvNumberDetail.setText(number);

        if (type.equals("OUTGOING")) {
            binding.imgType.setImageResource(R.drawable.ic_out_call);
        } else if (type.equals("INCOMING")) {
            binding.imgType.setImageResource(R.drawable.ic_in_call);
        } else {
            binding.imgType.setImageResource(R.drawable.ic_miss_call);
        }


        binding.imgCallWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                if (ActivityCompat.checkSelfPermission(DetailDiaryActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) DetailDiaryActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
                startActivity(intent);
            }
        });

        binding.imgMessageWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", number);
                smsIntent.putExtra("sms_body", "");
                startActivity(smsIntent);
            }
        });

        binding.imgEditDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.tvDuration.setText(duration);

        binding.tvNumberDetail2.setText(number);

        binding.tvPhoneNumberDetail.setText(number);

        binding.tvNation.setText(country);

        binding.tvNetwork.setText(numbertype);

        binding.imgBtnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}