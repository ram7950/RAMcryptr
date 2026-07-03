package com.rambo.ramcryptr

import android.content.Context
import android.os.Handler
import android.os.Looper
import java.io.File

object CacheCleanupManager {

    private const val MB = 1024L * 1024L

    fun cleanup(context: Context) {

        val cache = context.cacheDir
        val files = cache.listFiles() ?: return
        val now = System.currentTimeMillis()

        for (file in files) {

            if (!file.isFile) continue

            val name = file.name

            when {

                // Crash recovery
                name.startsWith("temp_enc_") ||
                name.startsWith("temp_dec_") -> {

                    try {
                        file.delete()
                    } catch (_: Exception) {
                    }
                }

                // Final decrypted files
                name.startsWith("dec_") -> {

                    val age = now - file.lastModified()

                    if (age >= expiryFor(file.length())) {

                        try {
                            file.delete()
                        } catch (_: Exception) {
                        }

                    }
                }
            }
        }
    }


    fun scheduleDelete(file: File) {

        val delay = expiryFor(file.length())

        Handler(Looper.getMainLooper()).postDelayed({

            try {
                if (file.exists()) {
                    file.delete()
                }
            } catch (_: Exception) {
            }

        }, delay)
    }


    private fun expiryFor(size: Long): Long {

        return when {

            size < 100L * MB ->
                5L * 60L * 1000L

            size < 1024L * MB ->
                15L * 60L * 1000L

            else ->
                30L * 60L * 1000L
        }
    }
}
