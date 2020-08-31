package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.cloneicaller.Room.BlockItemDatabase;
import com.example.cloneicaller.adapter.AdapterBlockerSearch;
import com.example.cloneicaller.adapter.AdapterItemSearch;
import com.example.cloneicaller.adapter.BlockListItemAdapter;
import com.example.cloneicaller.common.AppConstants;
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.ActivityFilterSearchBinding;
import com.example.cloneicaller.databinding.ActivityHomeBinding;
import com.example.cloneicaller.fragment.FragmentListBlock;
import com.example.cloneicaller.item.BlockerPersonItem;
import com.example.cloneicaller.item.ItemPerson;

import java.util.ArrayList;
import java.util.List;

public class FilterSearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterItemSearch.SearchClickListener, AdapterBlockerSearch.SearchBlockerListener, AppConstants {
    ActivityFilterSearchBinding binding;
    private ArrayList<ItemPerson> personArrayList = new ArrayList<>();
    private List<BlockerPersonItem> blockerArrayList ;
    private AdapterItemSearch adapterPerson;
    private AdapterBlockerSearch adapterBlocker;
    private List<BlockerPersonItem>blocker;
    private ArrayList<ItemPerson>filterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilterSearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        binding.rclFilter.setVisibility(View.GONE);
        binding.rclFilterBlock.setVisibility(View.GONE);
        binding.lnearFilterSearch.setVisibility(View.GONE);
        personArrayList = Common.resolverArrayList(null,this);
        personArrayList = Common.sortList(personArrayList);
        adapterPerson = new AdapterItemSearch(this,personArrayList);
        BlockItemDatabase database = Room.databaseBuilder(getApplicationContext(),BlockItemDatabase.class,
                "blockItems")
                .allowMainThreadQueries()
                .build();
        blockerArrayList = database.getItemDao().getItems();
        adapterBlocker = new AdapterBlockerSearch(this,blockerArrayList);
        binding.rclFilter.setAdapter(adapterPerson);
        binding.rclFilterBlock.setAdapter(adapterBlocker);
        adapterPerson.setListener(this);
        adapterBlocker.setListener(this);
        binding.tvDisplayedDiary.setOnClickListener(this);
        binding.tvDisplayedBlock.setOnClickListener(this);
        binding.imgBtnBack.setOnClickListener(this);
        binding.edtSearch.setFocusable(true);
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void filter(String toString) {
        blocker = new ArrayList<>();
        filterList = new ArrayList<>();
        for (ItemPerson person :personArrayList) {
            if (person.getName().trim().toLowerCase().startsWith(toString.trim().toLowerCase())){
                filterList.add(person);
            }
        }
        for (BlockerPersonItem blockerPersonItem :blockerArrayList) {
            if (blockerPersonItem.getName().trim().toLowerCase().startsWith(toString.trim().toLowerCase())){
                blocker.add(blockerPersonItem);
            }
        }
        int count;
        int n;
        count = filterList.size();
        n = blocker.size();
        if(count > 0||n >0){
            binding.lnearFilterSearch.setVisibility(View.VISIBLE);
            binding.tvDisplayedDiary.setText("DANH BẠ ("+filterList.size()+")");
            binding.tvDisplayedBlock.setText("DANH SÁCH CHẶN ("+blocker.size()+")");
        }
        adapterPerson.filterList(filterList);
        adapterBlocker.filterListB(blocker);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_back:
                finish();
                break;
            case R.id.tv_displayed_diary:
                binding.rclFilterBlock.setVisibility(View.GONE);
                binding.rclFilter.setVisibility(View.VISIBLE);
                binding.tvDisplayedDiary.setTextColor(Color.RED);
                binding.tvDisplayedBlock.setTextColor(Color.GRAY);
                break;
            case R.id.tv_displayed_block:
                binding.rclFilterBlock.setVisibility(View.VISIBLE);
                binding.rclFilter.setVisibility(View.GONE);
                binding.tvDisplayedDiary.setTextColor(Color.GRAY);
                binding.tvDisplayedBlock.setTextColor(Color.RED);
                break;
        }
    }

    @Override
    public void onBlockerClickListener(int position) {
        Intent intent = new Intent(FilterSearchActivity.this, DetailContact.class);
        intent.putExtra(INTENT_NAME,blocker.get(position).getName());
        intent.putExtra(INTENT_NUMBER,blocker.get(position).getNumber());
        intent.putExtra(INTENT_BLOCK,true);
        intent.putExtra(INTENT_BLOCK_TYPE,blocker.get(position).getType());
        startActivity(intent);
    }

    @Override
    public void onSearchClickListener(int position) {
        Intent intent = new Intent(FilterSearchActivity.this, DetailContact.class);
        intent.putExtra(INTENT_NAME,filterList.get(position).getName());
        intent.putExtra(INTENT_NUMBER,filterList.get(position).getNumber());
        intent.putExtra(INTENT_BLOCK,false);
        startActivity(intent);
    }
}