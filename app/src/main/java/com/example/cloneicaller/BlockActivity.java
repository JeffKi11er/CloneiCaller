package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cloneicaller.Room.BlockItemDatabase;
import com.example.cloneicaller.adapter.ItemBlockAdapter;
import com.example.cloneicaller.databinding.ActivityBlockBinding;
import com.example.cloneicaller.item.BlockTypeItem;
import com.example.cloneicaller.item.BlockerPersonItem;

import java.util.ArrayList;

public class BlockActivity extends AppCompatActivity {

    ActivityBlockBinding binding;
    private String nameBlock;
    private int imgBlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlockBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        BlockItemDatabase database = Room.databaseBuilder(getApplicationContext(),BlockItemDatabase.class,"blockItems")
                .allowMainThreadQueries()
                .build();
        ArrayList<BlockTypeItem>items = new ArrayList<>();
        items.add(new BlockTypeItem("BẤT ĐỘNG SẢN ",R.drawable.ic_red_real_estate,""));
        items.add(new BlockTypeItem("KHÁC",R.drawable.ic_red_other,""));
        items.add(new BlockTypeItem("ĐÒI NỢ",R.drawable.ic_red_loan_collection,""));
        items.add(new BlockTypeItem("CHO VAY",R.drawable.ic_red_financial_service,""));
        items.add(new BlockTypeItem("LỪA ĐẢO",R.drawable.ic_block_setting_scam,""));
        items.add(new BlockTypeItem("QUẢNG CÁO",R.drawable.ic_block_setting_advertising,""));
        ItemBlockAdapter itemBlockAdapter = new ItemBlockAdapter(this,0,items);
        binding.spin.setAdapter(itemBlockAdapter);
        binding.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BlockTypeItem blockTypeItem = (BlockTypeItem) parent.getItemAtPosition(position);
                //Toast.makeText(BlockActivity.this,blockTypeItem.getName(),Toast.LENGTH_LONG).show();
                nameBlock = blockTypeItem.getName();
                imgBlock = blockTypeItem.getIcon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getItemDao().insertAll(new BlockerPersonItem(binding.edtBlockerName.getText().toString(),
                        nameBlock,
                        binding.edtBlockerNumber.getText().toString(),
                        imgBlock,-1));
                startActivity(new Intent(BlockActivity.this, HomeActivity.class));
            }
        });
        binding.imgBackFrBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        binding.spin.setVisibility(View.GONE);
//        binding.cigmType.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.spin.setVisibility(View.VISIBLE);
//            }
//        });
    }
}