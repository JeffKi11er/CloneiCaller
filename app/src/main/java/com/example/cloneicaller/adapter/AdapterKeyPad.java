package com.example.cloneicaller.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public class AdapterKeyPad extends RecyclerView.Adapter<AdapterKeyPad.MyHolder> {
    @NonNull
    @Override
    public AdapterKeyPad.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKeyPad.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
