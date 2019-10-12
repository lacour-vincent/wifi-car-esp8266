package com.lacour.vincent.wificaresp8266.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.lacour.vincent.wificaresp8266.constant.Constant
import com.lacour.vincent.wificaresp8266.model.Storage

class Preferences(ctx: Context) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

    private fun getSharedPreferencesValue(storage: Storage): String {
        val (key, default) = storage
        val value = preferences.getString(key, default)!!
        return if (value.isBlank()) default else value
    }

    fun getIpAddress(): String = getSharedPreferencesValue(Constant.IP_ADDRESS_ST0RAGE)
    fun getPort(): String = getSharedPreferencesValue(Constant.PORT_STORAGE)

    fun getMoveForwardValue(): String = getSharedPreferencesValue(Constant.MOVE_FORWARD_STORAGE)
    fun getMoveBackwardValue(): String = getSharedPreferencesValue(Constant.MOVE_BACKWARD_STORAGE)
    fun getStopValue(): String = getSharedPreferencesValue(Constant.MOVE_STOP_STORAGE)
    fun getTurnRightValue(): String = getSharedPreferencesValue(Constant.TURN_RIGHT_STORAGE)
    fun getTurnLeftValue(): String = getSharedPreferencesValue(Constant.TURN_LEFT_STORAGE)

    fun getActionOneValue(): String = getSharedPreferencesValue(Constant.ACTION_1_STORAGE)
    fun getActionTwoValue(): String = getSharedPreferencesValue(Constant.ACTION_2_STORAGE)
    fun getActionThreeValue(): String = getSharedPreferencesValue(Constant.ACTION_3_STORAGE)
    fun getActionFourValue(): String = getSharedPreferencesValue(Constant.ACTION_4_STORAGE)
    fun getActionFiveValue(): String = getSharedPreferencesValue(Constant.ACTION_5_STORAGE)
    fun getActionSixValue(): String = getSharedPreferencesValue(Constant.ACTION_6_STORAGE)
    fun getActionSevenValue(): String = getSharedPreferencesValue(Constant.ACTION_7_STORAGE)
    fun getActionHeightValue(): String = getSharedPreferencesValue(Constant.ACTION_8_STORAGE)

}



