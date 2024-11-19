package com.example.pythoncourse;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pythoncourse.databinding.HomePageBinding;

import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    private HomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gotoLoginButton.setOnClickListener(view -> navigateToLoginFragment());

        scheduleAlarm(); // Schedule the alarm when the activity is created
    }

    // --------------------------- Navigation Methods ---------------------------
    // Method to navigate to the LoginFragment
    private void navigateToLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_login, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

    // Method to navigate to the RegisterFragment
    private void navigateToRegisterFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_register, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }
    // --------------------------- End of Navigation Methods -------------------

    // --------------------------- Menu Methods ---------------------------
    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Handle options menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuHome) {
            // Stay on HomePage
            Toast.makeText(this, "You are already on HomePage", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuLogin) {
            navigateToLoginFragment();
            return true;
        } else if (id == R.id.menuRegister) {
            navigateToRegisterFragment();
            return true;
        } else if (id == R.id.menuCloseApp) {
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // --------------------------- End of Menu Methods -------------------

    private void scheduleAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Check if the time has already passed for today
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Add one day
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}