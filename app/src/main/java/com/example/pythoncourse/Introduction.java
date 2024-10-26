package com.example.pythoncourse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Introduction extends AppCompatActivity {

    // Declare variables
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.introduction);

        // Find the Next button in the layout
        nextButton = findViewById(R.id.introductionToBasics);

        // Set a click listener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToBasicsFragment();
            }
        });
    }

    // Method to navigate to the BasicsFragment
    private void navigateToBasicsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BasicsFragment basicsFragment = new BasicsFragment();

        // Replace the current fragment or container with the BasicsFragment
        fragmentTransaction.replace(R.id.fragment_basics, basicsFragment);
        fragmentTransaction.addToBackStack(null); // Optionally add to back stack
        fragmentTransaction.commit();

        // Hide the introductory content
        findViewById(R.id.textView).setVisibility(View.GONE);
        findViewById(R.id.textView2).setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE); // Hide the Next button
    }
}
