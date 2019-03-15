package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter {

    // Create variable listCategories
    ArrayList<String> listCategories;

    // Create constructor
    public CategoriesAdapter(Context context, int resource, ArrayList<String> listCategories) {
        super(context, resource, listCategories);
        this.listCategories = listCategories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Load item if it has not been loaded before
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_row, parent, false);
        }

        // Get the category from the list
        String category = listCategories.get(position);

        // Set the text of the textview
        TextView categoryView = convertView.findViewById(R.id.category);
        categoryView.setText(category);

        return convertView;
    }
}