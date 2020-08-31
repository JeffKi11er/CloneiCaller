package com.example.cloneicaller.fragment;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.DetailContact;
import com.example.cloneicaller.DetailDiaryActivity;
import com.example.cloneicaller.R;
import com.example.cloneicaller.SwipeHelper;
import com.example.cloneicaller.adapter.AdapterAlphabetDiary;
import com.example.cloneicaller.adapter.AdapterPersonDiary;
import com.example.cloneicaller.adapter.ItemPersonAdapter;
import com.example.cloneicaller.common.AppConstants;
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.DialogDeleteContactBinding;
import com.example.cloneicaller.databinding.FragmentDiaryBinding;
import com.example.cloneicaller.item.ItemGroup;
import com.example.cloneicaller.item.ItemPerson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FragmentDiary extends Fragment implements View.OnClickListener, ItemPersonAdapter.PersonItemListener, AppConstants {
    public static String numberDiary;
    private ArrayList<ItemPerson> people = new ArrayList<>();
    private FragmentDiaryListner listener;

    FragmentDiaryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDiaryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.floatingBtnDiary.setOnClickListener(this);
        fetchContact();
        if (people.size() == 0) {
            binding.tvNoneDataDiary.setText(getString(R.string.none_data));
            binding.tvNoneDataDiary.setVisibility(View.VISIBLE);
            binding.rclDiary.setVisibility(View.GONE);
        }
        SwipeHelper swipeHelper = new SwipeHelper(getContext(), binding.rclDiary) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton("XÓA", 1, Color.parseColor("#A90939"), new SwipeHelper.UnderlayButtonClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Dialog dialog = new Dialog(getContext());
                        DialogDeleteContactBinding binding;
                        binding = DialogDeleteContactBinding.inflate(getLayoutInflater());
                        View view1 = binding.getRoot();
                        dialog.setContentView(view1);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        dialog.show();
                    }
                }
                ));
            }
        };
        return view;
    }

    private void fetchContact() {
        try {
            people = Common.resolverArrayList(null, getContext());
            people = Common.sortList(people);
            people = Common.addAlphabet(people);
            listener.onPutListDiarySent(people);
            ItemPersonAdapter adapter = new ItemPersonAdapter(getContext(), people);
            binding.rclDiary.setAdapter(adapter);
            adapter.setListener(this);
        } catch (Exception e) {
            Log.e("abc", e.getMessage());
        }
    }

    private ArrayList<String> sortArrayList(ArrayList<String> item) {
        Collections.sort(item, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return item;
    }

    private ArrayList<String> getAlList(ArrayList<String> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        //String temp;
        String[] strings = list.toArray(new String[0]);
        int n = strings.length;
        String[] al = new String[n];
        for (int i = 0; i < n; i++) {

            al[i] = Character.toString(strings[i].charAt(0));
        }

        ArrayList asList = (ArrayList) Arrays.asList(al);
        return arrayList;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "");
        startActivity(intent);
    }

    @Override
    public void onClickPerson(int position) {

        Intent intent = new Intent(getActivity(), DetailContact.class);
        Bundle bundle = new Bundle();
        bundle.putString(INTENT_NAME, people.get(position).getName());
        bundle.putString(INTENT_NUMBER, people.get(position).getNumber());
        intent.putExtras(bundle);
        intent.putExtra(INTENT_NAME, people.get(position).getName());
        intent.putExtra(INTENT_NUMBER, people.get(position).getNumber());
        intent.putExtra(INTENT_BLOCK, false);
        intent.putExtra(INTENT_BLOCK_TYPE, "");

        //Toast.makeText(getContext(),""+people.get(position).getName() +":"+people.get(position).getNumber(),Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getActivity(), DetailDiaryActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("name", people.get(position).getName());
//        bundle.putString("number", people.get(position).getNumber());
//        intent.putExtras(bundle);
        startActivity(intent);
    }

    public interface FragmentDiaryListner {
        void onPutListDiarySent(ArrayList<ItemPerson> people);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDiaryListner) {
            listener = (FragmentDiaryListner) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
