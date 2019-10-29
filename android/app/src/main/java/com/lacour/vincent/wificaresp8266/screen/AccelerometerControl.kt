package com.lacour.vincent.wificaresp8266.screen

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.lacour.vincent.wificaresp8266.R
import com.lacour.vincent.wificaresp8266.connector.CarConnector
import kotlinx.android.synthetic.main.accelerometer_control_activity.*

class AccelerometerControl : AppCompatActivity(), SensorEventListener {

    private lateinit var carConnector: CarConnector
    private lateinit var mSensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accelerometer_control_activity)
        setSupportActionBar(findViewById(R.id.toolbar_accelerometer_control))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        carConnector = CarConnector(this@AccelerometerControl)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        action_button_1.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_2.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_3.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_4.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_5.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_6.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_7.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_8.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        /* Do Nothing */
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 == null) return
        val axisX = p0.values[0]
        val axisY = p0.values[1]

        val isUpOrientation: Boolean = ((axisX > -3) && (axisX < 3) && (axisY < -3))
        val isDownOrientation: Boolean = ((axisX > -3) && (axisX < 3) && (axisY > 3))
        val isRightOrientation: Boolean = ((axisY > -3) && (axisY < 3) && (axisX < -3))
        val isLeftOrientation: Boolean = ((axisY > -3) && (axisY < 3) && (axisX > 3))

        when {
            isUpOrientation -> {
                carConnector.moveForward()
                arrow_up.setBackgroundResource(R.drawable.arrow_up_pressed)
                arrow_down.setBackgroundResource(R.drawable.arrow_down)
                arrow_right.setBackgroundResource(R.drawable.arrow_right)
                arrow_left.setBackgroundResource(R.drawable.arrow_left)
            }
            isDownOrientation -> {
                carConnector.moveBackward()
                arrow_up.setBackgroundResource(R.drawable.arrow_up)
                arrow_down.setBackgroundResource(R.drawable.arrow_down_pressed)
                arrow_right.setBackgroundResource(R.drawable.arrow_right)
                arrow_left.setBackgroundResource(R.drawable.arrow_left)
            }
            isRightOrientation -> {
                carConnector.turnRight()
                arrow_up.setBackgroundResource(R.drawable.arrow_up)
                arrow_down.setBackgroundResource(R.drawable.arrow_down)
                arrow_right.setBackgroundResource(R.drawable.arrow_right_pressed)
                arrow_left.setBackgroundResource(R.drawable.arrow_left)
            }
            isLeftOrientation -> {
                carConnector.turnLeft()
                arrow_up.setBackgroundResource(R.drawable.arrow_up)
                arrow_down.setBackgroundResource(R.drawable.arrow_down)
                arrow_right.setBackgroundResource(R.drawable.arrow_right)
                arrow_left.setBackgroundResource(R.drawable.arrow_left_pressed)
            }
            else -> {
                carConnector.stopMoving()
                arrow_up.setBackgroundResource(R.drawable.arrow_up)
                arrow_down.setBackgroundResource(R.drawable.arrow_down)
                arrow_right.setBackgroundResource(R.drawable.arrow_right)
                arrow_left.setBackgroundResource(R.drawable.arrow_left)
            }
        }
    }


    private fun onTouchAction(v: View, event: MotionEvent): Boolean {
        val isTouchDown = event.action == MotionEvent.ACTION_DOWN
        val isTouchUp = event.action == MotionEvent.ACTION_UP
        if (isTouchDown) {
            val orangeColor = ContextCompat.getColor(this, R.color.colorOrange)
            when (v.id) {
                R.id.action_button_1 -> {
                    carConnector.actionOne()
                    action_button_1.setTextColor(orangeColor)
                }
                R.id.action_button_2 -> {
                    carConnector.actionTwo()
                    action_button_2.setTextColor(orangeColor)
                }
                R.id.action_button_3 -> {
                    carConnector.actionThree()
                    action_button_3.setTextColor(orangeColor)
                }
                R.id.action_button_4 -> {
                    carConnector.actionFour()
                    action_button_4.setTextColor(orangeColor)
                }
                R.id.action_button_5 -> {
                    carConnector.actionFive()
                    action_button_5.setTextColor(orangeColor)
                }
                R.id.action_button_6 -> {
                    carConnector.actionSix()
                    action_button_6.setTextColor(orangeColor)
                }
                R.id.action_button_7 -> {
                    carConnector.actionSeven()
                    action_button_7.setTextColor(orangeColor)
                }
                R.id.action_button_8 -> {
                    carConnector.actionHeight()
                    action_button_8.setTextColor(orangeColor)
                }
            }
            return true
        }
        if (isTouchUp) {
            val whiteColor = ContextCompat.getColor(this, R.color.colorWhite)
            when (v.id) {
                R.id.action_button_1 -> action_button_1.setTextColor(whiteColor)
                R.id.action_button_2 -> action_button_2.setTextColor(whiteColor)
                R.id.action_button_3 -> action_button_3.setTextColor(whiteColor)
                R.id.action_button_4 -> action_button_4.setTextColor(whiteColor)
                R.id.action_button_5 -> action_button_5.setTextColor(whiteColor)
                R.id.action_button_6 -> action_button_6.setTextColor(whiteColor)
                R.id.action_button_7 -> action_button_7.setTextColor(whiteColor)
                R.id.action_button_8 -> action_button_8.setTextColor(whiteColor)
            }
            return true
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(
            this,
            accelerometerSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishActivity()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_accelerometer_control_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finishActivity()
                true
            }
            R.id.action_information -> {
                showInformationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun finishActivity() {
        finish()
        this@AccelerometerControl.overridePendingTransition(
            R.anim.anim_slide_in_right,
            R.anim.anim_slide_out_right
        )
    }

    private fun showInformationDialog() {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogTheme))
        with(builder) {
            setTitle(getString(R.string.accelerometer_dialog_title))
            setMessage(getString(R.string.accelerometer_dialog_message))
            setPositiveButton(getString(R.string.ok)) { _, _ -> }
            show()
        }
    }

}