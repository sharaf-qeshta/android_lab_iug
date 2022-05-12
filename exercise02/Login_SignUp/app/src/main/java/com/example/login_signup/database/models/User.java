package com.example.login_signup.database.models;

public class User
{
    public static final String TABLE_NAME = "users";
    public static final String USER_NAME_COLUMN = "user_name";
    public static final String USER_PASSWORD_COLUMN = "user_password";
    public static final String USER_EMAIL_COLUMN = "user_email";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
            + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_NAME_COLUMN + " VARCHAR(50), "
            + USER_EMAIL_COLUMN + " VARCHAR(255) NOT NULL UNIQUE, "
            + USER_PASSWORD_COLUMN + " VARCHAR(255) NOT NULL ); ";



    private String name, password, email;

    public User() { }

    public User(String name,
                String password,
                String email)
    {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
