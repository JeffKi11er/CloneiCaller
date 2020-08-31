package com.example.cloneicaller.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.cloneicaller.BlockActivity;
import com.example.cloneicaller.CallStateReceiver;
import com.example.cloneicaller.DetailContact;
import com.example.cloneicaller.HomeActivity;
import com.example.cloneicaller.MainActivity;
import com.example.cloneicaller.R;
import com.example.cloneicaller.Room.BlockItemDatabase;
import com.example.cloneicaller.adapter.BlockListItemAdapter;
import com.example.cloneicaller.call.ITelephony;
import com.example.cloneicaller.common.AppConstants;
import com.example.cloneicaller.common.Common;
import com.example.cloneicaller.databinding.FragmentBlackDiaryBinding;
import com.example.cloneicaller.item.BlockerPersonItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FragmentBlackDiary extends Fragment implements BlockListItemAdapter.BlockerItemListener, AppConstants {
    //CallStateReceiver blockUnknownReceiver;
    FragmentBlackDiaryBinding binding;
    SharedPreferences preferencesBlockCall;
    SharedPreferences preferencesBlockLier;
    SharedPreferences preferencesBlockAdvertise;
    private String number;
    private List<BlockerPersonItem>items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentBlackDiaryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }
    public static void endCall(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            ITelephony telephonyService;
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                Method m = tm.getClass().getDeclaredMethod("getITelephony");

                m.setAccessible(true);
                telephonyService = (ITelephony) m.invoke(tm);
                telephonyService.endCall();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            TelecomManager tm = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
            tm.endCall();
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
 //       String number = CallStateReceiver.incommingNumber;
//        Toast.makeText(getContext(),"FragmentBlack"+number,Toast.LENGTH_LONG).show();
//        Log.e("FragmentBlack","has call:"+number);
        preferencesBlockLier = getContext().getSharedPreferences("blockLierCall",Context.MODE_PRIVATE);
        preferencesBlockAdvertise = getContext().getSharedPreferences("blockAdvertiseCall",Context.MODE_PRIVATE);
        preferencesBlockCall = getContext().getSharedPreferences("blockUnknownCall",Context.MODE_PRIVATE);
        binding.swUnknown.setChecked(preferencesBlockCall.getBoolean("checked",false));
        binding.swLieOwe.setChecked(preferencesBlockLier.getBoolean("checked",false));
        binding.swAdvertise.setChecked(preferencesBlockAdvertise.getBoolean("checked",false));

        BlockItemDatabase database = Room.databaseBuilder(getContext().getApplicationContext(),BlockItemDatabase.class,
                "blockItems")
                .allowMainThreadQueries()
                .build();
        binding.btnFloatingAddToBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), BlockActivity.class));
            }
        });

        binding.btnUpdateListBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        binding.swUnknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.swUnknown.isChecked()){
                    SharedPreferences.Editor editor = preferencesBlockCall.edit();
                    editor.putBoolean("checked",true);
                    editor.commit();
                }else if(!binding.swUnknown.isChecked()){
                    SharedPreferences.Editor editor = preferencesBlockCall.edit();
                    editor.remove("checked");
                    editor.commit();
                }
                if (binding.swAdvertise.isChecked()){
                    SharedPreferences.Editor editor = preferencesBlockAdvertise.edit();
                    editor.putBoolean("checked",true);
                    editor.commit();
                }else if(!binding.swAdvertise.isChecked()){
                    SharedPreferences.Editor editor = preferencesBlockAdvertise.edit();
                    editor.remove("checked");
                    editor.commit();
                }
                if (binding.swLieOwe.isChecked()){
                    SharedPreferences.Editor editor = preferencesBlockLier.edit();
                    editor.putBoolean("checked",true);
                    editor.commit();
                }else if(!binding.swLieOwe.isChecked()){
                    SharedPreferences.Editor editor = preferencesBlockLier.edit();
                    editor.remove("checked");
                    editor.commit();
                }
            }
        });
        items = database.getItemDao().getItems();
        if (items.size()>1) {
            items = Common.sortBlockList(items);
            items = Common.addAlphabetBlocker(items);
        }
        BlockListItemAdapter adapter = new BlockListItemAdapter(items,getContext());
        binding.rcBlockList.setAdapter(adapter);
        adapter.setListener(this);
    }

        private BroadcastReceiver blockUnknownReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state =  intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            Log.e("AAAA", "state");
            String incomingNumber =intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            List<BlockerPersonItem>blockerItem = Common.checkRealDialer(items);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(getContext(),"The incoming number is : "+incomingNumber,Toast.LENGTH_LONG).show();
                if ((incomingNumber != null)) {
                    if(!Common.checkUnknown(incomingNumber,context)&& binding.swUnknown.isChecked()){
                        Log.e("Broadcast","Unknown?Checked!");
                        Toast.makeText(getContext(),"Unknown?Checked !",Toast.LENGTH_LONG).show();
                        CallStateReceiver.endCall(context);
                    }
                    if(Common.checkInside(incomingNumber,Common.checkLier(blockerItem))&&binding.swLieOwe.isChecked()){
                        Log.e("Broadcast","Lie or Owe?Checked!");
                        Toast.makeText(getContext(),"Lie or Owe?Checked !",Toast.LENGTH_LONG).show();
                        CallStateReceiver.endCall(context);
                    }
                    if (Common.checkInside(incomingNumber,Common.checkAdvertise(blockerItem))&&binding.swAdvertise.isChecked()){
                        Log.e("Broadcast","Advertised?Checked!");
                        Toast.makeText(getContext(),"Advertised?Checked !",Toast.LENGTH_LONG).show();
                        CallStateReceiver.endCall(context);
                    }
                }
//                number = incomingNumber;
            }
        }
    };
    @Override
    public void onStart() {
        super.onStart();
//        if(binding.swUnknown.isChecked()){
////            if(Common.checkUnknown(number,getContext())){
//                Log.e("FragmentBlack","check faint");
//                endCall(getContext());
////            }
//        }
       // blockUnknownReceiver = new CallStateReceiver();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PHONE_STATE");
        intentFilter.setPriority(100);
        getActivity().registerReceiver(blockUnknownReceiver,intentFilter);
    }

//    private void init() {
//        receiver = new CallStateReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.intent.action.PHONE_STATE");
//        getActivity().registerReceiver(receiver,filter);
//    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(blockUnknownReceiver);
    }

    @Override
    public void onClickBlocker(int position) {
        Intent intent = new Intent(getActivity(), DetailContact.class);
//        Bundle bundle = new Bundle();
        intent.putExtra(INTENT_NAME,items.get(position).getName());
        intent.putExtra(INTENT_NUMBER,items.get(position).getNumber());
        intent.putExtra(INTENT_BLOCK,true);
        intent.putExtra(INTENT_BLOCK_TYPE,items.get(position).getType());
//        bundle.putString(INTENT_NAME,items.get(position).getName());
//        bundle.putString(INTENT_NUMBER,items.get(position).getNumber());
//        bundle.putEx;
//        intent.putExtras(bundle);
        startActivity(intent);
    }
}
