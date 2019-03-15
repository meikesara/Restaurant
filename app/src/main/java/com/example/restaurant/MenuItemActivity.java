package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Get the intent and the menu item
        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("item");

        // Set the text and image
        TextView nameView = findViewById(R.id.nameItem);
        TextView priceView = findViewById(R.id.priceItem);
        ImageView imageView = findViewById(R.id.imageItem);
        TextView descriptionView = findViewById(R.id.descriptionItem);

        nameView.setText(menuItem.getName());
        priceView.setText("\u20ac" + String.valueOf(menuItem.getPrice()));
        descriptionView.setText(menuItem.getDescription());
        Picasso.with(this).load(menuItem.getImageUrl()).into(imageView);


    }
}
