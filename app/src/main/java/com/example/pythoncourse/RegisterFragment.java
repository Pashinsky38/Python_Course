package com.example.pythoncourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {
    // --------------------------- Variables ---------------------------
    private EditText etName, etEmail, etPassword, etReEnterPassword, etPhoneNumber;// Edit text fields for name, email, password, re-enter password, and phone number
    private Button buttonSignUp, buttonLogin;// Buttons for sign up and login
    // --------------------------- End of Variables -------------------

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);// Inflate the layout

        // --------------------------- Initialization ---------------------------
        etName = view.findViewById(R.id.newName);
        etEmail = view.findViewById(R.id.newEmail);
        etPassword = view.findViewById(R.id.newPassword);
        etReEnterPassword = view.findViewById(R.id.newRe_EnterPassword);
        etPhoneNumber = view.findViewById(R.id.newPhoneNumber);
        buttonSignUp = view.findViewById(R.id.newSignUpButton);
        buttonLogin = view.findViewById(R.id.newLoginButton);
        // --------------------------- End of Initialization ---------------------------

        // Set a click listener for the sign up button
        buttonSignUp.setOnClickListener(v -> {
            // Get the input values from the EditText fields
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String reEnteredPassword = etReEnterPassword.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();

            // Check if any of the fields are empty
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                    reEnteredPassword.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(getActivity(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the password and re-entered password match
            if (!password.equals(reEnteredPassword)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert the user data into the database
            HelperDB helperDB = new HelperDB(getActivity());
            helperDB.insertUser(name, email, password, phoneNumber);
            Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();
            navigateToLoginFragment();
        });

        buttonLogin.setOnClickListener(v -> navigateToLoginFragment());// Set a click listener for the login button
        return view;// Return the inflated view
    }
    // --------------------------- Navigation Methods ---------------------------
    // Navigate to LoginFragment
    private void navigateToLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        getActivity().getSupportFragmentManager()// Get the activity's support fragment manager
                .beginTransaction()// Start a fragment transaction
                .replace(R.id.fragment_register, loginFragment)// Replace the current fragment with LoginFragment
                .commit();// Commit the transaction
    }

    // Navigate to the home page
    private void navigateToHomePage() {
        startActivity(new Intent(getActivity(), HomePage.class));// Start the HomePage activity
    }
    // --------------------------- End of Navigation Methods -------------------

    // ---------------------------- Menu Methods ---------------------------
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();// Clear the menu
        inflater.inflate(R.menu.main, menu);// Inflate the menu from the main resource file
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Inflate the menu
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();// Get the item's ID
        if (id == R.id.menuHome) {// Check if the item is the home menu item
            navigateToHomePage();// Navigate back to the home page
            return true;
        } else if (id == R.id.menuLogin) {// Check if the item is the login menu item
            navigateToLoginFragment();// Navigate to the login fragment
            return true;
        } else if (id == R.id.menuRegister) {// Check if the item is the sign up menu item
            // Stays in the same fragment
            Toast.makeText(getActivity(), "Already in Register!", Toast.LENGTH_SHORT).show();// Show a toast message
            return true;
        } else if (id == R.id.menuCloseApp) {// Check if the item is the close app menu item
            getActivity().finish();// Finish the activity
            return true;
        }

        return super.onOptionsItemSelected(item);// Handle the item selection
    }
    // ---------------------------- End of Menu Methods -------------------
}// ---------------------------------- End of RegisterFragment ----------------------------------
