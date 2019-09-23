package com.lacour.vincent.wificaresp8266.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*

import android.widget.Toast
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



        action_button_1.setOnClickListener { carConnector.moveForward() }
        action_button_2.setOnClickListener { carConnector.moveBackward() }
        action_button_3.setOnClickListener { carConnector.turnLeft() }
        action_button_4.setOnClickListener { carConnector.turnRight() }
        action_button_5.setOnClickListener { carConnector.stopMoving() }
        action_button_6.setOnClickListener {}
        action_button_7.setOnClickListener {}
        action_button_8.setOnClickListener {}

        arrow_up.setOnTouchListener { v: View?, event: MotionEvent? -> onTouchArrow(v, event) }
        arrow_right.setOnTouchListener { v: View?, event: MotionEvent? -> onTouchArrow(v, event) }
        arrow_down.setOnTouchListener { v: View?, event: MotionEvent? -> onTouchArrow(v, event) }
        arrow_left.setOnTouchListener { v: View?, event: MotionEvent? -> onTouchArrow(v, event) }
    }

    private fun onTouchArrow(v: View?, event: MotionEvent?): Boolean {
        Toast.makeText(this, "touched", Toast.LENGTH_SHORT).show()
        return true
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