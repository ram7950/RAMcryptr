package com.rambo.ramcryptr

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

object LauncherManager {

    private const val ALIAS =
        "com.rambo.ramcryptr.LauncherEntry"

    fun hideLauncher(context: Context) {

        context.packageManager.setComponentEnabledSetting(

            ComponentName(context, ALIAS),

            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,

            PackageManager.DONT_KILL_APP
        )
    }

    fun showLauncher(context: Context) {

        context.packageManager.setComponentEnabledSetting(

            ComponentName(context, ALIAS),

            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,

            PackageManager.DONT_KILL_APP
        )
    }

    fun isLauncherHidden(context: Context): Boolean {

        val state = context.packageManager
            .getComponentEnabledSetting(
                ComponentName(context, ALIAS)
            )

        return state ==
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    }

    fun setLauncherHidden(
        context: Context,
        hidden: Boolean
    ) {

        if (hidden) {
            hideLauncher(context)
        } else {
            showLauncher(context)
        }
    }

}
