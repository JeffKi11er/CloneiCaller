package com.example.cloneicaller.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cloneicaller.fragment.FragmentBlackDiary;
import com.example.cloneicaller.fragment.FragmentDiary;

public class PageBlockerAdapter extends FragmentPagerAdapter {
    public PageBlockerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        final Fragment result;
        switch (position){
            case 0:
                result = new FragmentDiary();
                break;
            default:
                result = new FragmentBlackDiary();
                break;
        }
        return result;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "DANH BẠ";
        }
        return "DANH SÁCH CHẶN";
    }
}
