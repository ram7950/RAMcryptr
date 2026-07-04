package com.rambo.ramcryptr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class HiddenEntryActivity : Activity() {

    companion object {
        private const val ENTRY_PASSWORD = "4207950A"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hidden_entry)

        val password =
            findViewById<EditText>(R.id.editPassword)

        val toggle =
            findViewById<ImageButton>(R.id.btnTogglePassword)

        val open =
            findViewById<Button>(R.id.btnOpen)

        val cancel =
            findViewById<Button>(R.id.btnCancel)

        var visible = false

        fun openApp() {

            if (password.text.toString().trim() == ENTRY_PASSWORD) {

                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).apply {
                        addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    }
                )

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid password",
                    Toast.LENGTH_SHORT
                ).show()

                password.setText("")
                password.requestFocus()
            }
        }

        toggle.setOnClickListener {

            visible = !visible

            if (visible) {

                password.inputType =
                    InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

            } else {

                password.inputType =
                    InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            password.setSelection(password.text.length)
        }

        open.setOnClickListener {
            openApp()
        }

        password.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                openApp()
                true
            } else {
                false
            }
        }

        cancel.setOnClickListener {
            finish()
        }
    }
}
