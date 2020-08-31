package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;
import com.example.cloneicaller.databinding.ItemBlockedPeopleBinding;
import com.example.cloneicaller.item.BlockerPersonItem;
import com.example.cloneicaller.item.ItemPerson;

import java.util.ArrayList;
import java.util.List;

public class AdapterBlockerSearch extends RecyclerView.Adapter<AdapterBlockerSearch.MyBlockerHolder> {
    private Context context;
    private List<BlockerPersonItem>items;
    private SearchBlockerListener listener;

    public AdapterBlockerSearch(Context context, List<BlockerPersonItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setListener(SearchBlockerListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyBlockerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyBlockerHolder(ItemBlockedPeopleBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyBlockerHolder holder, int position) {
        holder.blockedPeopleBinding.tvBlockedName.setText(items.get(position).getName());
        holder.blockedPeopleBinding.tvBlockedWork.setText(items.get(position).getType());
        holder.blockedPeopleBinding.imgBlockedPerson.setImageResource(items.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onBlockerClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyBlockerHolder extends RecyclerView.ViewHolder{
        ItemBlockedPeopleBinding blockedPeopleBinding;
        public MyBlockerHolder(@NonNull ItemBlockedPeopleBinding blockedPeopleBinding) {
            super(blockedPeopleBinding.getRoot());
            this.blockedPeopleBinding = blockedPeopleBinding;
        }
    }
    public interface SearchBlockerListener{
        void onBlockerClickListener(int position);
    }
    public void filterListB(List<BlockerPersonItem> arrayList){
        items = arrayList;
        notifyDataSetChanged();
    }
}
