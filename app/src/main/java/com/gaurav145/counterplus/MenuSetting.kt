package com.gaurav145.counterplus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gaurav145.counter.databinding.ActivityMenuSettingBinding
import com.gaurav145.counterplus.MainActivity.Companion.SHARED_PREFS

class MenuSetting : AppCompatActivity() {
    lateinit var binding: ActivityMenuSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("savedSoundValue")
        val toggleValue = intent.getBooleanExtra("savedSoundToggle", true)
        binding.etSound.setText(message)
        binding.toggleSound.isChecked = toggleValue
        binding.saveSetting.setOnClickListener {
            val sharedPref: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("SoundValue", binding.etSound.text.toString())
                putBoolean("SoundToggle", binding.toggleSound.isChecked)
                apply()
            }
            Toast.makeText(this, "Saved!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
