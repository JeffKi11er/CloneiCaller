package com.example.cloneicaller.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cloneicaller.item.BlockerPersonItem;

import java.util.List;

@Dao
public interface BlockItemDao {
    @Query("SELECT * FROM blockitems WHERE id = :id")
    BlockerPersonItem getItemById(Long id);
    @Query("SELECT * FROM blockitems")
    List<BlockerPersonItem>getItems();
    @Insert
    void insertAll(BlockerPersonItem... blockerPersonItems);
    @Update
    void updateAll(BlockerPersonItem... blockerPersonItems);
    @Delete
    void deleteAll(BlockerPersonItem... blockerPersonItems);
}
