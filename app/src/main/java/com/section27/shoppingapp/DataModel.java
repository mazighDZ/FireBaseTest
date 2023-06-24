package com.section27.shoppingapp;

public class DataModel {

private String userName;
private String password;

/** Default Constructor is required in FireBase  , for calls to DataSnapshot.getValue(MyClass)**/
    public DataModel() {
    }

    public DataModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
