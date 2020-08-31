package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.Models.Question;
import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.ItemQuestionBinding;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {

    Context context;
    List<Question> questionList;
    public Integer num = 1;

    public QuestionAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionHolder(ItemQuestionBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        Question question = questionList.get(position);

        holder.binding.tvNumQuestion.setText(num++ + ".");
        holder.binding.tvNameQuestion.setText(question.getQuestion());
        holder.binding.tvAnswer.setText(question.getAnswer());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.binding.tvAnswer.getVisibility() == View.GONE) {
                    holder.binding.imgQuestion.setImageResource(R.drawable.ic_reduce);
                    holder.binding.tvAnswer.setVisibility(View.VISIBLE);
                } else {
                    holder.binding.tvAnswer.setVisibility(View.GONE);
                    holder.binding.imgQuestion.setImageResource(R.drawable.ic_add_new);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
