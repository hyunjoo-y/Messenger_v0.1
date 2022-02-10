package com.example.messenger;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

class Data{
    private Drawable icon ;
    private String name, chat;

    Data(Drawable icon, String name, String chat){
        this.icon = icon;
        this.chat = chat;
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}

public class ChatListAdapter extends ArrayAdapter<Data> {
    private LayoutInflater layoutInflater;

    public ChatListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

}
