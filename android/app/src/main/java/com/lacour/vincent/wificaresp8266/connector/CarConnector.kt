package com.lacour.vincent.wificaresp8266.connector

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.lacour.vincent.wificaresp8266.R
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
    private var service: CarApiService? = null

    init {
        val url = "http://${preferences.getIpAddress()}:${preferences.getPort()}"
        try {
            val retrofit = Retrofit.Builder().baseUrl(url).build()
            service = retrofit.create(CarApiService::class.java)
        } catch (e: IllegalArgumentException) {
            Toast.makeText(
                context,
                context.getString(R.string.invalid_hostname, url),
                Toast.LENGTH_LONG
            ).show()
        }

    }

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
        if (service == null) return
        val request = (service as CarApiService).move(dir)
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
        if (service == null) return
        val request = (service as CarApiService).action(type)
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
