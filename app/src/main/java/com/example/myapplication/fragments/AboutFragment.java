package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class AboutFragment extends Fragment {

    private TextView aboutTitle, aboutDescription, aboutVersion, aboutDeveloper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Initialize TextViews
        aboutTitle = view.findViewById(R.id.about_title);
        aboutDescription = view.findViewById(R.id.about_description);
        aboutVersion = view.findViewById(R.id.about_version);
        aboutDeveloper = view.findViewById(R.id.about_developer);

        // Set the description text
        aboutTitle.setText("About This App");
        aboutDescription.setText("This app helps workers in Tunisair to fix their own problems related to materials like printers, keyboards, network cables, and more. The app provides solutions and guides to help resolve common technical issues, empowering users to troubleshoot and solve problems efficiently.");
        aboutVersion.setText("App Version: 1.0.0");
        aboutDeveloper.setText("Developer: Wajih Ben Hassen");

        return view;
    }
}
