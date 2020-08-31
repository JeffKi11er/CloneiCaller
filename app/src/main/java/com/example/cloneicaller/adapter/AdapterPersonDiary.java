package com.example.cloneicaller.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.DetailDiaryActivity;
import com.example.cloneicaller.R;
import com.example.cloneicaller.item.ItemPerson;

import java.util.ArrayList;
import java.util.List;

public class AdapterPersonDiary extends RecyclerView.Adapter<AdapterPersonDiary.PersonHolder> {
    private Context context;
    private ArrayList<String>itemPeople;
    private ItemClicker listener;

    public AdapterPersonDiary(Context context, ArrayList<String> itemPeople) {
        this.context = context;
        this.itemPeople = itemPeople;
    }
    public void setListener(ItemClicker listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_person_diary,parent,false);
        return new PersonHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, final int position) {
        holder.textView.setText(itemPeople.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemPeople.size();
    }

    public static class PersonHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name_person);
        }
    }
    public interface ItemClicker{
        void onClickListener(int position);
    }
}
