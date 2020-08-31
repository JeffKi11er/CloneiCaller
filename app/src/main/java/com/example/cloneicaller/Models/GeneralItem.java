package com.example.cloneicaller.Models;

import com.example.cloneicaller.ListItem;

public class GeneralItem implements ListItem {

    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public int getType() {
        return TYPE_GENERAL;
    }
}
