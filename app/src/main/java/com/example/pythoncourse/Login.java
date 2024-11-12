package com.example.pythoncourse;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    UserDetails user = new UserDetails("", ""); // Declare a variable to hold user details (just 2, not 4)

    EditText etEmailLogin, etPasswordLogin; // Declare EditText fields for email and password
    Button buttonSignUp, buttonLogin, buttonGoBack; // Declare buttons for sign up, login, and going back (to HomePage)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize the sign up button and set a click listener
        buttonSignUp = findViewById(R.id.buttonSignUp1);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the Register activity
                Intent intent = new Intent(Login.this, Register.class);
                // Start the Register activity
                startActivity(intent);
            }
        });

        // Initialize the login button, email EditText, and password EditText
        buttonLogin = findViewById(R.id.buttonLogin1);
        etEmailLogin = findViewById(R.id.EnterEmail1);
        etPasswordLogin = findViewById(R.id.EnterPassword1);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        etEmailLogin.setText(email);

        String password = intent.getStringExtra("password");
        etPasswordLogin.setText(password);

        // Set a click listener for the login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract text from EditText fields
                String emailLogin = etEmailLogin.getText().toString();
                String passwordLogin = etPasswordLogin.getText().toString();

                user.setEmail(emailLogin);
                user.setPassword(passwordLogin);
            }
        });

        // Initialize the go back button and set a click listener
        buttonGoBack = findViewById(R.id.goBack1);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the Home Page activity
                Intent intent = new Intent(Login.this, HomePage.class);
                // Start the Home Page activity
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
            // If clicked on Home Page, goes from Login to HomePage
            Intent intent = new Intent(Login.this, HomePage.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuLogin) {
            // If clicked on Login, stays on the Login page
            Intent intent = new Intent(Login.this, Login.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menuRegister) {
            // If clicked on Sign Up, goes to Register page
            Intent intent = new Intent(Login.this, Register.class);
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
