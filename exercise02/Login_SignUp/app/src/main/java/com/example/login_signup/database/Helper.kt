package com.example.login_signup.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.login_signup.database.models.User

class Helper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION)
{
    private var db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(p0: SQLiteDatabase?)
    {
        p0!!.execSQL(User.CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {
        p0!!.execSQL("DROP TABLE IF EXISTS ${User.TABLE_NAME}")
        onCreate(p0)
    }

    @SuppressLint("Recycle")
    fun login(email: String, password: String): Boolean
    {
        val cursor = db.rawQuery("select * from ${User.TABLE_NAME} where " +
                "${User.USER_EMAIL_COLUMN} = '$email' and ${User.USER_PASSWORD_COLUMN} = '$password'", null)
        cursor.moveToFirst()

        return cursor.count != 0
    }


    /**
     * if we want to increase security we can encrypt the password instead of storing it raw
     * */
    fun register(user: User): Boolean
    {
          val cv = ContentValues()
          cv.put(User.USER_EMAIL_COLUMN, user.email)
          cv.put(User.USER_PASSWORD_COLUMN, user.password)
          cv.put(User.USER_NAME_COLUMN, user.name)
          return db.insert(User.TABLE_NAME, null, cv) > 0
    }


    companion object
    {
        private const val DATABASE_NAME = "taskdb"
        private const val DATABASE_VERSION = 7
    }
}