package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.GroupItemBinding;
import com.example.cloneicaller.databinding.ItemBlockListBinding;
import com.example.cloneicaller.databinding.ItemBlockedPeopleBinding;
import com.example.cloneicaller.item.BlockTypeItem;
import com.example.cloneicaller.item.BlockerPersonItem;

import java.util.ArrayList;
import java.util.List;

public class BlockListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BlockerPersonItem> blockTypeItems;
    private Context context;
    private BlockerItemListener listener;

    public BlockListItemAdapter(List<BlockerPersonItem> blockTypeItems, Context context) {
        this.blockTypeItems = blockTypeItems;
        this.context = context;
    }

    public void setListener(BlockerItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Common.VIEW_TYPE_GROUP){
            return new GroupBlockerHolder(GroupItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent,false));
        }
        else if(viewType == Common.VIEW_TYPE_PERSON){
            return new BlockPersonHolder(ItemBlockedPeopleBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent,false));
        }
        else {
            return new BlockPersonHolder(ItemBlockedPeopleBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        holder.itemBlockListBinding.tvBlockedName.setText(blockTypeItems.get(position).getName());
//        holder.itemBlockListBinding.tvBlockedWork.setText(blockTypeItems.get(position).getType());
//        holder.itemBlockListBinding.imgBlockedPerson.setImageResource(blockTypeItems.get(position).getImage());
        if (holder instanceof GroupBlockerHolder) {
            GroupBlockerHolder groupBlockerHolder = (GroupBlockerHolder) holder;
            groupBlockerHolder.groupItemBinding.tvGroupList.setText(blockTypeItems.get(position).getName());
        }else if(holder instanceof BlockPersonHolder){
            BlockPersonHolder blockPersonHolder = (BlockPersonHolder)holder;
            blockPersonHolder.itemBlockListBinding.tvBlockedName.setText(blockTypeItems.get(position).getName());
            blockPersonHolder.itemBlockListBinding.tvBlockedWork.setText(blockTypeItems.get(position).getType());
            blockPersonHolder.itemBlockListBinding.imgBlockedPerson.setImageResource(blockTypeItems.get(position).getImage());
            blockPersonHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onClickBlocker(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return blockTypeItems.size();
    }

    public class BlockPersonHolder extends RecyclerView.ViewHolder{
        ItemBlockedPeopleBinding itemBlockListBinding;

        public BlockPersonHolder(@NonNull ItemBlockedPeopleBinding itemBlockListBinding) {
            super(itemBlockListBinding.getRoot());
            this.itemBlockListBinding = itemBlockListBinding;
        }
    }
    public class GroupBlockerHolder extends RecyclerView.ViewHolder{
        GroupItemBinding groupItemBinding;

        public GroupBlockerHolder(@NonNull GroupItemBinding groupItemBinding) {
            super(groupItemBinding.getRoot());
            this.groupItemBinding = groupItemBinding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return blockTypeItems.get(position).getTypeArrange();
    }

    public interface BlockerItemListener{
        void onClickBlocker(int position);
    }
}
