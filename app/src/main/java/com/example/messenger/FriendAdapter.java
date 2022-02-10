package com.example.messenger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendAdapter extends BaseAdapter {
    Context context;
    ArrayList<FriendItem> items;

    public FriendAdapter(Context context, ArrayList<FriendItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.freindrow, null);
        }

        FriendItem people = (FriendItem) getItem(position);
        ImageView image = convertView.findViewById(R.id.image);
        TextView name = convertView.findViewById(R.id.name);
        TextView stateMessege = convertView.findViewById(R.id.stateMessege);

        image.setImageResource(people.getUser());
        name.setText(people.getName());
        stateMessege.setText(people.getState());

        return convertView;
    }
}
