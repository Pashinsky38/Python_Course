package com.example.pythoncourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    // --------------------------- Variables ---------------------------
    Button gotoLoginButton;// Button to navigate to the LoginFragment
    // --------------------------- End of Variables -------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);// Set the layout for the activity

        gotoLoginButton = findViewById(R.id.gotoLoginButton);// Find the button by its ID

        // Set a click listener for the start button
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// Handle the click event
                navigateToLoginFragment();// Navigate to the LoginFragment
            }
        });
    }

    // --------------------------- Navigation Methods ---------------------------
    // Method to navigate to the LoginFragment
    private void navigateToLoginFragment() {
        getSupportFragmentManager().beginTransaction()// Start a new FragmentTransaction
                .replace(R.id.fragment_login, new LoginFragment())// Replace the current fragment with the LoginFragment
                .addToBackStack(null)// Add the transaction to the back stack
                .commit();// Commit the transaction
    }


    // Method to navigate to the RegisterFragment
    private void navigateToRegisterFragment() {
        getSupportFragmentManager().beginTransaction()// Start a new FragmentTransaction
                .replace(R.id.fragment_register, new RegisterFragment())// Replace the current fragment with the RegisterFragment
                .addToBackStack(null)// Add the transaction to the back stack
                .commit();// Commit the transaction
    }
    // --------------------------- End of Navigation Methods -------------------

    // --------------------------- Menu Methods ---------------------------
    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);// Inflate the menu layout from the menu.xml file
        return true;
    }

    // Handle options menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();// Get the ID of the selected item

        if (id == R.id.menuHome) {// Check if the selected item is the Home menu item
            // Stay on HomePage
            Toast.makeText(this, "You are already on HomePage", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuLogin) {// Check if the selected item is the Login menu item
            navigateToLoginFragment();// Navigate to the LoginFragment
            return true;
        } else if (id == R.id.menuRegister) {// Check if the selected item is the Register menu item
            navigateToRegisterFragment();// Navigate to the RegisterFragment
            return true;
        } else if (id == R.id.menuCloseApp) {// Check if the selected item is the Close App menu item
            finishAffinity();// Close the application
            return true;
        }
        return super.onOptionsItemSelected(item);// Handle other menu item selections
    }
    // --------------------------- End of Menu Methods -------------------
}// -------------------------------------------------------------------------- End of HomePage Class --------------------------------------------------------------------------