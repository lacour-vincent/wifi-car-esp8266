package com.lacour.vincent.wificaresp8266.screen

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.lacour.vincent.wificaresp8266.R
import com.lacour.vincent.wificaresp8266.databinding.WelcomeActivityBinding


class Welcome : AppCompatActivity() {

    private lateinit var binding: WelcomeActivityBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_welcome))

        binding.menuButton.setOnClickListener {
            val intent = Intent(this, ButtonControl::class.java)
            startActivity(intent)
            this@Welcome.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
        }

        binding.menuAccelerometer.setOnClickListener {
            val intent = Intent(this, AccelerometerControl::class.java)
            startActivity(intent)
            this@Welcome.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
        }

        binding.menuVoice.setOnClickListener {
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
