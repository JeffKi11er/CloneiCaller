package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.Holder.BlockListHolder;
import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.ItemBlockListBinding;
import com.example.cloneicaller.databinding.ItemQuestionBinding;

public class BlockListAdapter extends RecyclerView.Adapter<BlockListHolder> {

    Context context;

    @NonNull
    @Override
    public BlockListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlockListHolder(ItemBlockListBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlockListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
