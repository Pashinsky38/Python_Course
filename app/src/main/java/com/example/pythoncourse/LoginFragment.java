package com.example.pythoncourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pythoncourse.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding; // View binding for the fragment
    private HelperDB dbHelper; // Database helper for user authentication

    public LoginFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        dbHelper = new HelperDB(getActivity());

        // Set click listener for the login button
        binding.buttonLogin.setOnClickListener(v -> {
            String emailLogin = binding.EnterEmail.getText().toString().trim();
            String passwordLogin = binding.EnterPassword.getText().toString().trim();

            // Check if email and password are empty
            if (emailLogin.isEmpty() || passwordLogin.isEmpty()) {
                Toast.makeText(getActivity(), "Email and password must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Authenticate user
            if (isUserRegistered(emailLogin, passwordLogin)) {
                // Navigate to Introduction activity if login is successful
                Intent intent = new Intent(getActivity(), Introduction.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for the sign up button
        binding.buttonSignUp.setOnClickListener(v -> navigateToRegisterFragment());
        // Set click listener for the skip button
        binding.gotoHomePageButton.setOnClickListener(v -> navigateToHomePage());

        return view;
    }

    // Check if the user is registered in the database
    private boolean isUserRegistered(String email, String password) {
        String storedPassword = dbHelper.getPasswordByEmail(email);
        return password.equals(storedPassword);
    }

    // Navigate to the RegisterFragment
    private void navigateToRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_register, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    // Navigate to the HomePage activity
    private void navigateToHomePage() {
        startActivity(new Intent(getActivity(), HomePage.class));
    }

    // Inflate the options menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Handle options menu item selection
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuHome) {
            navigateToHomePage();
            return true;
        } else if (id == R.id.menuLogin) {
            Toast.makeText(getActivity(), "Already in Login!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuRegister) {
            navigateToRegisterFragment();
            return true;
        } else if (id == R.id.menuCloseApp) {
            getActivity().finishAffinity(); // Close the app
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}