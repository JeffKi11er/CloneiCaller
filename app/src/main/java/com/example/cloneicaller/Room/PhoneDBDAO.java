package com.example.cloneicaller.Room;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cloneicaller.Models.DataModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PhoneDBDAO {

    @Query("SELECT * FROM databean")
    List<DataModel.DataBeanX.DataBean> getAll();

    @Query("SELECT * FROM DataBean WHERE phone =:phone")
    DataModel.DataBeanX.DataBean getDataByPhone(String phone);


    //     thêm 1 hoặc nhiều User
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<DataModel.DataBeanX.DataBean> dataBeanList);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updatePhone(List<DataModel.DataBeanX.DataBean> dataBeanList);

}

