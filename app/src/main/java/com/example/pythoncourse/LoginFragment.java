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

public class LoginFragment extends Fragment {
    // --------------------------- Variables ---------------------------
    private EditText etEmailLogin, etPasswordLogin;// Email and password EditText fields
    private Button gotoRegisterButton, buttonLogin, gotoHomePageButton;// Buttons for navigation
    private HelperDB dbHelper;// Database helper
    // --------------------------- End of Variables -------------------

    public LoginFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setHasOptionsMenu(true); // Allow fragment to have its own options menu

        // --------------------------- Initialization ---------------------------
        etEmailLogin = view.findViewById(R.id.EnterEmail);
        etPasswordLogin = view.findViewById(R.id.EnterPassword);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        gotoRegisterButton = view.findViewById(R.id.buttonSignUp);
        gotoHomePageButton = view.findViewById(R.id.gotoHomePageButton);
        // --------------------------- End of Initialization -------------------

        // Initialize the database helper
        dbHelper = new HelperDB(getActivity());

        // Initialize the login button

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract text from EditText fields
                String emailLogin = etEmailLogin.getText().toString().trim();
                String passwordLogin = etPasswordLogin.getText().toString().trim();

                // Input validation
                if (emailLogin.isEmpty() || passwordLogin.isEmpty()) {
                    Toast.makeText(getActivity(), "Email and password must not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if user is registered in the database
                if (isUserRegistered(emailLogin, passwordLogin)) {
                    // Navigate to the Introduction activity if login is successful
                    Intent intent = new Intent(getActivity(), Introduction.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gotoRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegisterFragment(); // Navigate to RegisterFragment
            }
        });

        gotoHomePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHomePage(); // Navigate back to HomePage
            }
        });

        return view;
    }

    // Check if the user is registered
    private boolean isUserRegistered(String email, String password) {
        String storedPassword = dbHelper.getPasswordByEmail(email);
        return password.equals(storedPassword);
    }

    // --------------------------- Navigation Methods ---------------------------
    // Navigate to RegisterFragment
    private void navigateToRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_register, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    // Navigate back to HomePage
    private void navigateToHomePage() {
        startActivity(new Intent(getActivity(), HomePage.class));// Start the HomePage activity
    }
    // --------------------------- End of Navigation Methods -------------------

    // --------------------------- Options Menu Methods -----------------------
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();// Clear the menu
        inflater.inflate(R.menu.main, menu); // Inflate a different menu if desired
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // Get the selected item's ID

        if (id == R.id.menuHome) {
            navigateToHomePage(); // Navigate back to HomePage
            return true;
        } else if (id == R.id.menuLogin) {
            // Stay on LoginFragment
            Toast.makeText(getActivity(), "Already in Login!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuRegister) {
            navigateToRegisterFragment(); // Navigate to RegisterFragment
            return true;
        } else if (id == R.id.menuCloseApp) {
            getActivity().finishAffinity(); // Close the app
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // --------------------------- End of Options Menu Methods -----------------
}// -------------------------------- End of LoginFragment --------------------------------