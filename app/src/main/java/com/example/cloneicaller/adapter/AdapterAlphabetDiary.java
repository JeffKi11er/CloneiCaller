package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.ItemListDiaryBinding;
import com.example.cloneicaller.databinding.ItemQuestionBinding;
import com.example.cloneicaller.item.ItemGroup;
import com.example.cloneicaller.item.ItemPerson;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdapterAlphabetDiary extends RecyclerView.Adapter<AdapterAlphabetDiary.AlphaHolder> implements AdapterPersonDiary.ItemClicker{
    private Context context;
    private ArrayList<ItemGroup> arrayListGroup;
    private ArrayList<String>itemPeople;

    public AdapterAlphabetDiary(Context context, ArrayList<ItemGroup> arrayListGroup) {
        this.context = context;
        this.arrayListGroup = arrayListGroup;
    }
    @NonNull
    @Override
    public AlphaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlphaHolder(ItemListDiaryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlphaHolder holder, int position) {
        holder.itemListDiaryBinding.tvFirstAlphabet.setText(arrayListGroup.get(position).getName());
        for (int i = 0; i < arrayListGroup.get(position).getPerson().size(); i++) {

        }
        AdapterPersonDiary personAdapter = new AdapterPersonDiary(context,itemPeople);
        holder.itemListDiaryBinding.rclItemDiary.setAdapter(personAdapter);
        personAdapter.setListener(this);
    }

    @Override
    public int getItemCount() {
        return arrayListGroup.size();
    }

    @Override
    public void onClickListener(int position) {
        Toast.makeText(context,itemPeople.get(position),Toast.LENGTH_LONG).show();
    }

    public static class AlphaHolder extends RecyclerView.ViewHolder{
        ItemListDiaryBinding itemListDiaryBinding;
        public AlphaHolder(@NonNull ItemListDiaryBinding itemListDiaryBinding) {
            super(itemListDiaryBinding.getRoot());
            this.itemListDiaryBinding = itemListDiaryBinding;
        }
    }
    public interface GroupOnClick{
        void onGroupClickListener(int position);
    }
}
