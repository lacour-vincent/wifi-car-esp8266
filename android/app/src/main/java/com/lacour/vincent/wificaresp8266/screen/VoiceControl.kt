package com.lacour.vincent.wificaresp8266.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.lacour.vincent.wificaresp8266.connector.CarConnector
import kotlinx.android.synthetic.main.voice_control_activity.*
import android.media.AudioManager
import com.lacour.vincent.wificaresp8266.R
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*
import android.speech.RecognizerIntent
import android.content.Intent
import android.widget.Toast

import android.app.Activity


class VoiceControl : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var carConnector: CarConnector

    private lateinit var tts: TextToSpeech
    private lateinit var mAudioManager: AudioManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.voice_control_activity)
        setSupportActionBar(findViewById(R.id.toolbar_voice_control))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                title = getString(R.string.activity_voice)
            }
        }

        carConnector = CarConnector(this@VoiceControl)

        tts = TextToSpeech(this, this)
        mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager


        val pm = packageManager
        val activities =
            pm.queryIntentActivities(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0)
        val hasVoiceRecognizer: Boolean = activities.size != 0
        if (hasVoiceRecognizer) {
            action_voice.setOnClickListener { onVoiceAction() }
        } else {
            action_voice.setOnClickListener {
                Toast.makeText(
                    this,
                    "You do not have a speech recognizer installed on your device",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        action_button_1.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_2.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_3.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_4.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_5.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_6.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_7.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        action_button_8.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }


    }

    public override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("TTS - onInit", "This Language is not supported")
            }
        } else {
            Log.e("TTS - onInit", "Initilization Failed!")
        }
    }

    private fun startVoiceRecognitionIntent() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en_US")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.VoicePrompt))
        startActivityForResult(intent, 20100)
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun onVoiceAction() {
        startVoiceRecognitionIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 20100 && resultCode == Activity.RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            Toast.makeText(this, matches.toString(), Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
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


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishActivity()
        }
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            mAudioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI
            );
        }
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            mAudioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI
            );
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_voice_control_menu, menu)
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
        this@VoiceControl.overridePendingTransition(
            R.anim.anim_slide_in_right,
            R.anim.anim_slide_out_right
        )
    }

    private fun showInformationDialog() {
        val builder =
            AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogTheme))
        with(builder) {
            setTitle("title")
            setMessage("message")
            setPositiveButton(getString(R.string.ok)) { _, _ -> }
            show()
        }
    }

}