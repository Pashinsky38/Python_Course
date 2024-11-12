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
        etName = view.findViewById(R.id.newName);// Find the EditText field by its ID
        etEmail = view.findViewById(R.id.newEmail);// Find the EditText field by its ID
        etPassword = view.findViewById(R.id.newPassword);// Find the EditText field by its ID
        etReEnterPassword = view.findViewById(R.id.newRe_EnterPassword);// Find the EditText field by its ID
        etPhoneNumber = view.findViewById(R.id.newPhoneNumber);// Find the EditText field by its ID
        buttonSignUp = view.findViewById(R.id.newSignUpButton);// Find the button by its ID
        buttonLogin = view.findViewById(R.id.newLoginButton);// Find the button by its ID
        // --------------------------- End of Initialization ---------------------------

        buttonSignUp.setOnClickListener(new View.OnClickListener() {// Set a click listener for the button
            @Override
            public void onClick(View v) {// Handle the button click
                // Get the input values from the EditText fields
                String name = etName.getText().toString().trim();// Trim leading and trailing spaces
                String email = etEmail.getText().toString().trim();// Trim leading and trailing spaces
                String password = etPassword.getText().toString().trim();// Trim leading and trailing spaces
                String reEnteredPassword = etReEnterPassword.getText().toString().trim();// Trim leading and trailing spaces
                String phoneNumber = etPhoneNumber.getText().toString().trim();// Trim leading and trailing spaces

                // Check if any of the fields are empty
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        reEnteredPassword.isEmpty() || phoneNumber.isEmpty()) {// Check if any of the fields are empty
                    Toast.makeText(getActivity(), "All fields must be filled", Toast.LENGTH_SHORT).show();// Show a toast message
                    return;
                }

                // Check if the password and re-entered password match
                if (!password.equals(reEnteredPassword)) {
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();// Show a toast message
                    return;
                }

                // Insert the user data into the database
                HelperDB helperDB = new HelperDB(getActivity());// Create an instance of HelperDB
                helperDB.insertUser(name, email, password, phoneNumber);// Insert the user data into the database
                Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();// Show a toast message
                navigateToLoginFragment();//
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {// Set a click listener for the button
            @Override
            public void onClick(View v) {// Handle the button click
                navigateToLoginFragment();// Navigate to LoginFragment
            }
        });

        return view;
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
        } else if (id == R.id.menuRegister)   {// Check if the item is the sign up menu item
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
