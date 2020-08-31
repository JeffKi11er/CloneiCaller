package com.example.cloneicaller.item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "blockitems")
public class BlockerPersonItem {
    public BlockerPersonItem(String name, String type, String number, int image, int typeArrange) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.image = image;
        this.typeArrange = typeArrange;
    }
    public BlockerPersonItem() {
    }
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "image")
    private int image;

    private int typeArrange;

    public int getTypeArrange() {
        return typeArrange;
    }

    public void setTypeArrange(int typeArrange) {
        this.typeArrange = typeArrange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
