package com.example.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buildRequest()
    }

    private fun buildRequest() {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d(TAG , url)
                    val myResponse : String = response.body.toString()
                    runOnUiThread { txtVwResult.text = myResponse }
                }
            }
        })
    }

    companion object {
        const val TAG = "OnResponse"
        val client = OkHttpClient()
        const val url = "https://reqres.in/api/users?page=2"
    }
}