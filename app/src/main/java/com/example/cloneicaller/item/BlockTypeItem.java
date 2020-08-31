package com.example.cloneicaller.item;

public class BlockTypeItem {
    private String name;
    private String type;
    private int icon;
    public BlockTypeItem(String name, int icon, String type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
