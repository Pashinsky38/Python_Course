package com.example.pythoncourse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;

public class HelperDB extends SQLiteOpenHelper {

    // Database constants
    private static final String DATABASE_NAME = "user_management.db";// Database name
    private static final int DATABASE_VERSION = 1;// Database version
    public static final String USERS_TABLE = "Users";// Table name
    public static final String USER_NAME = "UserName";// Column names
    public static final String USER_PWD = "UserPassword";// Column names
    public static final String USER_EMAIL = "UserEmail";// Column names
    public static final String USER_PHONE = "UserPhone";// Column names

    private Context context;

    // Constructor
    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Method to create the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table with the new column order
        String createTable = "CREATE TABLE " + USERS_TABLE + " (" +
                USER_NAME + " TEXT, " +
                USER_EMAIL + " TEXT PRIMARY KEY, " +
                USER_PWD + " TEXT, " +
                USER_PHONE + " TEXT)";
        db.execSQL(createTable);
    }

    // Method to upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        // Create tables again with the new column order
        onCreate(db);
    }


    // Method to insert user details into the database
    public void insertUser(String userName, String userEmail, String userPassword, String userPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(USER_EMAIL, userEmail);
        values.put(USER_PWD, userPassword);
        values.put(USER_PHONE, userPhone);

        // Insert the values into the database
        long result = db.insert(USERS_TABLE, null, values);
        if (result == -1) {// If insertion fails
            Toast.makeText(context, "Failed to register user", Toast.LENGTH_SHORT).show();
        } else {// If insertion is successful
            Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to get the password by email
    @SuppressLint("Range")
    public String getPasswordByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase(); // Get a readable database
        String password = null;

        // Query to get the password for the specified email
        Cursor cursor = db.query(USERS_TABLE, new String[]{USER_PWD}, USER_EMAIL + "=?", new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(USER_PWD)); // Retrieve the password
            cursor.close(); // Close the cursor
        }

        return password; // Return the password (or null if not found)
    }
}
