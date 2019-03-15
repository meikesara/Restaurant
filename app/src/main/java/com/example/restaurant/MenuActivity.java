package com.example.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequests.Callback {

    ListView listView = findViewById(R.id.menuView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Get intent and retrieve the category
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // Get the menu items using the MenuItemsRequests class
        MenuItemsRequests x = new MenuItemsRequests(this, category);
        x.getMenuItems(this);

        // If the listView is clicked go to CategoryClickListener
        listView.setOnItemClickListener(new MenuItemClickListener());

    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {

        // If the menu items are loaded set adapter to the listView
        MenuItemsAdapter adapter = new MenuItemsAdapter(this, R.layout.entry_menu, menuItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {

        // If there was a problem loading the categories display the error
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class MenuItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Create the intent
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);

            // Add the clicked MenuItem to the intent
            MenuItem menuItem = (MenuItem) parent.getItemAtPosition(position);
            intent.putExtra("item", menuItem);

            // Start the activity
            startActivity(intent);
        }
    }
}
