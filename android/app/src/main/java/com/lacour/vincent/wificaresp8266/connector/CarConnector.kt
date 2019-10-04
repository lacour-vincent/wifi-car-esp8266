package com.lacour.vincent.wificaresp8266.connector

import android.content.Context
import android.util.Log
import com.lacour.vincent.wificaresp8266.storage.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*

import retrofit2.http.GET
import retrofit2.http.Query


private interface CarApiService {
    @GET("/move")
    suspend fun move(@Query("dir") direction: String): Response<Void>

    @GET("/action")
    suspend fun action(@Query("type") type: String): Response<Void>
}

class CarConnector(context: Context) {

    private val preferences = Preferences(context)
    private val url = "http://${preferences.getIpAddress()}:${preferences.getPort()}"
    private val retrofit = Retrofit.Builder().baseUrl(url).build()
    private val service = retrofit.create(CarApiService::class.java)

    fun moveForward() = sendMoveRequest(preferences.getMoveBackwardValue())
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
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.move(dir)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.i("Response", response.code().toString())
                    } else {
                        Log.i("Error", response.code().toString())
                    }
                } catch (e: HttpException) {
                    Log.i("Exception", e.message().toString())
                }
            }
        }
    }

    private fun sendActionRequest(type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.action(type)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.i("Response", response.code().toString())
                    } else {
                        Log.i("Error", response.code().toString())
                    }
                } catch (e: HttpException) {
                    Log.i("Exception", e.message().toString())
                }
            }
        }
    }

}
