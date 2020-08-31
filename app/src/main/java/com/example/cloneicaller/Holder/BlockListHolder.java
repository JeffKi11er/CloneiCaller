package com.example.cloneicaller.Holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.databinding.ItemBlockListBinding;

public class BlockListHolder extends RecyclerView.ViewHolder {
    ItemBlockListBinding blockListBinding;
    public BlockListHolder(@NonNull ItemBlockListBinding itemBlockListBinding) {
        super(itemBlockListBinding.getRoot());
        this.blockListBinding = itemBlockListBinding;
    }
}
