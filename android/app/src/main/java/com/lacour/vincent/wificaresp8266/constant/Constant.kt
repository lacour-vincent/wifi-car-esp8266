package com.lacour.vincent.wificaresp8266.constant

import com.lacour.vincent.wificaresp8266.model.Storage


object Constant {

    val IP_ADDRESS_ST0RAGE = Storage("ip_address", "192.168.4.1")
    val PORT_STORAGE = Storage("port", "8080")

    val MOVE_FORWARD_STORAGE = Storage("move_forward", "F")
    val MOVE_BACKWARD_STORAGE = Storage("move_backward", "B")
    val MOVE_STOP_STORAGE = Storage("move_stop", "S")
    val TURN_RIGHT_STORAGE = Storage("turn_right", "R")
    val TURN_LEFT_STORAGE = Storage("turn_left", "L")

    val ACTION_1_STORAGE = Storage("action_1", "1")
    val ACTION_2_STORAGE = Storage("action_2", "2")
    val ACTION_3_STORAGE = Storage("action_3", "3")
    val ACTION_4_STORAGE = Storage("action_4", "4")
    val ACTION_5_STORAGE = Storage("action_5", "5")
    val ACTION_6_STORAGE = Storage("action_6", "6")
    val ACTION_7_STORAGE = Storage("action_7", "7")
    val ACTION_8_STORAGE = Storage("action_8", "8")

}