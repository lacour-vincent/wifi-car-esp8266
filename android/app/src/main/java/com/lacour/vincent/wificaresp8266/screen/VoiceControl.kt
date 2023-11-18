package com.lacour.vincent.wificaresp8266.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lacour.vincent.wificaresp8266.R
import com.lacour.vincent.wificaresp8266.connector.CarConnector
import com.lacour.vincent.wificaresp8266.databinding.VoiceControlActivityBinding
import com.lacour.vincent.wificaresp8266.storage.Preferences

class VoiceControl : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE: Int = 20100
        const val MAX_MATCHES = 3
        const val DELAY_STOP: Long = 3000
    }

    private lateinit var binding: VoiceControlActivityBinding
    private lateinit var carConnector: CarConnector
    private lateinit var preferences: Preferences

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VoiceControlActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_voice_control))

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        carConnector = CarConnector(this@VoiceControl)
        preferences = Preferences(this@VoiceControl)


        val activities =
            packageManager.queryIntentActivities(
                Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                0
            )
        val hasVoiceRecognizer: Boolean = activities.size != 0
        if (hasVoiceRecognizer) {
            binding.actionVoice.setOnClickListener { startVoiceRecognitionIntent() }
        } else {
            binding.actionVoice.setOnClickListener {
                Toast.makeText(
                    this,
                    getString(R.string.speech_recognizer_not_found),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.actionButton1.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton2.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton3.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton4.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton5.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton6.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton7.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
        binding.actionButton8.setOnTouchListener { v: View, e: MotionEvent -> onTouchAction(v, e) }
    }


    private fun startVoiceRecognitionIntent() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        val language: String = preferences.getSpeechRecognitionLanguageValue()
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_recognition_prompt))
        startActivityForResult(intent, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data !== null) {
                val matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                handleVoiceRecognitionMatches(matches!!)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun handleVoiceRecognitionMatches(matches: List<String>) {
        val forwardKeyword: String = preferences.getKeywordForwardValue()
        val backwardKeyword: String = preferences.getKeywordBackwardValue()
        val rightKeyword: String = preferences.getKeywordRightValue()
        val leftKeyword: String = preferences.getKeywordLeftValue()
        binding.actionVoice.isEnabled = false

        when {
            isKeywordRecognized(matches, forwardKeyword) -> {
                carConnector.moveForward()
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up_pressed)
                stopMovingDelayed()
            }

            isKeywordRecognized(matches, backwardKeyword) -> {
                carConnector.moveBackward()
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down_pressed)
                stopMovingDelayed()
            }

            isKeywordRecognized(matches, rightKeyword) -> {
                carConnector.turnRight()
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right_pressed)
                stopMovingDelayed()
            }

            isKeywordRecognized(matches, leftKeyword) -> {
                carConnector.turnLeft()
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left_pressed)
                stopMovingDelayed()
            }

            else -> {
                Toast.makeText(
                    this,
                    getString(R.string.speech_recognizer_no_matches),
                    Toast.LENGTH_LONG
                ).show()
                binding.actionVoice.isEnabled = true
            }
        }
    }


    private fun isKeywordRecognized(matches: List<String>, keyword: String): Boolean {
        if (matches.isEmpty()) return false
        val subEndIndex: Int = if (matches.size > MAX_MATCHES - 1) MAX_MATCHES else matches.size
        val mainMatches: List<String> = matches.subList(0, subEndIndex)
        return mainMatches.any { m -> m.contains(keyword) }
    }

    private fun stopMovingDelayed() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                carConnector.stopMoving()
                binding.actionVoice.isEnabled = true
                binding.arrowUp.setBackgroundResource(R.drawable.arrow_up)
                binding.arrowDown.setBackgroundResource(R.drawable.arrow_down)
                binding.arrowRight.setBackgroundResource(R.drawable.arrow_right)
                binding.arrowLeft.setBackgroundResource(R.drawable.arrow_left)
            },
            DELAY_STOP
        )
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
        val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogTheme))
        with(builder) {
            setTitle(getString(R.string.voice_dialog_title))
            setMessage(getString(R.string.voice_dialog_message))
            setPositiveButton(getString(R.string.ok)) { _, _ -> }
            show()
        }
    }

}