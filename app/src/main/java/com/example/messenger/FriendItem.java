package com.example.messenger;

public class FriendItem  {
    private int user;
    private String name, state;

    public FriendItem(int user, String name, String state) {
        this.name = name;
        this.user = user;
        this.state = state;
    }

    public FriendItem(int user, String name){
        this.name = name;
        this.user = user;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
