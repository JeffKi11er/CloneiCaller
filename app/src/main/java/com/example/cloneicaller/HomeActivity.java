package com.example.cloneicaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.cloneicaller.Models.Data;
import com.example.cloneicaller.Models.Datas;
import com.example.cloneicaller.Models.RootData;
import com.example.cloneicaller.Room.PhoneDB;
import com.example.cloneicaller.Room.PhoneDBQueryTask;
import com.example.cloneicaller.auth.RetrofitClient;
import com.example.cloneicaller.databinding.ActivityHomeBinding;
import com.example.cloneicaller.fragment.FragmentCallKeyboard;
import com.example.cloneicaller.fragment.FragmentDiary;
import com.example.cloneicaller.fragment.FragmentHome;
import com.example.cloneicaller.fragment.FragmentListBlock;
import com.example.cloneicaller.fragment.FragmentListHistory;
import com.example.cloneicaller.fragment.FragmentSetting;
import com.example.cloneicaller.item.ItemPerson;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, FragmentDiary.FragmentDiaryListner, Callback<String> {
    private FragmentCallKeyboard fragmentCallKeyboard = new FragmentCallKeyboard();
    private FragmentListBlock fragmentListBlock = new FragmentListBlock();
    private FragmentListHistory fragmentListHistory = new FragmentListHistory();
    private FragmentSetting fragmentSetting = new FragmentSetting();
    private ArrayList<ItemPerson> persons = new ArrayList<>();
    private DataReceiverListener listener;
    private Bundle bundle = new Bundle();

    ActivityHomeBinding binding;

    PhoneDB phoneDB;

    String data;
    String jsonPhone;
    Integer LIMIT = 50;
    String SELECT = "id,code,phone,name,warn_type,updated_at";

    public ArrayList<ItemPerson> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<ItemPerson> persons) {
        this.persons = persons;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        getSupportFragmentManager().beginTransaction().replace(binding.containerView.getId(),
                new FragmentHome()).commit();


        RetrofitClient.getInstance().getData(LIMIT, ">2020-01-01", 1, 1, SELECT, "id", "DESC").enqueue(HomeActivity.this);

//        Log.e("ABC", jsonPhone+"");

//        PhoneDBQueryTask phoneDBQueryTask = new PhoneDBQueryTask(this);
//        phoneDBQueryTask.insertData(new PhoneDBQueryTask.OnQuery<long[]>() {
//            @Override
//            public void onResult(long[] longs) {
//
//            }
//        });

    }


    public static String decryptPhoneDB(String key, String data) {
        try {
            //128 bits
            int CIPHER_KEY_LEN = 16;
            if (key.length() < CIPHER_KEY_LEN) {
                int numPad = CIPHER_KEY_LEN - key.length();
                StringBuilder keyBuilder = new StringBuilder(key);
                for (int i = 0; i < numPad; i++) {
                    keyBuilder.append("0"); //0 pad to len 16 bytes
                }
                key = keyBuilder.toString();
            } else if (key.length() > CIPHER_KEY_LEN) {
                key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }

            String[] parts = data.split(":");
            IvParameterSpec iv = new IvParameterSpec(Base64.decode(parts[parts.length - 1], Base64.DEFAULT));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.ISO_8859_1), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                builder.append(parts[i]);
            }
            byte[] decodedEncryptedData = android.util.Base64.decode(builder.toString(), Base64.DEFAULT);
            byte[] original = cipher.doFinal(decodedEncryptedData);
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
//    private BottomNavigationView.OnNavigationItemSelectedListener botListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            Fragment selectedFragment = null;
//            switch (menuItem.getItemId()){
//                case R.id.menu_keyboard:
//                    selectedFragment = new FragmentCallKeyboard();
//                    break;
//                case R.id.menu_history:
//                    selectedFragment = new FragmentListHistory();
//                    break;
//                case R.id.menu_block:
//                    selectedFragment = new FragmentListBlock();
//                    break;
//                case R.id.menu_setting:
//                    selectedFragment = new FragmentSetting();
//                    break;
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_view,selectedFragment).commit();
//            return true;
//        }
//    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_pad:
                getSupportFragmentManager().beginTransaction().replace(binding.containerView.getId(), fragmentCallKeyboard).commit();
                break;
            case R.id.img_history:
                getSupportFragmentManager().beginTransaction().replace(binding.containerView.getId(), fragmentListHistory).commit();
                break;
            case R.id.img_block:
                fragmentListBlock.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(binding.containerView.getId(), fragmentListBlock).commit();
                break;
            case R.id.img_setting:
        }
    }

    @Override
    public void onPutListDiarySent(ArrayList<ItemPerson> people) {
//        for (int i = 0; i < people.size(); i++) {
//            persons.add(people.get(i));
//        }
        bundle.putSerializable("ModelList", people);
        fragmentListBlock.setArguments(bundle);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.e("ABC", response.body() + "");
        data = response.body();
        String key = "0123456789abcdef";
        jsonPhone = decryptPhoneDB(key, data);
        Log.e("TAG", decryptPhoneDB(key, data));
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Log.e("ABC", t.getMessage() + "");
    }


    public interface DataReceiverListener {
        void onReceived(int requestCode, int resultCode, Intent data);
    }

    public void setListener(DataReceiverListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (listener != null) {
            int request = 101;
            int result = 2;
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("ModelList", persons);
            intent.putExtras(bundle);
            listener.onReceived(request, result, intent);
        }
    }
}