package com.example.pythoncourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    // Declare variables
    Button buttonStart;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        // Find the start button in the layout
        buttonStart = findViewById(R.id.buttonStart);

        // Set a click listener for the start button
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cancel the timer if the button is clicked before the timer runs out
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }

                // Navigate to the LoginFragment using fragment transaction
                navigateToLoginFragment();
            }
        });

        // Start the countdown timer for 5 seconds
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Irrelevant, does nothing
            }

            @Override
            public void onFinish() {
                // Automatically navigate to the LoginFragment after 5 seconds
                navigateToLoginFragment();
                // Display a toast message saying "Welcome!"
                Toast.makeText(HomePage.this, "Welcome!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    // --------------------------- Navigation Methods ---------------------------
    // Method to navigate to the LoginFragment
    private void navigateToLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment loginFragment = new LoginFragment();

        // Replace the current fragment with the LoginFragment
        fragmentTransaction.replace(R.id.fragment_login, loginFragment);
        fragmentTransaction.addToBackStack(null); // Optionally add to back stack
        fragmentTransaction.commit();
    }

    // Method to navigate to the RegisterFragment
    private void navigateToRegisterFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment registerFragment = new RegisterFragment();

        // Replace the current fragment with the RegisterFragment
        fragmentTransaction.replace(R.id.fragment_register, registerFragment);
        fragmentTransaction.addToBackStack(null); // Optionally add to back stack
        fragmentTransaction.commit();
    }
    // --------------------------- End of Navigation Methods -------------------

    // Create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Handle options menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuHome) {
            // Stay on HomePage
            return true;
        } else if (id == R.id.menuLogin) {
            // Navigate to the LoginFragment
            navigateToLoginFragment();
            return true;
        } else if (id == R.id.menuSignUp) {
            // Navigate to the RegisterFragment
            navigateToRegisterFragment();
            return true;
        } else if (id == R.id.menuCloseApp) {
            // Close the app
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
