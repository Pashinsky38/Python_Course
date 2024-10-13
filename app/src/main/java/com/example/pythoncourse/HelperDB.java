package com.example.pythoncourse;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class HelperDB extends SQLiteOpenHelper {
    // Database file name for storing user information
    public static final String DB_FILE = "user_management.db";
    // Table and column names for the Users table
    public static final String USERS_TABLE = "Users";
    public static final String USER_NAME = "UserName";
    public static final String USER_EMAIL = "UserEmail";
    public static final String USER_PWD = "UserPassword";
    public static final String USER_PHONE = "UserPhone";
    // Constructor for the HelperDB class
    public HelperDB(@Nullable Context context) {
// Call to the parent SQLiteOpenHelper constructor with database details
        super(context, DB_FILE, null, 1); // DB version is set to 1
    }
    // This method is called when the database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
// Create the Users table when the database is initialized
        db.execSQL(buildUserTable());
    }
    // This method is called when the database needs to be upgraded (e.g., when changing the version)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Currently, no upgrade logic is needed (so it's left empty)
// Can be used later for migrating to a new version of the database
    }
    // Method to build the SQL query for creating the Users table with the correct order
    public String buildUserTable() {
// SQL query string for creating the Users table with columns in the specified order
        String st = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE;
        st += "(" + USER_NAME + " TEXT,"; // Column for storing the user's name (TEXT type)
        st += USER_EMAIL + " TEXT, "; // Column for storing the user's email (TEXT type)
        st += USER_PWD + " TEXT, "; // Column for storing the user's password (TEXT type)
        st += USER_PHONE + " TEXT"; // Column for storing the user's phone number (TEXT type)
        st += ")"; // Close the CREATE TABLE statement
        return st;
    }
}// END OF CLASS
