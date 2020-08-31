package com.example.cloneicaller.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cloneicaller.Models.DataModel;

// khởi tạo cơ sở dữ liệu với bảng Data
@Database(entities = {DataModel.DataBeanX.DataBean.class}, version = 1, exportSchema = false)
public abstract class PhoneDB extends RoomDatabase {
    public static String DB_NAME = "databean_db";
    public static PhoneDB instance;

    public static synchronized PhoneDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PhoneDB.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract PhoneDBDAO phoneDBDAO();
}
