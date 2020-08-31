package com.example.cloneicaller.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.ItemQuestionBinding;

public class QuestionHolder extends RecyclerView.ViewHolder {

    ItemQuestionBinding binding;


    public QuestionHolder(@NonNull ItemQuestionBinding itemQuestionBinding) {
        super(itemQuestionBinding.getRoot());
        this.binding = itemQuestionBinding;
    }
}
