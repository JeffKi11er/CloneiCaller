package com.example.cloneicaller.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneicaller.DetailDiaryActivity;
import com.example.cloneicaller.Holder.HeaderListHistory;
import com.example.cloneicaller.Holder.ListHistoryHolder;
import com.example.cloneicaller.ListItem;
import com.example.cloneicaller.Models.Contact;
import com.example.cloneicaller.Models.DateItem;
import com.example.cloneicaller.Models.GeneralItem;
import com.example.cloneicaller.R;
import com.example.cloneicaller.SwipeHelper;

import java.util.List;

public class ListHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Thanhnv

    Context context;
    List<ListItem> listItemList;
    public static String outgoingNumber = "";

    public ListHistoryAdapter(Context context, List<ListItem> listItemList) {
        this.context = context;
        this.listItemList = listItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_GENERAL:
                View v1 = inflater.inflate(R.layout.item_contact, parent,
                        false);
                viewHolder = new ListHistoryHolder(v1);
                break;

            case ListItem.TYPE_DATE:
                View v2 = inflater.inflate(R.layout.item_sortbydate, parent, false);
                viewHolder = new HeaderListHistory(v2);
                break;
        }

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:

                GeneralItem generalItem = (GeneralItem) listItemList.get(position);
                ListHistoryHolder listHistoryHolder = (ListHistoryHolder) holder;

                if (generalItem.getContact().getName() == null) {
                    listHistoryHolder.tvNameContact.setText(generalItem.getContact().getNumber() + "");
                } else {
                    listHistoryHolder.tvNameContact.setText(generalItem.getContact().getName());
                }
                if (generalItem.getContact().getType() == "OUTGOING") {
                    listHistoryHolder.imgCall.setImageResource(R.drawable.ic_out_call);
                } else if (generalItem.getContact().getType() == "INCOMING") {
                    listHistoryHolder.imgCall.setImageResource(R.drawable.ic_in_call);
                } else {
                    listHistoryHolder.imgCall.setImageResource(R.drawable.ic_miss_call);
                }

                listHistoryHolder.tvTimeCall.setText(generalItem.getContact().getDuration());

                listHistoryHolder.imgDetailCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DetailDiaryActivity.class);
                        intent.putExtra("number", generalItem.getContact().getNumber());
                        intent.putExtra("duration", generalItem.getContact().getDuration());
                        intent.putExtra("date", generalItem.getContact().getDate());
                        intent.putExtra("type", generalItem.getContact().getType());
                        intent.putExtra("name", generalItem.getContact().getName());
                        intent.putExtra("country", generalItem.getContact().getCountry());
                        intent.putExtra("numbertype", generalItem.getContact().getNetwork());
                        context.startActivity(intent);
                    }
                });
                listHistoryHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + generalItem.getContact().getNumber() + ""));
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        }
                        outgoingNumber = generalItem.getContact().getNumber();
                        Log.e("ABC",outgoingNumber);
                        context.startActivity(intent);
                    }
                });


                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) listItemList.get(position);
                HeaderListHistory headerListHistory = (HeaderListHistory) holder;

                headerListHistory.tvDate.setText(dateItem.getDate());

                break;
        }

    }


    @Override
    public int getItemCount() {
        return listItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listItemList.get(position).getType();

    }

}
