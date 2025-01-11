package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class ShareFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for the fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        // Button to share via email
        Button shareEmailButton = view.findViewById(R.id.share_email_button);
        shareEmailButton.setOnClickListener(v -> shareAppByEmail());

        // Button to share on Facebook (if the app is installed)
        Button shareFacebookButton = view.findViewById(R.id.share_facebook_button);
        shareFacebookButton.setOnClickListener(v -> shareOnFacebook());

        return view;
    }

    // Method to share the app via email
    public void shareAppByEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"}); // Optional recipient
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this amazing app!");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "I found this amazing app, you should try it!");
        startActivity(Intent.createChooser(emailIntent, "Send email"));
    }

    // Method to share the app via Facebook (only if Facebook is installed)
    public void shareOnFacebook() {
        Intent facebookIntent = new Intent(Intent.ACTION_SEND);
        facebookIntent.setType("text/plain");
        String shareText = "Check out this amazing app!";
        facebookIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        facebookIntent.setPackage("com.facebook.katana"); // Facebook package name

        try {
            startActivity(facebookIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Facebook is not installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
