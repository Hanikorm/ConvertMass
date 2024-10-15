package com.hanikorm.convertmass

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val inputValue = findViewById<EditText>(R.id.editTextNumberDecimalFunt)
        val resultValue = findViewById<EditText>(R.id.editTextNumberDecResult)
        val clearButton = findViewById<Button>(R.id.buttonClear)
        val radioButtonStone = findViewById<RadioButton>(R.id.radioButtonSt)
        val radioButtonGram = findViewById<RadioButton>(R.id.radioButtonGr)
        val radioButtonKg = findViewById<RadioButton>(R.id.radioButtonKg)
        val radioButtonMg = findViewById<RadioButton>(R.id.radioButtonMg)
        clearButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Очистка данных")
                .setMessage("Вы уверены, что хотите очистить все данные?")
                .setPositiveButton("Да") { _, _ ->
                    inputValue.setText("")
                    resultValue.setText("")
                    radioButtonStone.isChecked = false
                    radioButtonGram.isChecked = false
                    radioButtonKg.isChecked = false
                    radioButtonMg.isChecked = false
                }
                .setNegativeButton("Отмена", null)
                .show()
        }
        radioButtonStone.setOnClickListener {
            convertTo()
        }
        radioButtonGram.setOnClickListener {
            convertTo()
        }
        radioButtonKg.setOnClickListener {
            convertTo()
        }
        radioButtonMg.setOnClickListener {
            convertTo()
        }
    }
    private fun convertTo() {
        val inputValue = findViewById<EditText>(R.id.editTextNumberDecimalFunt)
        val resultValue = findViewById<EditText>(R.id.editTextNumberDecResult)
        val radioButtonStone = findViewById<RadioButton>(R.id.radioButtonSt)
        val radioButtonGram = findViewById<RadioButton>(R.id.radioButtonGr)
        val radioButtonKg = findViewById<RadioButton>(R.id.radioButtonKg)
        val radioButtonMg = findViewById<RadioButton>(R.id.radioButtonMg)
        if (inputValue.text.toString().isEmpty()) {
            Toast.makeText(this, "Введите количество фунтов!", Toast.LENGTH_SHORT).show()
            radioButtonStone.isChecked = false
            radioButtonGram.isChecked = false
            radioButtonKg.isChecked = false
            radioButtonMg.isChecked = false
            return
        }
        val pounds: Double = inputValue.text.toString().toDouble()
        val result: Double = when {
            radioButtonStone.isChecked -> pounds / 14
            radioButtonGram.isChecked -> pounds * 453.592
            radioButtonKg.isChecked -> pounds * 0.453592
            radioButtonMg.isChecked -> pounds * 453592
            else -> 0.0
        }
        resultValue.setText(String.format("%.3f", result))
    }

}