package com.example.pythoncourse;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText newName, newEmail, newPassword, newRe_EnterPassword, newPhoneNumber;
    Button newSignUpButton, newLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        HelperDB helperDB = new HelperDB(this);
        SQLiteDatabase db;

        // Initialize EditText fields and buttons
        newName = findViewById(R.id.newName1);
        newEmail = findViewById(R.id.newEmail1);
        newPassword = findViewById(R.id.newPassword1);
        newRe_EnterPassword = findViewById(R.id.newRe_EnterPassword1);
        newPhoneNumber = findViewById(R.id.newPhoneNumber1);
        newSignUpButton = findViewById(R.id.newSignUpButton1);
        newLoginButton = findViewById(R.id.newLoginButton1);

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

                // Check if the passwords match
                if (!password.equals(reEnteredPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return; // Stop the registration process
                }

                // Create UserDetails object
                UserDetails user = new UserDetails(name, email, password, phoneNumber);

                // Show a toast upon successfully registering
                Toast.makeText(Register.this, "Registered now", Toast.LENGTH_SHORT).show();

                // Go back to Login activity
                Intent intent = new Intent(Register.this, Login.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("password", user.getPassword());
                startActivity(intent);
            }
        });

        // Set click listener for login button, goes back to Login
        newLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    // Create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Variable for receiving an input
        int id = item.getItemId();

        if (id == R.id.menuHome) {
            // If clicked on Home Page, goes from Register to HomePage
            Intent intent = new Intent(Register.this, HomePage.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuLogin) {
            // If clicked on Login, goes to Login page
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuRegister) {
            // If clicked on Sign Up, stays on Register page
            Intent intent = new Intent(Register.this, Register.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuCloseApp) {
            // If clicked on Close App, the app closes
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
