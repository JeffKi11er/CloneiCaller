package com.example.cloneicaller.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;

public class HeaderListHistory extends RecyclerView.ViewHolder {

    public TextView tvDate;
    public HeaderListHistory(@NonNull View itemView) {
        super(itemView);
        tvDate = itemView.findViewById(R.id.tvDate);
    }
}
