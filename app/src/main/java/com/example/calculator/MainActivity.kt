package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.kaen.dagger.ExpressionParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numPadButtons = registerNumPadButtons()

        for (button in numPadButtons) {
            button.setOnClickListener {
                onNumPadClick(it)
            }
        }
    }

    private fun onNumPadClick(it: View?) {
        val itButton = it as Button

        if (isResult) {
            calculationDisplay.text = clearString()
            isResult = false
        }

        val newText = when (itButton.text) {
            "=" -> calculate(calculationDisplay.text.toString())
            "clear" -> clearString()
            "x" -> resources.getString(
                R.string.calculation_display_value_default,
                calculationDisplay.text,
                "*"
            )
            else -> resources.getString(
                R.string.calculation_display_value_default,
                calculationDisplay.text,
                itButton.text
            )
        }

        calculationDisplay.text = newText
    }

    private fun clearString(): String {
        return ""
    }

    private fun calculate(text: String): String {
        val parser = ExpressionParser()
        isResult = true
        return parser.evaluate(text).toString()
    }

    private fun registerNumPadButtons(): Array<Button> {
        return arrayOf(
            numpad_0,
            numpad_1,
            numpad_2,
            numpad_3,
            numpad_4,
            numpad_5,
            numpad_6,
            numpad_7,
            numpad_8,
            numpad_9,
            numpad_point,
            numpad_equals,
            numpad_plus,
            numpad_minus,
            numpad_multiply,
            numpad_divide,
            numpad_clear
        )
    }
}