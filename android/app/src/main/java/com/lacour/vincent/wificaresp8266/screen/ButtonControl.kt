package com.lacour.vincent.wificaresp8266.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button

import android.widget.Toast
import androidx.core.content.ContextCompat
import com.lacour.vincent.wificaresp8266.R
import com.lacour.vincent.wificaresp8266.connector.CarConnector
import kotlinx.android.synthetic.main.button_control_activity.*

class ButtonControl : AppCompatActivity() {

    lateinit var carConnector: CarConnector;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button_control_activity)
        setSupportActionBar(findViewById(R.id.toolbar_button_control))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = "Button" // getString(R.string.)
            }
        }

        carConnector = CarConnector(this@ButtonControl)

        arrow_up.setOnTouchListener { v: View, event: MotionEvent -> onTouchArrow(v, event) }
        arrow_right.setOnTouchListener { v: View, event: MotionEvent -> onTouchArrow(v, event) }
        arrow_down.setOnTouchListener { v: View, event: MotionEvent -> onTouchArrow(v, event) }
        arrow_left.setOnTouchListener { v: View, event: MotionEvent -> onTouchArrow(v, event) }

        action_button_1.setOnClickListener { v: View -> onClickAction(v) }
        action_button_2.setOnClickListener { v: View -> onClickAction(v) }
        action_button_3.setOnClickListener { v: View -> onClickAction(v) }
        action_button_4.setOnClickListener { v: View -> onClickAction(v) }
        action_button_5.setOnClickListener { v: View -> onClickAction(v) }
        action_button_6.setOnClickListener { v: View -> onClickAction(v) }
        action_button_7.setOnClickListener { v: View -> onClickAction(v) }
        action_button_8.setOnClickListener { v: View -> onClickAction(v) }
    }

    private fun onTouchArrow(v: View, event: MotionEvent): Boolean {
        val isTouchDown = event.action == MotionEvent.ACTION_DOWN
        val isTouchUp = event.action == MotionEvent.ACTION_UP;
        if (isTouchDown) {
            when (v.id) {
                R.id.arrow_up -> {
                    carConnector.moveForward()
                    arrow_up.setBackgroundResource(R.drawable.arrow_up_pressed)
                }
                R.id.arrow_down -> {
                    carConnector.moveBackward()
                    arrow_down.setBackgroundResource(R.drawable.arrow_down_pressed)
                }
                R.id.arrow_right -> {
                    carConnector.turnRight()
                    arrow_right.setBackgroundResource(R.drawable.arrow_right_pressed)
                }
                R.id.arrow_left -> {
                    carConnector.turnLeft()
                    arrow_left.setBackgroundResource(R.drawable.arrow_left_pressed)
                }
            }
            return true
        }
        if (isTouchUp) {
            carConnector.stopMoving();
            when (v.id) {
                R.id.arrow_up -> arrow_up.setBackgroundResource(R.drawable.arrow_up)
                R.id.arrow_down -> arrow_down.setBackgroundResource(R.drawable.arrow_down)
                R.id.arrow_right -> arrow_right.setBackgroundResource(R.drawable.arrow_right)
                R.id.arrow_left -> arrow_left.setBackgroundResource(R.drawable.arrow_left)
            }
            return true
        }
        return false;
    }

    private fun onClickAction(v: View): Boolean {
        when (v.id) {
            R.id.action_button_1 -> carConnector.actionOne()
            R.id.action_button_2 -> carConnector.actionTwo()
            R.id.action_button_3 -> carConnector.actionThree()
            R.id.action_button_4 -> carConnector.actionFour()
            R.id.action_button_5 -> carConnector.actionFive()
            R.id.action_button_6 -> carConnector.actionSix()
            R.id.action_button_7 -> carConnector.actionSeven()
            R.id.action_button_8 -> carConnector.actionHeight()
        }
        return true;
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
                Toast.makeText(this, "Information", Toast.LENGTH_LONG).show()
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

}