package com.example.noter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notes = findViewById<TextView>(R.id.notes)
        val noteInput = findViewById<EditText>(R.id.noteInput)
        val errorText = findViewById<TextView>(R.id.errorText)
        val clearNotesButton = findViewById<Button>(R.id.clearNotesButton)

        var noteCount = 0

        noteInput.setOnKeyListener {
            _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (noteCount == R.integer.lineLimit) {
                    errorText.text = "Too many notes!"
                } else {
                    if (noteInput.text.isEmpty()) {
                        errorText.text = "Empty note!"
                    } else {
                        notes.text = getString(
                            R.string.new_note,
                            notes.text,
                            noteInput.text
                        )
                        noteInput.setText("")
                        noteCount += 1
                    }
                }
            }
            false
        }

        clearNotesButton.setOnClickListener {
            notes.text = ""
            noteCount = 0
        }
    }
}