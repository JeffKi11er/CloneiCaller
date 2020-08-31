package com.example.cloneicaller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.R;
import com.example.cloneicaller.item.BlockTypeItem;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemBlockAdapter extends ArrayAdapter<BlockTypeItem> {
    public ItemBlockAdapter(@NonNull Context context, int resource, @NonNull List<BlockTypeItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_check_type,parent,false);
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.cimg_item);
        TextView tvItem = convertView.findViewById(R.id.tv_item);
        BlockTypeItem itemBlock = getItem(position);
        tvItem.setText(itemBlock.getName());
        imageViewFlag.setImageResource(itemBlock.getIcon());
        return convertView;
    }
}
