package com.example.pythoncourse;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    private EditText newName, newEmail, newPassword, newRe_EnterPassword, newPhoneNumber;
    private Button newSignUpButton, newLoginButton;

    // Required empty public constructor
    public RegisterFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Initialize EditText fields and buttons
        newName = view.findViewById(R.id.newName);
        newEmail = view.findViewById(R.id.newEmail);
        newPassword = view.findViewById(R.id.newPassword);
        newRe_EnterPassword = view.findViewById(R.id.newRe_EnterPassword);
        newPhoneNumber = view.findViewById(R.id.newPhoneNumber);
        newSignUpButton = view.findViewById(R.id.newSignUpButton);
        newLoginButton = view.findViewById(R.id.newLoginButton);

        HelperDB helperDB = new HelperDB(getContext());
        SQLiteDatabase db;

        // Set click listener for sign up button
        newSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input from EditText fields
                String name = newName.getText().toString();
                String email = newEmail.getText().toString();
                String password = newPassword.getText().toString();
                String reEnteredPassword = newRe_EnterPassword.getText().toString();
                String phoneNumber = newPhoneNumber.getText().toString();

                // Check if passwords match
                if (!password.equals(reEnteredPassword)) {
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create UserDetails object
                UserDetails user = new UserDetails(name, email, password, phoneNumber);

                // Show a toast upon successfully registering
                Toast.makeText(getContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                // Optional: Save user details to the database here

                // Navigate back to LoginFragment
                getActivity().getSupportFragmentManager()
                        .popBackStack(); // Pop the back stack to return to the LoginFragment
            }
        });

        // Set click listener for login button, goes back to LoginFragment
        newLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to LoginFragment
                getActivity().getSupportFragmentManager()
                        .popBackStack(); // Pop the back stack to return to the LoginFragment
            }
        });

        return view;
    }
}
