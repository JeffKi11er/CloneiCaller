package com.example.cloneicaller.item;

public class ItemPerson  {
    private String name,number;
    private int viewType;

    public ItemPerson() {
    }

    public ItemPerson(String name, int viewType,String number) {
        this.name = name;
        this.viewType = viewType;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
