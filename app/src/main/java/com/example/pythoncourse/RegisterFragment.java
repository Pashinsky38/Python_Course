package com.example.pythoncourse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.pythoncourse.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Set click listener for the sign-up button
        binding.newSignUpButton.setOnClickListener(v -> {
            String name = binding.newName.getText().toString().trim();
            String email = binding.newEmail.getText().toString().trim();
            String password = binding.newPassword.getText().toString().trim();
            String reEnteredPassword = binding.newReEnterPassword.getText().toString().trim();
            String phoneNumber = binding.newPhoneNumber.getText().toString().trim();

            // Validate input fields
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                    reEnteredPassword.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(getActivity(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if passwords match
            if (!password.equals(reEnteredPassword)) {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert user data into the database
            HelperDB helperDB = new HelperDB(getActivity());
            boolean isInserted = helperDB.insertUser(name, email, password, phoneNumber);
            if (isInserted) {
                Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();
                navigateToLoginFragment();
            } else {
                Toast.makeText(getActivity(), "Failed to register user", Toast.LENGTH_SHORT).show();
            }

            // Navigate to LoginFragment after successful registration
            navigateToLoginFragment();
        });

        binding.newLoginButton.setOnClickListener(v -> navigateToLoginFragment());

        return view;
    }

    // Navigate to the LoginFragment
    private void navigateToLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_register, loginFragment)
                .commit();
    }
}