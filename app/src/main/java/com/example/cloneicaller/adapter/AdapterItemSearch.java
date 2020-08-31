package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.ItemQuestionBinding;
import com.example.cloneicaller.databinding.PersonItemBinding;
import com.example.cloneicaller.item.ItemPerson;

import java.util.ArrayList;

public class AdapterItemSearch extends RecyclerView.Adapter<AdapterItemSearch.PersonSearchItem> {
    private Context context;
    private ArrayList<ItemPerson>people;
    private SearchClickListener listener;
    public AdapterItemSearch(Context context, ArrayList<ItemPerson> people) {
        this.context = context;
        this.people = people;
    }

    public void setListener(SearchClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonSearchItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonSearchItem(PersonItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonSearchItem holder, int position) {
        holder.personItemBinding.tvPersonTitle.setText(people.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onSearchClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class PersonSearchItem extends RecyclerView.ViewHolder {
        PersonItemBinding personItemBinding;
        public PersonSearchItem(@NonNull PersonItemBinding personItemBinding) {
            super(personItemBinding.getRoot());
            this.personItemBinding = personItemBinding;
        }
    }
    public interface SearchClickListener{
        void onSearchClickListener(int position);
    }
    public void filterList(ArrayList<ItemPerson>arrayList){
        people = arrayList;
        notifyDataSetChanged();
    }
}
