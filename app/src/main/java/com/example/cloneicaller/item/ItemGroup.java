package com.example.cloneicaller.item;

import java.util.ArrayList;

public class ItemGroup {
    private ArrayList<String> person;
    private String name;

    public ItemGroup(ArrayList<String> person, String name) {
        this.person = person;
        this.name = name;
    }

    public ArrayList<String> getPerson() {
        return person;
    }

    public void setPerson(ArrayList<String> person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
