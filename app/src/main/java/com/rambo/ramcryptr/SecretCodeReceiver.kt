package com.rambo.ramcryptr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SecretCodeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val prefs = context.getSharedPreferences(
            "ramcryptr_security",
            Context.MODE_PRIVATE
        )

        if (!prefs.getBoolean("secret_code_enabled", true)) {
            return
        }

        val launch = Intent(
            context,
            MainActivity::class.java
        ).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_SINGLE_TOP
            )
        }

        context.startActivity(launch)
    }
}
