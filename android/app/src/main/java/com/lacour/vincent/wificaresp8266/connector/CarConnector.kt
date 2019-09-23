package com.lacour.vincent.wificaresp8266.connector

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.preference.PreferenceManager

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface CarApiService {
    @GET("/move")
    fun move(@Query("dir") direction: String): Call<Void>

    @GET("/action")
    fun action(@Query("type") type: String): Call<Void>
}

class CarConnector(context: Context) {

    private val ctx: Context = context
    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)
    private val baseUrl = preferences.getString("ip_address", "")!!
    private val retrofit = Retrofit.Builder().baseUrl("http://192.168.0.18:5000").build()
    private val service = retrofit.create(CarApiService::class.java)


    fun moveForward() {
        val moveForwardRequest = service.move("F")
        send(moveForwardRequest)
    }

    fun moveBackward() {
        val moveBackwardRequest = service.move("B")
        send(moveBackwardRequest)
    }

    fun turnLeft() {
        val turnLeftRequest = service.move("L")
        send(turnLeftRequest)
    }

    fun turnRight() {
        val turnRightRequest = service.move("R")
        send(turnRightRequest)
    }

    fun stopMoving() {
        val stopMovingRequest = service.move("S")
        send(stopMovingRequest)
    }

    fun send(request: Call<Void>) {
        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                // Log.i("Response", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(ctx, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
