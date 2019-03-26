package com.example.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Create variables
    private Callback activity;
    private Context context;
    ArrayList<String> listCategories = new ArrayList<>();

    // Create callback
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Create constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // This methods gets the categories from a url
    public void getCategories(Callback activity) {

        // Create a RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create jsonObjectRequest
        String url = "https://resto.mprog.nl/categories";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

        // Set the activity
        this.activity = activity;
    }

    // This method is called when there is an error with the volley
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    // This method is called when there is no error with the volley
    @Override
    public void onResponse(JSONObject response) {

        try {
            // Get a JSONArray from the response
            JSONArray categoriesArray = response.getJSONArray("categories");

            // Extract the categories from the JSONArray and add to a list
            for (int i = 0; i < categoriesArray.length(); i++) {
                listCategories.add(categoriesArray.getString(i));
            }
            activity.gotCategories(listCategories);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}