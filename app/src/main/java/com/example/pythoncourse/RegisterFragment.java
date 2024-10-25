package com.example.pythoncourse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {
    private EditText etName, etEmail, etPassword, etReEnterPassword, etPhoneNumber; // Input fields
    private Button buttonSignUp, buttonLogin; // Sign-up and login buttons

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Initialize the EditText fields
        etName = view.findViewById(R.id.newName);
        etEmail = view.findViewById(R.id.newEmail);
        etPassword = view.findViewById(R.id.newPassword);
        etReEnterPassword = view.findViewById(R.id.newRe_EnterPassword);
        etPhoneNumber = view.findViewById(R.id.newPhoneNumber);

        // Initialize the sign-up button
        buttonSignUp = view.findViewById(R.id.newSignUpButton);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract text from EditText fields
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String reEnteredPassword = etReEnterPassword.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();

                // Input validation
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        reEnteredPassword.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(getActivity(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(reEnteredPassword)) {
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Here you would typically save the user details to a database
                // For demonstration, we just show a Toast
                Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();

                // Optionally navigate back to LoginFragment after successful registration
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_register, new LoginFragment()) // Replace with LoginFragment
                        .addToBackStack(null) // Add this transaction to the back stack
                        .commit(); // Commit the transaction
            }
        });

        // Initialize the login button
        buttonLogin = view.findViewById(R.id.newLoginButton);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginFragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_register, new LoginFragment()) // Replace with LoginFragment
                        .addToBackStack(null) // Add this transaction to the back stack
                        .commit(); // Commit the transaction
            }
        });

        return view;
    }
}
