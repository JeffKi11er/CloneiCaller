package com.example.cloneicaller.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Members {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("group")
    @Expose
    private Integer group;

    @SerializedName("active")
    @Expose
    private boolean active;

    public Members() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
