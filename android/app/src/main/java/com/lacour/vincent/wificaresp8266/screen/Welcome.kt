package com.lacour.vincent.wificaresp8266.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.provider.Settings
import android.widget.Toast
import com.lacour.vincent.wificaresp8266.R

import kotlinx.android.synthetic.main.welcome_activity.*

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        setSupportActionBar(findViewById(R.id.toolbar_welcome))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                title = getString(R.string.app_name)
            }
        }

        menu_button.setOnClickListener {
            val intent = Intent(this, ButtonControl::class.java)
            startActivity(intent)
            this@Welcome.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
        }

        menu_accelerometer.setOnClickListener {
            val intent = Intent(this, AccelerometerControl::class.java)
            startActivity(intent)
            this@Welcome.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
        }

        menu_voice.setOnClickListener {
            val intent = Intent(this, VoiceControl::class.java)
            startActivity(intent)
            this@Welcome.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_welcome_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_wifi -> {
                val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                startActivity(intent)
                this@Welcome.overridePendingTransition(
                    R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left
                )
                true
            }
            R.id.action_configuration -> {
                val intent = Intent(this, Configuration::class.java)
                startActivity(intent)
                this@Welcome.overridePendingTransition(
                    R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
