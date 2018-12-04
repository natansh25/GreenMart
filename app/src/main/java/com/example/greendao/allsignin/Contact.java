package com.example.greendao.allsignin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    private Contact ResponseData;

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("IsUpdatedOn")
    @Expose
    private Object isUpdatedOn;
    @SerializedName("IsActive")
    @Expose
    private Object isActive;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getIsUpdatedOn() {
        return isUpdatedOn;
    }

    public void setIsUpdatedOn(Object isUpdatedOn) {
        this.isUpdatedOn = isUpdatedOn;
    }

    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        this.isActive = isActive;
    }

    public Contact getResponseData() {
        return ResponseData;
    }

    public void setResponseData(Contact responseData) {
        ResponseData = responseData;
    }
}
