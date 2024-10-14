package com.example.pythoncourse;

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
    private UserDetails user = new UserDetails("", ""); // Store user details (email & password)
    private EditText etEmailLogin, etPasswordLogin; // Email and password input fields
    private Button buttonSignUp, buttonLogin, buttonGoBack; // Sign-up, login, and go-back buttons

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

        // Initialize the login button
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract text from EditText fields
                String emailLogin = etEmailLogin.getText().toString();
                String passwordLogin = etPasswordLogin.getText().toString();
                user.setEmail(emailLogin);
                user.setPassword(passwordLogin);
                // Display a Toast message for successful login (implement login logic here)
                Toast.makeText(getActivity(), "Logged in with " + emailLogin, Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize the sign-up button
        buttonSignUp = view.findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Register fragment
                RegisterFragment registerFragment = new RegisterFragment(); // Create a new instance of RegisterFragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_register, registerFragment) // Replace fragment_register with the RegisterFragment
                        .addToBackStack(null) // Add this transaction to the back stack
                        .commit(); // Commit the transaction
            }
        });

        // Initialize the go-back button
        buttonGoBack = view.findViewById(R.id.goBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to HomePage using fragment back stack
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    // Create options menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Handle options menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuHome) {
            getActivity().getSupportFragmentManager().popBackStack(); // Navigate back to HomePage
            return true;
        } else if (id == R.id.menuCloseApp) {
            getActivity().finishAffinity(); // Close the app
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
