package com.example.cloneicaller.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cloneicaller.item.BlockerPersonItem;

@Database(entities = {BlockerPersonItem.class}, version = 2)
public abstract class BlockItemDatabase extends RoomDatabase {
    public abstract BlockItemDao getItemDao();
}
