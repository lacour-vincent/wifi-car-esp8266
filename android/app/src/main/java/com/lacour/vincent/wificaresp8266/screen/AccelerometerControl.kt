package com.lacour.vincent.wificaresp8266.screen

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lacour.vincent.wificaresp8266.R
import com.lacour.vincent.wificaresp8266.connector.CarConnector
import com.lacour.vincent.wificaresp8266.databinding.AccelerometerControlActivityBinding

class AccelerometerControl : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: AccelerometerControlActivityBinding
    private lateinit var carConnector: CarConnector
    private lateinit var mSensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccelerometerControlActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
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


        binding.actionButton1.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton2.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton3.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton4.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton5.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton6.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton7.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton8.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
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
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up_pressed)
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down)
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right)
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left)
            }

            isDownOrientation -> {
                carConnector.moveBackward()
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up)
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down_pressed)
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right)
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left)
            }

            isRightOrientation -> {
                carConnector.turnRight()
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up)
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down)
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right_pressed)
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left)
            }

            isLeftOrientation -> {
                carConnector.turnLeft()
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up)
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down)
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right)
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left_pressed)
            }

            else -> {
                carConnector.stopMoving()
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up)
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down)
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right)
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left)
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
                    binding.actionButton1.setTextColor(orangeColor)
                }

                R.id.action_button_2 -> {
                    carConnector.actionTwo()
                    binding.actionButton2.setTextColor(orangeColor)
                }

                R.id.action_button_3 -> {
                    carConnector.actionThree()
                    binding.actionButton3.setTextColor(orangeColor)
                }

                R.id.action_button_4 -> {
                    carConnector.actionFour()
                    binding.actionButton4.setTextColor(orangeColor)
                }

                R.id.action_button_5 -> {
                    carConnector.actionFive()
                    binding.actionButton5.setTextColor(orangeColor)
                }

                R.id.action_button_6 -> {
                    carConnector.actionSix()
                    binding.actionButton6.setTextColor(orangeColor)
                }

                R.id.action_button_7 -> {
                    carConnector.actionSeven()
                    binding.actionButton7.setTextColor(orangeColor)
                }

                R.id.action_button_8 -> {
                    carConnector.actionHeight()
                    binding.actionButton8.setTextColor(orangeColor)
                }
            }
            return true
        }
        if (isTouchUp) {
            val whiteColor = ContextCompat.getColor(this, R.color.colorWhite)
            when (v.id) {
                R.id.action_button_1 -> binding.actionButton1.setTextColor(whiteColor)
                R.id.action_button_2 -> binding.actionButton2.setTextColor(whiteColor)
                R.id.action_button_3 -> binding.actionButton3.setTextColor(whiteColor)
                R.id.action_button_4 -> binding.actionButton4.setTextColor(whiteColor)
                R.id.action_button_5 -> binding.actionButton5.setTextColor(whiteColor)
                R.id.action_button_6 -> binding.actionButton6.setTextColor(whiteColor)
                R.id.action_button_7 -> binding.actionButton7.setTextColor(whiteColor)
                R.id.action_button_8 -> binding.actionButton8.setTextColor(whiteColor)
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