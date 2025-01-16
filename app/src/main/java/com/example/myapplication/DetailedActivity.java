package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.databinding.ActivityDetailedBinding;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String time = intent.getStringExtra("time");
            String ingredients = intent.getStringExtra("ingredients");
            String description = intent.getStringExtra("description");
            String image = intent.getStringExtra("image"); // Image URL

            // Set text values
            binding.detailName.setText(name);
            binding.detailTime.setText(time);
            binding.detailIngredients.setText(ingredients != null ? ingredients : "Ingredients not available");
            binding.detailDesc.setText(description != null ? description : "Description not available");

            // Load image from URL using Picasso
            Picasso.get().load(image).into(binding.detailImage);
        }

        // Set up the back button
        binding.backButton.setOnClickListener(v -> finish()); // Finish current activity and go back to the previous one
    }
}
