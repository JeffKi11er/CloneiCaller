package com.example.cloneicaller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cloneicaller.R;
import com.example.cloneicaller.adapter.PagerAdapter;
import com.example.cloneicaller.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class FragmentHome extends Fragment {

    FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        setUpTab();
        return view;
    }

    private void setUpTab() {
        binding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_dial_hover);
        binding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
        binding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_blocking);
        binding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_setting);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        binding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_dial_hover);
                        binding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
                        binding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_blocking);
                        binding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_setting);
                        break;
                    case 1:
                        binding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_pad);
                        binding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history_hover);
                        binding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_blocking);
                        binding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_setting);
                        break;
                    case 2 :
                        binding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_pad);
                        binding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
                        binding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_blocking_hover);
                        binding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_setting);
                        break;
                    case 3:
                        binding.tabLayout.getTabAt(0).setIcon(R.drawable.ic_pad);
                        binding.tabLayout.getTabAt(1).setIcon(R.drawable.ic_history);
                        binding.tabLayout.getTabAt(2).setIcon(R.drawable.ic_blocking);
                        binding.tabLayout.getTabAt(3).setIcon(R.drawable.ic_setting_hover);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
