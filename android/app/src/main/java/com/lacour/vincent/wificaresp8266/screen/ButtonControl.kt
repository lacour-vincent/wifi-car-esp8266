package com.lacour.vincent.wificaresp8266.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast
import com.lacour.vincent.wificaresp8266.R

class ButtonControl : AppCompatActivity() {

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