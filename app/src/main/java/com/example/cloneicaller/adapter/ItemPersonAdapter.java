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
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.GroupItemBinding;
import com.example.cloneicaller.databinding.PersonItemBinding;
import com.example.cloneicaller.item.ItemPerson;

import java.util.ArrayList;
import java.util.List;

public class ItemPersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ItemPerson>personList;
    private PersonItemListener listener;

    public ItemPersonAdapter(Context context, List<ItemPerson> personList) {
        this.context = context;
        this.personList = personList;
    }

    public void setListener(PersonItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Common.VIEW_TYPE_GROUP){
            return new GroupHolder(GroupItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        }
        else if(viewType == Common.VIEW_TYPE_PERSON){
            return new PersonHolder(PersonItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        }
        else{
            return new GroupHolder(GroupItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GroupHolder){
            GroupHolder groupHolder = (GroupHolder)holder;
            groupHolder.groupItemBinding.tvGroupList.setText(personList.get(position).getName());
        }
        else if(holder instanceof PersonHolder){
            PersonHolder personHolder = (PersonHolder) holder;
            personHolder.personItemBinding.tvPersonTitle.setText(personList.get(position).getName());
            personHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onClickPerson(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder{
        PersonItemBinding personItemBinding;

        public PersonHolder(@NonNull PersonItemBinding personItemBinding) {
            super(personItemBinding.getRoot());
            this.personItemBinding = personItemBinding;
        }
    }
    public class GroupHolder extends RecyclerView.ViewHolder{
        GroupItemBinding groupItemBinding;

        public GroupHolder(@NonNull GroupItemBinding groupItemBinding) {
            super(groupItemBinding.getRoot());
            this.groupItemBinding = groupItemBinding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return personList.get(position).getViewType();
    }
    public interface PersonItemListener{
        void onClickPerson(int position);
    }
    public void filterList(ArrayList<ItemPerson>arrayList){
        personList = arrayList;
        notifyDataSetChanged();
    }
}
