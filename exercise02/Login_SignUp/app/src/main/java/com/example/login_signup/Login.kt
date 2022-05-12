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
import com.example.login_signup.databinding.ActivityLoginBinding
import com.shashank.sony.fancytoastlib.FancyToast

class Login : AppCompatActivity()
{
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener{
            startActivity( Intent(this, SignUp::class.java) )
        }

        binding.login.setOnClickListener {
            login()
        }
    }

    private fun login()
    {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()

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
        if (password.isEmpty())
        {
            binding.password.error = "Password is Required"
            binding.password.requestFocus()
            return
        }

        LoginAsyncTask(this).execute(email, password)
    }

    /** i think google will be deprecated next month */
    @SuppressLint("StaticFieldLeak")
    inner class LoginAsyncTask(private val context: Context): AsyncTask<String, Int, Int>()
    {

        override fun doInBackground(vararg p0: String?): Int
        {
            val helper = Helper(context)
            SystemClock.sleep(15000) // 15 second
            if (helper.login(p0[0].toString(), p0[1].toString()))
                return 0
            return -1
        }

        override fun onPostExecute(result: Int)
        {
            super.onPostExecute(result)
            // using the context we can display a toast
            if (result == 0) // success
                FancyToast.makeText(context, "Account is Valid",
                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show()
            else
                FancyToast.makeText(context, "Account is Not Valid",
                    FancyToast.LENGTH_LONG, FancyToast.WARNING,true).show()

        }
    }
}