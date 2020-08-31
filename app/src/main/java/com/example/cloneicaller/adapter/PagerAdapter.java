package com.example.cloneicaller.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cloneicaller.fragment.FragmentCallKeyboard;
import com.example.cloneicaller.fragment.FragmentListBlock;
import com.example.cloneicaller.fragment.FragmentListHistory;
import com.example.cloneicaller.fragment.FragmentSetting;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        final Fragment result;
        switch (position){
            case 0:
                result = new FragmentCallKeyboard();
                break;
            case 1:
                result = new FragmentListHistory();
                break;
            case 2:
                result = new FragmentListBlock();
                break;
            default:
                result = new FragmentSetting();
                break;
        }
        return result;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
