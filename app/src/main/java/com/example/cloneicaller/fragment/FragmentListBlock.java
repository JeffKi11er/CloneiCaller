package com.example.cloneicaller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cloneicaller.DetailDiaryActivity;
import com.example.cloneicaller.FilterSearchActivity;
import com.example.cloneicaller.HomeActivity;
import com.example.cloneicaller.R;
import com.example.cloneicaller.adapter.AdapterItemSearch;
import com.example.cloneicaller.adapter.ItemPersonAdapter;
import com.example.cloneicaller.adapter.PageBlockerAdapter;
import com.example.cloneicaller.adapter.PagerAdapter;
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.FragmentBlockingBinding;
import com.example.cloneicaller.item.ItemPerson;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FragmentListBlock extends Fragment implements View.OnClickListener, AdapterItemSearch.SearchClickListener {
    private AdapterItemSearch adapterPerson;
    private ArrayList<ItemPerson> persons = new ArrayList<>();
    private ArrayList<ItemPerson> filterList;
    private int count;

    FragmentBlockingBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentBlockingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        persons = new ArrayList<>();
        PageBlockerAdapter adapter = new PageBlockerAdapter(getChildFragmentManager());
        binding.view.setAdapter(adapter);
        binding.tabBlock.setupWithViewPager(binding.view);

        persons = new ArrayList<>();
        filterList = new ArrayList<>();
        PageBlockerAdapter adapter1 = new PageBlockerAdapter(getChildFragmentManager());

        binding.imgBtnSearch.setOnClickListener(this);

        binding.tabBlock.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int potion = tab.getPosition();
                binding.tvHeader.setText(adapter.getPageTitle(potion));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        binding.edtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


        return view;
    }

    private void filter(String toString) {
        persons = Common.resolverArrayList(null, getContext());
        persons = Common.sortList(persons);
        for (ItemPerson person : persons) {
            if (person.getName().trim().toLowerCase().startsWith(toString.trim().toLowerCase())) {
                filterList.add(person);
            }
        }
        count = filterList.size();
        if (count > 0) {
            binding.rltRclFilter.setVisibility(View.VISIBLE);
            binding.tvHeader.setText("DANH BẠ (" + filterList.size() + ")");
        }
        adapterPerson.filterList(filterList);
        filterList.clear();
        count = 0;
    }

//    public void setArrayListPerson(ArrayList<ItemPerson> listPerson) {
//        for (int i = 0; i < listPerson.size(); i++) {
//            persons.add(listPerson.get(i));
//        persons = Common.resolverArrayList(null,getContext());
//        persons = Common.sortList(persons);
//        for (ItemPerson person :persons) {
//            if (person.getName().trim().toLowerCase().startsWith(toString.trim().toLowerCase())){
//                filterList.add(person);
//            }
//        }
//        count  = filterList.size();
//        if(count > 0){
//            lnFilterSearch.setVisibility(View.VISIBLE);
//            tvDisplayedDiary.setText("DANH BẠ ("+filterList.size()+")");
//
//        }
//        adapterPerson.filterList(filterList);
//        filterList.clear();
//        count = 0;
//    }


//    public void setArrayListPerson(ArrayList<ItemPerson>listPerson){
//        for (int i = 0; i < listPerson.size(); i++) {
//            persons.add(listPerson.get(i));
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_search:
                startActivity(new Intent(getContext(), FilterSearchActivity.class));
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_view, new FragmentSearchFilter()).commit();
//                binding.rltRclFilter.setVisibility(View.VISIBLE);
                break;
//            case R.id.img_btn_back:
//                binding.lnearFilter.setVisibility(View.GONE);
//                binding.lnearRclFilter.setVisibility(View.GONE);
//                binding.lnearFilterSearch.setVisibility(View.GONE);
//                break;
//            case R.id.tv_displayed_diary:
//                binding.tvDisplayedDiary.setTextColor(Color.RED);
//                binding.tvDisplayedBlock.setTextColor(Color.GRAY);
//                binding.lnearRclFilter.setVisibility(View.VISIBLE);
//                break;
//            case R.id.tv_displayed_block:
//                binding.tvDisplayedDiary.setTextColor(Color.GRAY);
//                binding.tvDisplayedBlock.setTextColor(Color.RED);
//                binding.lnearRclFilter.setVisibility(View.GONE);
//
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_view, new FragmentSearchFilter()).commit();
//
//                break;
        }
    }


//    @Override
//    public void onReceived(int requestCode, int resultCode, Intent data) {
//
//         setArrayListPerson((ArrayList<ItemPerson>) data.getExtras().getSerializable("ModelList"));
//    }

    @Override
    public void onSearchClickListener(int position) {
        Intent intent = new Intent(getActivity(), DetailDiaryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name1", persons.get(position).getName());
        bundle.putString("number1", persons.get(position).getNumber());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
 