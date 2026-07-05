package com.rambo.ramcryptr

import android.content.Context

object AppSecurityManager {

    private const val PREF =
        "ramcryptr_security"

    private const val SECRET =
        "secret_code_enabled"

    private const val HIDE =
        "hide_launcher_enabled"

    fun isSecretDialerEnabled(
        context: Context
    ): Boolean {

        return context
            .getSharedPreferences(
                PREF,
                Context.MODE_PRIVATE
            )
            .getBoolean(
                SECRET,
                true
            )
    }

    fun setSecretDialerEnabled(
        context: Context,
        enabled: Boolean
    ) {

        context
            .getSharedPreferences(
                PREF,
                Context.MODE_PRIVATE
            )
            .edit()
            .putBoolean(
                SECRET,
                enabled
            )
            .apply()
    }

    fun isLauncherHiddenPref(
        context: Context
    ): Boolean {

        return context
            .getSharedPreferences(
                PREF,
                Context.MODE_PRIVATE
            )
            .getBoolean(
                HIDE,
                false
            )
    }

    fun setLauncherHiddenPref(
        context: Context,
        hidden: Boolean
    ) {

        context
            .getSharedPreferences(
                PREF,
                Context.MODE_PRIVATE
            )
            .edit()
            .putBoolean(
                HIDE,
                hidden
            )
            .apply()
    }
}
