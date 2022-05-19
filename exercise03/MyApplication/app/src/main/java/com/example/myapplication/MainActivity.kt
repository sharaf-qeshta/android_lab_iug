package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            getData()
        }
    }


    private fun getData()
    {
        val queue = Volley.newRequestQueue(this)
        val url = "https://saree3.net/api/user/products";

        val jsonObjectRequest = JsonObjectRequest( Request.Method.GET, url, null,
            { response ->
                Log.e("data_status", "${response.getBoolean("status")}")
                Log.e("data_message", response.getString("message"))

                val data = response.getJSONObject("data")

                Log.e("data_products_count", "${data.getInt("product_count")}")

                // displaying products
                val products = data.getJSONArray("products")
                for (i in 0 until products.length())
                {
                    Log.e("data_product${i+1}_start", "********************************************")
                    val product = products.getJSONObject(i)
                    Log.e("data_product${i+1}_id", "${product.getInt("id")}")
                    Log.e("data_product${i+1}_name", product.getString("name"))
                    Log.e("data_product${i+1}_arabic_name", product.getString("name_ar"))
                    Log.e("data_product${i+1}_english_name", product.getString("name_en"))
                    Log.e("data_product${i+1}_description", product.getString("description"))
                    Log.e("data_product${i+1}_arabic_description", product.getString("description_ar"))
                    Log.e("data_product${i+1}_english_description", product.getString("description_en"))
                    Log.e("data_product${i+1}_image_url", product.getString("image"))
                    Log.e("data_product${i+1}_price", "${product.getInt("price")}")
                    Log.e("data_product${i+1}_offer", "${product.getInt("offer")}")
                    Log.e("data_product${i+1}_quantity", "${product.getInt("quantity")}")
                    Log.e("data_product${i+1}_currency", product.getString("currency"))
                    Log.e("data_product${i+1}_preparation time", "${product.getInt("preparation_time")}")
                    Log.e("data_product${i+1}_category", product.getString("category"))
                    Log.e("data_product${i+1}_company", product.getString("company"))
                    Log.e("data_product${i+1}_company_id", "${product.getInt("company_id")}")
                    Log.e("data_product${i+1}_rate sum", "${product.getInt("rate_sum")}")
                    Log.e("data_product${i+1}_rate sum", "${product.getInt("rate_count")}")
                    Log.e("data_product${i+1}_rate", "${product.getInt("rate")}")
                    Log.e("data_product${i+1}_in favorite", "${product.getBoolean("in_favorite")}")

                    Log.e("data_product${i+1}_images", "--------------------------")

                    // reading images for each product
                    val imagesArray = product.getJSONArray("images")
                    for (j in 0 until imagesArray.length())
                    {
                        Log.e("data_product${i+1}_image${j+1}", imagesArray.getString(j))
                    }
                    Log.e("data_product${i}_end", "*******************************************")
                }

                val paginationOptions = data.getJSONObject("pagination_options")

                Log.e("data_Pagination_Options", "=================================================")
                Log.e("data_Pagination_current_page", "${paginationOptions.getInt("current_page")}")
                Log.e("data_Pagination_next_page_url",
                    paginationOptions.getString("next_page_url"))
                Log.e("data_Pagination_previous_page_url", paginationOptions.getString("prev_page_url"))
                Log.e("data_Pagination_first_page", "${paginationOptions.getBoolean("first_page")}")
                Log.e("data_Pagination_last_page", "${paginationOptions.getInt("last_page")}")
                Log.e("data_Pagination_count", "${paginationOptions.getInt("count")}")
                Log.e("data_Pagination_per_page", "${paginationOptions.getInt("per_page")}")
                Log.e("data_Pagination_total", "${paginationOptions.getInt("total")}")
            },
            {
                Log.e("sharaf", "fail")
            })

        queue.add(jsonObjectRequest)
    }
}