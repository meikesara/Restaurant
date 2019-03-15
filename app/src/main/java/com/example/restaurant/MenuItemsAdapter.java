package com.example.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuItemsAdapter extends ArrayAdapter {

    //Create variable listMenuItems
    ArrayList<MenuItem> listMenuItems;

    // Create constructor
    public MenuItemsAdapter(Context context, int resource, ArrayList<MenuItem> listMenuItems) {
        super(context, resource, listMenuItems);
        this.listMenuItems = listMenuItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Load item if it has not been loaded before
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_menu, parent, false);
        }

        // Get the menu item from the list
        MenuItem menuItem = listMenuItems.get(position);

        // Set the the text and images
        TextView nameView = convertView.findViewById(R.id.name);
        TextView priceView = convertView.findViewById(R.id.price);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        nameView.setText(menuItem.getName());
        priceView.setText("\u20ac" + String.valueOf(menuItem.getPrice()));
        Picasso.with(getContext()).load(menuItem.getImageUrl()).into(imageView);

        return convertView;
    }
}
