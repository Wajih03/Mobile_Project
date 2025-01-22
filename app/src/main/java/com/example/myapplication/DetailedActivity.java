package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import this to use Log
import com.example.myapplication.databinding.ActivityDetailedBinding;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve intent data
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String time = intent.getStringExtra("time");
            String ingredients = intent.getStringExtra("ingredients");
            String description = intent.getStringExtra("description");
            String image = intent.getStringExtra("image"); // Image URL

            // Log the image URL for debugging
            Log.d("DetailedActivity", "Image URL: " + image);

            // Bind data to UI elements
            binding.detailName.setText(name != null ? name : "Name not available");
            binding.detailTime.setText(time != null ? time : "Time not available");
            binding.detailIngredients.setText(ingredients != null ? ingredients : "Ingredients not available");
            binding.detailDesc.setText(description != null ? description : "Description not available");

            // Load image with Picasso
            if (image != null && !image.isEmpty()) {
                Picasso.get()
                        .load(image)
                        .placeholder(R.drawable.aklogo) // Placeholder while loading
                        .error(R.drawable.pancake)       // Image on error
                        .into(binding.detailImage);
            } else {
                binding.detailImage.setImageResource(R.drawable.headerbkg); // Default image
            }
        }

        // Back button functionality
        binding.backButton.setOnClickListener(v -> finish());
    }
}
