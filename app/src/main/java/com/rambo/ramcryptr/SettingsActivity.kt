package com.rambo.ramcryptr

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private var updatingUi = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        val secretSwitch =
            findViewById<Switch>(R.id.switchSecretDialer)

        val hideSwitch =
            findViewById<Switch>(R.id.switchHideLauncher)

        secretSwitch.isChecked =
            AppSecurityManager.isSecretDialerEnabled(this)

        hideSwitch.isChecked =
            AppSecurityManager.isLauncherHiddenPref(this)

        secretSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (updatingUi) return@setOnCheckedChangeListener

            if (AppSecurityManager.isLauncherHiddenPref(this)
                && !isChecked) {

                updatingUi = true
                secretSwitch.isChecked = true
                updatingUi = false

                Toast.makeText(
                    this,
                    "Disable 'Hide App Icon' first.",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnCheckedChangeListener
            }

            AppSecurityManager.setSecretDialerEnabled(
                this,
                isChecked
            )
        }

        hideSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (updatingUi) return@setOnCheckedChangeListener

            if (isChecked) {

                AlertDialog.Builder(this)
                    .setTitle("Hide App Icon")
                    .setMessage(
                        "After hiding the app icon you can still open RAMcryptr using:\n\n" +
                        "• Secret Dialer Code\n" +
                        "• Text Selection Entry (@XxXxXxXx@)\n\n" +
                        "Continue?"
                    )
                    .setNegativeButton("Cancel") { _, _ ->

                        updatingUi = true
                        hideSwitch.isChecked = false
                        updatingUi = false

                    }
                    .setPositiveButton("Hide") { _, _ ->

                        AppSecurityManager
                            .setSecretDialerEnabled(
                                this,
                                true
                            )

                        updatingUi = true
                        secretSwitch.isChecked = true
                        updatingUi = false

                        LauncherManager.setLauncherHidden(
                            this,
                            true
                        )

                        AppSecurityManager
                            .setLauncherHiddenPref(
                                this,
                                true
                            )

                        Toast.makeText(
                            this,
                            "App icon hidden.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .show()

            } else {

                LauncherManager.setLauncherHidden(
                    this,
                    false
                )

                AppSecurityManager
                    .setLauncherHiddenPref(
                        this,
                        false
                    )

                Toast.makeText(
                    this,
                    "App icon restored.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
