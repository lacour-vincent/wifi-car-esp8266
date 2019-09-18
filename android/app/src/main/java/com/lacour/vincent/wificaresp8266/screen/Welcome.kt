package com.lacour.vincent.wificaresp8266.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.provider.Settings;
import com.lacour.vincent.wificaresp8266.R

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        setSupportActionBar(findViewById(R.id.toolbar_welcome))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_welcome_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_wifi -> {
                val wifi_intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                startActivity(wifi_intent)
                true
            }
            R.id.action_settings -> {
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
