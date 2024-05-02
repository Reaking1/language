package com.gamecodeschool.a3languages.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gamecodeschool.a3languages.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Initialize shared preferences
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);

        // Get language selection spinner
        Spinner languageSpinner = root.findViewById(R.id.languageSpinner);

        // Set up spinner with language options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Load saved language preference
        String selectedLanguage = sharedPreferences.getString("selectedLanguage", "English");
        int position = adapter.getPosition(selectedLanguage);
        languageSpinner.setSelection(position);

        // Save selected language preference
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String language = parent.getItemAtPosition(position).toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedLanguage", language);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return root;
    }
}
