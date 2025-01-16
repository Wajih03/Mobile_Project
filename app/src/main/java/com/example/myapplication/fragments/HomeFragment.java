package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.DetailedActivity;
import com.example.myapplication.ListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.api.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String BASE_URL = "http://192.168.90.233:5000/";
    private ListAdapter listAdapter;
    private ArrayList<Recipe> dataArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize ListView
        ListView listView = view.findViewById(R.id.listview); // Make sure your fragment_home.xml has a ListView with this ID

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create ApiService instance
        ApiService apiService = retrofit.create(ApiService.class);

        // Make network request to get recipes
        apiService.getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Recipe> recipes = response.body();
                    if (recipes.isEmpty()) {
                        Log.e("HomeFragment", "No recipes found!");
                    } else {
                        dataArrayList.clear();
                        dataArrayList.addAll(recipes);
                        listAdapter = new ListAdapter(getActivity(), dataArrayList);
                        listView.setAdapter(listAdapter);
                    }
                } else {
                    Log.e("HomeFragment", "Error: " + response.code() + " - " + response.message());
                }
            }


            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                // Handle failure case
            }
        });

        // Set item click listener to navigate to DetailedActivity
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Recipe clickedRecipe = dataArrayList.get(i);
            Intent intent = new Intent(getActivity(), DetailedActivity.class);
            intent.putExtra("name", clickedRecipe.getName());
            intent.putExtra("time", clickedRecipe.getTime());
            intent.putExtra("ingredients", clickedRecipe.getIngredients());  // Update this with actual ingredient data
            intent.putExtra("description", clickedRecipe.getDescription());
            intent.putExtra("image", clickedRecipe.getImage()); // Pass image URL here
            startActivity(intent);
        });


        return view;
    }
}
