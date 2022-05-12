package com.example.login_signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Patterns
import com.example.login_signup.database.Helper
import com.example.login_signup.database.models.User
import com.example.login_signup.databinding.ActivitySignUpBinding
import com.shashank.sony.fancytoastlib.FancyToast

class SignUp : AppCompatActivity()
{
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.setOnClickListener{
            startActivity(Intent(this@SignUp, Login::class.java))
        }

        binding.register.setOnClickListener {
            register()
        }
    }

    private fun register()
    {
        val email = binding.email.text.toString().trim()
        val pass1 = binding.password.text.toString().trim()
        val pass2 = binding.password2.text.toString().trim()
        val name = binding.name.text.toString().trim()

        // check if email is empty
        if (email.isEmpty())
        {
            binding.email.error = "Email is Required"
            binding.email.requestFocus()
            return
        }

        // check if the email is valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.email.error = "Provide a Valid Email"
            binding.email.requestFocus()
            return
        }

        // check if password is empty
        if (pass1.isEmpty())
        {
            binding.password.error = "Password is Required"
            binding.password.requestFocus()
            return
        }

        // check if two passwords match
        if (pass1 != pass2)
        {
            binding.password.error = "Passwords Must Match"
            binding.password2.error = "Passwords Must Match"
            binding.password.requestFocus()
            binding.password.setText("")
            binding.password2.setText("")
            return
        }

        LoginAsyncTask(this).execute(name, pass1, email)
    }


    @SuppressLint("StaticFieldLeak")
    inner class LoginAsyncTask(private val context: Context): AsyncTask<String, Int, Int>()
    {

        override fun doInBackground(vararg p0: String?): Int
        {
            val helper = Helper(context)
            SystemClock.sleep(15000) // 15 second
            val user = User(p0[0].toString(), p0[1].toString(), p0[2].toString())
            if (helper.register(user))
                return 0
            return -1
        }

        override fun onPostExecute(result: Int)
        {
            super.onPostExecute(result)
            if (result == 0) // success
                FancyToast.makeText(context, "Account Registered",
                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show()
            else
                FancyToast.makeText(context, "Account is Already Registered",
                    FancyToast.LENGTH_LONG, FancyToast.WARNING,true).show()
        }
    }
}