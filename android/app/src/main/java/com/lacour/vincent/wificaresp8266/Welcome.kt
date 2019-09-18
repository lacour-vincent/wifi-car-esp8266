package com.lacour.vincent.wificaresp8266

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

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
                true
            }
            R.id.action_settings -> {
                val settingsIntent = Intent(this, Settings::class.java)
                startActivity(settingsIntent)
                this@Welcome.overridePendingTransition(
                    R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
