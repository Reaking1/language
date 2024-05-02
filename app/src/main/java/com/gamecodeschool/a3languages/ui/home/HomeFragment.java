package com.gamecodeschool.a3languages.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gamecodeschool.a3languages.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView titleTextView;
    private SharedPreferences sharedPreferences;
    private static final String LANG_PREF_KEY = "lang_pref";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        titleTextView = root.findViewById(R.id.titleTextView);

        // Retrieve language preference from shared preferences
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String languagePref = sharedPreferences.getString(LANG_PREF_KEY, "en");

        // Set text based on language preference
        setTextBasedOnLanguage(languagePref);

        // Button click listener to navigate to the second interface
        Button nextButton = root.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the second interface
                navigateToSecondInterface();
            }
        });

        return root;
    }

    // Method to set text based on language preference
    private void setTextBasedOnLanguage(String language) {
        switch (language) {
            case "fr":
                titleTextView.setText("Bienvenue chez XYZ Enterprises");
                break;
            case "es":
                titleTextView.setText("Bienvenido a Empresas XYZ!");
                break;
            default:
                titleTextView.setText("Welcome to XYZ Enterprises");
                break;
        }
    }

    // Method to navigate to the second interface
    private void navigateToSecondInterface() {
        // Replace this with your navigation logic to the second interface
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
