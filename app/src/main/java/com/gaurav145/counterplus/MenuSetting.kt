package com.gaurav145.counterplus

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gaurav145.counter.databinding.ActivityMenuSettingBinding

class MenuSetting : AppCompatActivity() {
    lateinit var binding: ActivityMenuSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)

        val message = intent.getStringExtra("savedSoundValue")
        val toggleValue = intent.getBooleanExtra("savedSoundToggle", true)
        binding.etSound.setText(message)
        binding.toggleSound.isChecked = toggleValue
        binding.saveSetting.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("SoundValue", binding.etSound.text.toString())
                putBoolean("SoundToggle", binding.toggleSound.isChecked)
            }.apply()
            Toast.makeText(this, "Saved!!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
