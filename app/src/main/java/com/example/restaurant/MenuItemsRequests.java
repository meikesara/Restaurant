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

public class MenuItemsRequests implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Create variables
    private MenuItemsRequests.Callback activity;
    private Context context;
    private ArrayList<MenuItem> listMenuItems = new ArrayList<>();
    private String category;

    // Create callback
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> categories);
        void gotMenuItemsError(String message);
    }

    // Create constructor
    public MenuItemsRequests(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    // This methods gets the menu items from a url
    public void getMenuItems(MenuItemsRequests.Callback activity) {

        // Create a RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);

        // Create jsonObjectRequests
        String url = "https://resto.mprog.nl/menu?category="+category;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

        // Set the activity
        this.activity = activity;
    }

    // This method is called when there is an error with the volley
    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuItemsError(error.getMessage());
    }

    // This method is called when there is no error with the volley
    @Override
    public void onResponse(JSONObject response) {

        try {
            // Get a JSONArray from the response
            JSONArray menuItemsArray = response.getJSONArray("items");

            // Extract the menu items from the JSONArray and add to a list
            for (int i = 0; i < menuItemsArray.length(); i++) {
                JSONObject item = menuItemsArray.getJSONObject(i);
                MenuItem menuItem = new MenuItem(item.getString("name"),item.getString("description"),
                        item.getString("image_url"), item.getInt("price"), item.getString("category"));
                listMenuItems.add(menuItem);
            }
            activity.gotMenuItems(listMenuItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
