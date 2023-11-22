package com.gaurav145.counterplus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gaurav145.counter.R
import com.gaurav145.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var counter = 0;
    private var savedSoundValue: String? = null
    private var savedSoundToggle = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //    val count = Count(0)
        binding.count = counter

        binding.buttonPlus.setOnClickListener {
            counterIncrement()
        }

        binding.tvCount.setOnClickListener {
            counterIncrement()
        }
        binding.buttonMinus.setOnClickListener {
            binding.count = counter--
        }

    }

    override fun onResume() {
        val sharedPref: SharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        savedSoundValue = sharedPref.getString("SoundValue", "10")
        savedSoundToggle = sharedPref.getBoolean("SoundToggle", false)
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                counter = 0
                binding.count = 0
            }
            R.id.action_sound -> {
                //  soundCheck()
                val intent: Intent = Intent(this, MenuSetting::class.java)
                intent.putExtra("savedSoundValue", savedSoundValue)
                intent.putExtra("savedSoundToggle", savedSoundToggle)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun counterIncrement() {
        if (counter == savedSoundValue?.toInt() && savedSoundToggle) {
            val notification: Uri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        200,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(200)
            }
        }
        binding.count = counter++
    }

    companion object {
        const val SHARED_PREFS = "sharedPrefs"
    }
}