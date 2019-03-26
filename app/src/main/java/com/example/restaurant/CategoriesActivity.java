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

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    // Create an instance of listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Get categories using the CategoriesRequest class
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);

        listView = findViewById(R.id.listView);

        // If the listView is clicked go to CategoryClickListener
        listView.setOnItemClickListener(new CategoryClickListener());
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // If the categories are loaded set adapter to the listView
        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.entry_row, categories);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {

        // If there was a problem loading the categories display the error
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class CategoryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Create the intent
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);

            // Get the category that was pressed and add it to the intent
            TextView category = view.findViewById(R.id.category);
            intent.putExtra("category", category.getText());

            // Start new activity
            startActivity(intent);
        }
    }
}

