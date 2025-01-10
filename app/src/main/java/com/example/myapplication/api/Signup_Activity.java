package com.example.myapplication.api;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_Activity extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                // Check for empty fields
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Signup_Activity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a user object
                HelperClass helperClass = new HelperClass(name, email, username, password);

                // Make the API call using Retrofit
                ApiService apiService = RetrofitClient.getInstance().getApiService();
                Call<ApiResponse> call = apiService.signup(helperClass);

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            // Show success message
                            Toast.makeText(Signup_Activity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
                            // Navigate to login activity
                            Intent intent = new Intent(Signup_Activity.this, Login_Activity.class);
                            startActivity(intent);
                        } else {
                            // Show error message
                            Toast.makeText(Signup_Activity.this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // Show error message
                        Toast.makeText(Signup_Activity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}
