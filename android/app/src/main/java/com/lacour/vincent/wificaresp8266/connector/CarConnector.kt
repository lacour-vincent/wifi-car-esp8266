package com.lacour.vincent.wificaresp8266.connector

import android.content.Context
import android.util.Log
import com.lacour.vincent.wificaresp8266.storage.Preferences
import retrofit2.*

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

    fun moveForward() = sendMoveRequest(preferences.getMoveForwardValue())
    fun moveBackward() = sendMoveRequest(preferences.getMoveBackwardValue())
    fun stopMoving() = sendMoveRequest(preferences.getStopValue())
    fun turnLeft() = sendMoveRequest(preferences.getTurnLeftValue())
    fun turnRight() = sendMoveRequest(preferences.getTurnRightValue())

    fun actionOne() = sendActionRequest(preferences.getActionOneValue())
    fun actionTwo() = sendActionRequest(preferences.getActionTwoValue())
    fun actionThree() = sendActionRequest(preferences.getActionThreeValue())
    fun actionFour() = sendActionRequest(preferences.getActionFourValue())
    fun actionFive() = sendActionRequest(preferences.getActionFiveValue())
    fun actionSix() = sendActionRequest(preferences.getActionSixValue())
    fun actionSeven() = sendActionRequest(preferences.getActionSevenValue())
    fun actionHeight() = sendActionRequest(preferences.getActionHeightValue())


    private fun sendMoveRequest(dir: String) {
        val request = service.move(dir)
        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i("Response", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        })
    }

    private fun sendActionRequest(type: String) {
        val request = service.action(type)
        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i("Response", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        })
    }

}
