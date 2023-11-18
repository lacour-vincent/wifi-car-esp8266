package com.lacour.vincent.wificaresp8266.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lacour.vincent.wificaresp8266.R
import com.lacour.vincent.wificaresp8266.connector.CarConnector
import com.lacour.vincent.wificaresp8266.databinding.ButtonControlActivityBinding

class ButtonControl : AppCompatActivity() {

    private lateinit var binding: ButtonControlActivityBinding
    private lateinit var carConnector: CarConnector

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ButtonControlActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_button_control))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        carConnector = CarConnector(this@ButtonControl)

        binding.arrowUp.setOnTouchListener { v: View, e: MotionEvent -> onTouchArrow(v, e) }
        binding.arrowRight.setOnTouchListener { v: View, e: MotionEvent -> onTouchArrow(v, e) }
        binding.arrowDown.setOnTouchListener { v: View, e: MotionEvent -> onTouchArrow(v, e) }
        binding.arrowLeft.setOnTouchListener { v: View, e: MotionEvent -> onTouchArrow(v, e) }

        binding.actionButton1.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton2.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton3.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton4.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton5.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton6.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton7.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton8.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
    }

    private fun onTouchArrow(v: View, event: MotionEvent): Boolean {
        val isTouchDown = event.action == MotionEvent.ACTION_DOWN
        val isTouchUp = event.action == MotionEvent.ACTION_UP
        if (isTouchDown) {
            when (v.id) {
                R.id.arrow_up -> {
                    carConnector.moveForward()
                    binding.arrowUp.setBackgroundResource(R.drawable.arrow_up_pressed)
                }

                R.id.arrow_down -> {
                    carConnector.moveBackward()
                    binding.arrowDown.setBackgroundResource(R.drawable.arrow_down_pressed)
                }

                R.id.arrow_right -> {
                    carConnector.turnRight()
                    binding.arrowRight.setBackgroundResource(R.drawable.arrow_right_pressed)
                }

                R.id.arrow_left -> {
                    carConnector.turnLeft()
                    binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left_pressed)
                }
            }
            return true
        }
        if (isTouchUp) {
            carConnector.stopMoving()
            when (v.id) {
                R.id.arrow_up -> binding.arrowUp.setBackgroundResource(R.drawable.arrow_up)
                R.id.arrow_down -> binding.arrowDown.setBackgroundResource(R.drawable.arrow_down)
                R.id.arrow_right -> binding.arrowRight.setBackgroundResource(R.drawable.arrow_right)
                R.id.arrow_left -> binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left)
            }
            return true
        }
        return false
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishActivity()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_button_control_menu, menu)
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
        this@ButtonControl.overridePendingTransition(
            R.anim.anim_slide_in_right,
            R.anim.anim_slide_out_right
        )
    }

    private fun showInformationDialog() {
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogTheme))
        with(builder) {
            setTitle(getString(R.string.button_dialog_title))
            setMessage(getString(R.string.button_dialog_message))
            setPositiveButton(getString(R.string.ok)) { _, _ -> }
            show()
        }
    }

}