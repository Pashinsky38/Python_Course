package com.example.pythoncourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    private EditText etEmailLogin, etPasswordLogin; // Email and password input fields
    private Button buttonSignUp, buttonLogin, buttonGoBack; // Sign-up, login, and go-back buttons
    private HelperDB dbHelper; // Database helper instance

    public LoginFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setHasOptionsMenu(true); // Allow fragment to have its own options menu

        // Initialize the email and password EditText fields
        etEmailLogin = view.findViewById(R.id.EnterEmail);
        etPasswordLogin = view.findViewById(R.id.EnterPassword);

        // Initialize the database helper
        dbHelper = new HelperDB(getActivity());

        // Initialize the login button
        buttonLogin = view.findViewById(R.id.buttonLogin);
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

        // Initialize the sign-up button
        buttonSignUp = view.findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegisterFragment(); // Navigate to RegisterFragment
            }
        });

        // Initialize the go-back button
        buttonGoBack = view.findViewById(R.id.goBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToHomePage(); // Navigate back to HomePage
            }
        });

        return view;
    }

    // Check if the user is registered
    private boolean isUserRegistered(String email, String password) {
        String storedPassword = dbHelper.getPasswordByEmail(email);
        return password.equals(storedPassword);
    }

    // Handle options menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuHome) {
            navigateBackToHomePage(); // Navigate back to HomePage
            return true;
        } else if (id == R.id.menuLogin){
            // Stay on LoginFragment
            return true;
        } else if (id == R.id.menuSignUp) {
            navigateToRegisterFragment(); // Navigate to RegisterFragment
            return true;
        } else if (id == R.id.menuCloseApp) {
            getActivity().finishAffinity(); // Close the app
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    private void navigateBackToHomePage() {
        getActivity().getSupportFragmentManager().popBackStack(null, 0); // Navigate back to HomePage
    }
    // --------------------------- End of Navigation Methods -------------------
}
