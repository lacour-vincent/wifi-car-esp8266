package com.lacour.vincent.wificaresp8266.connector

import android.content.Context
import android.util.Log
import com.lacour.vincent.wificaresp8266.storage.Preferences

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


private interface CarApiService {
    @GET("/move")
    fun move(@Query("dir") direction: String): Call<Void>

    @GET("/action")
    fun action(@Query("type") type: String): Call<Void>
}

class CarConnector(context: Context) {

    private val preferences = Preferences(context)
    private val url = "http://${preferences.getIpAddress()}:${preferences.getPort()}"
    private val retrofit = Retrofit.Builder().baseUrl(url).build()
    private val service = retrofit.create(CarApiService::class.java)

    fun moveForward() {
        send(service.move(preferences.getMoveForwardValue()))
    }

    fun moveBackward() = send(service.move(preferences.getMoveBackwardValue()))
    fun stopMoving() = send(service.move(preferences.getStopValue()))
    fun turnLeft() = send(service.move(preferences.getTurnLeftValue()))
    fun turnRight() = send(service.move(preferences.getTurnRightValue()))

    fun actionOne() = send(service.action(preferences.getActionOneValue()))
    fun actionTwo() = send(service.action(preferences.getActionTwoValue()))
    fun actionThree() = send(service.action(preferences.getActionThreeValue()))
    fun actionFour() = send(service.action(preferences.getActionFourValue()))
    fun actionFive() = send(service.action(preferences.getActionFiveValue()))
    fun actionSix() = send(service.action(preferences.getActionSixValue()))
    fun actionSeven() = send(service.action(preferences.getActionSevenValue()))
    fun actionHeight() = send(service.action(preferences.getActionHeightValue()))


    private fun send(request: Call<Void>) {
        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i("Response", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("Error", t.message!!)
            }
        })
    }
}
