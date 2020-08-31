package com.example.cloneicaller.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.cloneicaller.ListItem;
import com.example.cloneicaller.Models.Contact;
import com.example.cloneicaller.Models.DateItem;
import com.example.cloneicaller.Models.GeneralItem;
import com.example.cloneicaller.PermissionActivity;
import com.example.cloneicaller.R;
import com.example.cloneicaller.SwipeHelper;
import com.example.cloneicaller.adapter.ItemPersonAdapter;
import com.example.cloneicaller.adapter.ListHistoryAdapter;
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.FragmentHistoryBinding;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class FragmentListHistory extends Fragment {

    private List<Contact> contactList = new ArrayList<>();
    List<ListItem> consolidatedList = new ArrayList<>();

    FragmentHistoryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        contactList = Common.getCallDetails(getContext());

        HashMap<String, List<Contact>> groupedHashMap = Common.groupDataIntoHashMap(contactList);


        for (String date : groupedHashMap.keySet()) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);


            for (Contact contact : groupedHashMap.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setContact(contact);
                consolidatedList.add(generalItem);
            }
        }

        if (contactList.size() == 0){
            binding.tvNoneDataHistory.setText(getString(R.string.none_data));
            binding.tvNoneDataHistory.setVisibility(View.VISIBLE);
            binding.rcListHistory.setVisibility(View.GONE);
        }

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext());

        binding.rcListHistory.setLayoutManager(linearLayoutManager);

        ListHistoryAdapter listHistoryAdapter = new ListHistoryAdapter(getContext(), consolidatedList);
        binding.rcListHistory.setAdapter(listHistoryAdapter);

        SwipeHelper swipeHelper = new SwipeHelper(getContext(), binding.rcListHistory) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton("CHẶN", 1, Color.parseColor("#A90939"), new SwipeHelper.UnderlayButtonClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Toast.makeText(getContext(), "ABC", Toast.LENGTH_SHORT).show();
                    }
                }
                ));
            }
        };

        return view;
    }
}


