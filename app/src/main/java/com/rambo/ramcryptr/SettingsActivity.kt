package com.rambo.ramcryptr

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences(
            "ramcryptr_security",
            MODE_PRIVATE
        )

        val secretSwitch =
            findViewById<Switch>(R.id.switchSecretDialer)

        secretSwitch.isChecked =
            prefs.getBoolean("secret_code_enabled", true)

        secretSwitch.setOnCheckedChangeListener { _, isChecked ->

            prefs.edit()
                .putBoolean(
                    "secret_code_enabled",
                    isChecked
                )
                .apply()
        }
    }
}
