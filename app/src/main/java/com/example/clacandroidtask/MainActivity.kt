package com.example.clacandroidtask

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {

        var numbers = findViewById<TextView>(R.id.screen_write)

        var result = findViewById<TextView>(R.id.result)


        when (view!!.id) {

            R.id.zero_btn -> numbers.text = numbers.text.toString() + "0"
            R.id.one_btn -> numbers.text = numbers.text.toString() + "1"
            R.id.two_btn -> numbers.text = numbers.text.toString() + "2"
            R.id.three_btn -> numbers.text = numbers.text.toString() + "3"
            R.id.four_btn -> numbers.text = numbers.text.toString() + "4"
            R.id.five_btn -> numbers.text = numbers.text.toString() + "5"
            R.id.sex_btn -> numbers.text = numbers.text.toString() + "6"
            R.id.seven_btn -> numbers.text = numbers.text.toString() + "7"
            R.id.eight_btn -> numbers.text = numbers.text.toString() + "8"
            R.id.nine_btn -> numbers.text = numbers.text.toString() + "9"

            R.id.dot_btn -> numbers.text = numbers.text.toString() + "."

            R.id.equal_btn -> if (!numbers.text.toString().isEmpty()) equal_Operation(
                numbers,
                result
            )

            R.id.plus_btn -> if (result.text.toString().isEmpty()) numbers.text =
                numbers.text.toString() + "+"
            else numbers.text = result.text.toString() + "+"

            R.id.mins_btn -> if (result.text.toString().isEmpty()) numbers.text =
                numbers.text.toString() + "-"
            else numbers.text = result.text.toString() + "-"

            R.id.multi_btn -> if (result.text.toString().isEmpty()) numbers.text =
                numbers.text.toString() + "*"
            else numbers.text = result.text.toString() + "*"

            R.id.divide_btn -> if (result.text.toString().isEmpty()) numbers.text =
                numbers.text.toString() + "/"
            else numbers.text = result.text.toString() + "/"

            R.id.moduls_btn -> Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show()

            R.id.clear_btn -> clearScreen(numbers, result)

        }
    }


    private fun clearScreen(numbers: TextView, result: TextView) {
        numbers.text = ""
        result.text = ""
    }

    private fun equal_Operation(numbers: TextView, result: TextView) {

        var ques: String = numbers.text.toString()
        ques = ques.replace("+", " + ")
        ques = ques.replace("-", " - ")
        ques = ques.replace("*", " * ")
        ques = ques.replace("/", " / ")

        var stringArray: List<String> = ques.split(" ")

        stringArray = operations(stringArray as ArrayList<String>, "*", "/")
        stringArray = operations(stringArray, "+", "-")
        result.text = stringArray.joinToString()

    }
}

fun operations(s: ArrayList<String>, a: String, b: String): ArrayList<String> {
    while (s.contains(a) || s.contains(b)) {
        var index_of_Multi_Or_sum = s.indexOf(a)
        val index_of_divide_Or_minus = s.indexOf(b)
        var sign = a[0]
        if (index_of_Multi_Or_sum > index_of_divide_Or_minus && index_of_divide_Or_minus != -1) {
            sign = b[0]
            index_of_Multi_Or_sum = index_of_divide_Or_minus
        }
        if (index_of_Multi_Or_sum == -1) {
            index_of_Multi_Or_sum = index_of_divide_Or_minus
            sign = b[0]
        }

        val num1 = s[index_of_Multi_Or_sum - 1].toDouble()
        val num2 = s[index_of_divide_Or_minus + 1].toDouble()

        var result = 0.0
        result =
            if (sign == '*') num1 * num2
            else if (sign == '/') num1 / num2
            else if (sign == '+') num1 + num2
            else num1 - num2

        s[index_of_Multi_Or_sum] = result.toString()
        s.removeAt(index_of_Multi_Or_sum + 1)
        s.removeAt(index_of_Multi_Or_sum - 1)
    }
    return s
}
