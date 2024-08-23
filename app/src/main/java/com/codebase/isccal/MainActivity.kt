package com.codebase.isccal

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.E
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cbrt
import kotlin.math.cosh
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sinh
import kotlin.math.sqrt
import kotlin.math.tanh

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var isRadians = true
    private var isSecondFunction = false
    private var currentInput: String = ""

    // Buttons declaration
    private lateinit var buttonAC: Button
    private lateinit var buttonPlusMinus: Button
    private lateinit var buttonPercent: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonSeven: Button
    private lateinit var buttonEight: Button
    private lateinit var buttonNine: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonFour: Button
    private lateinit var buttonFive: Button
    private lateinit var buttonSix: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonPlus: Button
    private lateinit var buttonZero: Button
    private lateinit var buttonDot: Button
    private lateinit var buttonEquals: Button
    private lateinit var buttonOpnBracket: Button
    private lateinit var buttonCloseBracket: Button
    private lateinit var buttonSquare: Button
    private lateinit var buttonCube: Button
    private lateinit var buttonPower: Button
    private lateinit var buttonExp: Button
    private lateinit var buttonTenPowerX: Button
    private lateinit var buttonSin: Button
    private lateinit var buttonCos: Button
    private lateinit var buttonTan: Button
    private lateinit var buttonSinh: Button
    private lateinit var buttonCosh: Button
    private lateinit var buttonTanh: Button
    private lateinit var buttonLog10: Button
    private lateinit var buttonLn: Button
    private lateinit var buttonFactorial: Button
    private lateinit var buttonPi: Button
    private lateinit var buttonE: Button
    private lateinit var buttonEE: Button
    private lateinit var button1x: Button
    private lateinit var button2sx: Button
    private lateinit var button3sx: Button
    private lateinit var buttonYsRootX: Button
    private lateinit var buttonRand: Button
    private lateinit var buttonMr: Button
    private lateinit var buttonMc: Button
    private lateinit var buttonMplus: Button
    private lateinit var buttonMminus: Button
    private lateinit var buttonRad: Button
    private lateinit var button2nd: Button

    private var memoryValue = 0.0
    private var lastNumeric = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check current orientation
        val orientation = resources.configuration.orientation

        // Load appropriate layout based on orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape)

            scientificButtonIds()
        } else {
            setContentView(R.layout.activity_main)
        }

        supportActionBar?.hide()

        display = findViewById(R.id.display)

        // Initialize buttons
        basicButtonIds()


    }

    private fun basicButtonIds() {
        // Initialize buttons
        buttonAC = findViewById(R.id.buttonAC)
        buttonPlusMinus = findViewById(R.id.buttonPlusMinus)
        buttonPercent = findViewById(R.id.buttonPercent)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonSeven = findViewById(R.id.buttonSeven)
        buttonEight = findViewById(R.id.buttonEight)
        buttonNine = findViewById(R.id.buttonNine)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonFour = findViewById(R.id.buttonFour)
        buttonFive = findViewById(R.id.buttonFive)
        buttonSix = findViewById(R.id.buttonSix)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonOne = findViewById(R.id.buttonOne)
        buttonTwo = findViewById(R.id.buttonTwo)
        buttonThree = findViewById(R.id.buttonThree)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonZero = findViewById(R.id.buttonZero)
        buttonDot = findViewById(R.id.buttonDot)
        buttonEquals = findViewById(R.id.buttonEquals)

        buttonOne.setOnClickListener {
            display.text = (display.text.toString() + "1")
        }

        buttonTwo.setOnClickListener {
            display.text = (display.text.toString() + "2")
        }

        buttonThree.setOnClickListener {
            display.text = (display.text.toString() + "3")
        }

        buttonFour.setOnClickListener {
            display.text = (display.text.toString() + "4")
        }

        buttonFive.setOnClickListener {
            display.text = (display.text.toString() + "5")
        }

        buttonSix.setOnClickListener {
            display.text = (display.text.toString() + "6")
        }

        buttonSeven.setOnClickListener {
            display.text = (display.text.toString() + "7")
        }

        buttonEight.setOnClickListener {
            display.text = (display.text.toString() + "8")
        }

        buttonNine.setOnClickListener {
            display.text = (display.text.toString() + "9")
        }

        buttonZero.setOnClickListener {
            display.text = (display.text.toString() + "0")
        }

        buttonDot.setOnClickListener {
            display.text = (display.text.toString() + ".")
        }

        buttonPlus.setOnClickListener {
            display.text = (display.text.toString() + "+")
        }

        buttonDivide.setOnClickListener {
            display.text = (display.text.toString() + "/")
        }

        buttonMinus.setOnClickListener {
            val str = display.text.toString()
            if (!str.get(index = str.length - 1).equals("-")) {
                display.text = (display.text.toString() + "-")
            }
        }

        buttonMultiply.setOnClickListener {
            val str = display.text.toString()
            if (!str.get(index = str.length - 1).equals("*")) {
                display.text = (display.text.toString() + "*")
            }
        }

        buttonPlusMinus.setOnClickListener {
            if (display.text.toString().isNotEmpty()) {
                onPlusMinus()
            }
        }

        buttonPercent.setOnClickListener {
            if (display.text.toString().isNotEmpty()) {
                onPercent()
            }
        }

        buttonEquals.setOnClickListener {
            val str: String = display.text.toString()

            val result: Double = evaluate(str)
            display.text = result.toString()
            currentInput = result.toString()
        }

        buttonAC.setOnClickListener {

            display.text = ""
        }

    }

    private fun scientificButtonIds() {
        buttonOpnBracket = findViewById(R.id.buttonopnBracket)
        buttonCloseBracket = findViewById(R.id.buttoncloseBracket)
        buttonSquare = findViewById(R.id.buttonSquare)
        buttonCube = findViewById(R.id.buttonCube)
        buttonPower = findViewById(R.id.buttonPower)
        buttonExp = findViewById(R.id.buttonExp)
        buttonTenPowerX = findViewById(R.id.buttonTenPowerX)
        buttonSin = findViewById(R.id.buttonSin)
        buttonCos = findViewById(R.id.buttonCos)
        buttonTan = findViewById(R.id.buttonTan)
        buttonSinh = findViewById(R.id.buttonSinh)
        buttonCosh = findViewById(R.id.buttonCosh)
        buttonTanh = findViewById(R.id.buttonTanh)
        buttonLog10 = findViewById(R.id.buttonLog10)
        buttonLn = findViewById(R.id.buttonln)
        buttonFactorial = findViewById(R.id.buttonFactorial)
        buttonPi = findViewById(R.id.buttonPi)
        buttonE = findViewById(R.id.buttone)
        buttonEE = findViewById(R.id.buttonEE)
        button1x = findViewById(R.id.button1x)
        button2sx = findViewById(R.id.button2sx)
        button3sx = findViewById(R.id.button3sx)
        buttonYsRootX = findViewById(R.id.buttonysx)
        buttonRand = findViewById(R.id.buttonRand)
        buttonMr = findViewById(R.id.buttonMr)
        buttonMc = findViewById(R.id.buttonMc)
        buttonMplus = findViewById(R.id.buttonMplus)
        buttonMminus = findViewById(R.id.buttonMminus)
        buttonRad = findViewById(R.id.buttonRad)
        button2nd = findViewById(R.id.button2nd)


        // Set click listeners for scientific buttons
        buttonOpnBracket.setOnClickListener {
            display.text = (display.text.toString() + "(")
        }
        buttonCloseBracket.setOnClickListener {
            display.text = (display.text.toString() + ")")
        }

        buttonPi.setOnClickListener {
            display.text = (display.text.toString() + "3.142")
        }

        buttonSin.setOnClickListener {

            if (isSecondFunction) {
                display.text = (display.text.toString() + "asin")

            } else {

                display.text = (display.text.toString() + buttonSin.text.toString())
            }

        }
        buttonCos.setOnClickListener {
            if (isSecondFunction) {
                display.text = (display.text.toString() + "acos")

            } else {

                display.text = (display.text.toString() + buttonCos.text.toString())
            }

        }
        buttonTan.setOnClickListener {
            if (isSecondFunction) {
                display.text = (display.text.toString() + "atan")

            } else {
                display.text = (display.text.toString() + buttonTan.text.toString())
            }

        }

        buttonSinh.setOnClickListener {
            display.text = (display.text.toString() + "sinh")

        }
        buttonCosh.setOnClickListener {
            display.text = (display.text.toString() + "cosh")

        }
        buttonTanh.setOnClickListener {
            display.text = (display.text.toString() + "tanh")

        }

        buttonLn.setOnClickListener {
            display.text = (display.text.toString() + "ln")

        }

        buttonSquare.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val d: Double = display.text.toString().toDouble()
                val square = d * d

                display.text = square.toString()
            }
        }
        buttonCube.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val c: Double = display.text.toString().toDouble()
                val cube = c * c * c

                display.text = cube.toString()
            }
        }
        buttonFactorial.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val value: Int = display.text.toString().toInt()
                val fact = factorial(value)
                display.text = fact.toString()
            }
        }


        button2sx.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val str: String = display.text.toString()
                val r = sqrt(str.toDouble())

                display.text = r.toString()
            }
        }
        button3sx.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val str: String = display.text.toString()
                val r = cbrt(str.toDouble())

                display.text = r.toString()
            }
        }

        button1x.setOnClickListener {
            display.text = (display.text.toString() + "1/")
        }

        buttonLog10.setOnClickListener {
            display.text = (display.text.toString() + "log")
        }

        buttonTenPowerX.setOnClickListener {
            display.text = (display.text.toString() + "10^")
        }

        buttonExp.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                val x: Double = display.text.toString().toDouble()

                display.text = exp(x).toString()
            }
        }

        buttonPower.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                display.text = display.text.toString() + '^'
            }

        }

        buttonYsRootX.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                display.text = display.text.toString() + "^(1/"
            }
        }

        buttonE.setOnClickListener {

            var e = display.text.toString()
            e += E.toString()
            display.text = e

        }

        buttonEE.setOnClickListener {
            if (display.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter the valid number..", Toast.LENGTH_SHORT).show()
            } else {
                var ee = display.text.toString()
                ee += 'E'
                display.text = ee
            }

        }
        buttonRand.setOnClickListener {
            val randomValue = (0..100).random()
            display.text = randomValue.toString()
        }

        buttonRad.setOnClickListener {
            isRadians = !isRadians
            buttonRad.text = if (isRadians) "Rad" else "Deg"
        }

        button2nd.setOnClickListener {
            isSecondFunction = !isSecondFunction
            updateButtonFunctions()
        }
        // Memory buttons
        buttonMr.setOnClickListener { onMemoryRecall() }
        buttonMc.setOnClickListener { onMemoryClear() }
        buttonMplus.setOnClickListener { onMemoryAdd() }
        buttonMminus.setOnClickListener { onMemorySubtract() }
    }

    private fun factorial(n: Int): Double {
        return if (n == 0) 1.0 else n * factorial(n - 1)
    }

    // Usage in your trigonometric functions
    fun sinValue(x: Double): Double {
        return if (isRadians) sin(x) else Math.sin(Math.toRadians(x))
    }

    fun cosValue(x: Double): Double {
        return if (isRadians) Math.cos(x) else Math.cos(Math.toRadians(x))
    }

    fun tanValue(x: Double): Double {
        return if (isRadians) Math.tan(x) else Math.tan(Math.toRadians(x))
    }

    private fun updateButtonFunctions() {
        if (isSecondFunction) {
            buttonSin.text = "sin⁻¹"
            buttonCos.text = "cos⁻¹"
            buttonTan.text = "tan⁻¹"
        } else {
            buttonSin.text = "sin"
            buttonCos.text = "cos"
            buttonTan.text = "tan"
        }
    }

    private fun evaluate(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < str.length) str[pos].code else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length)
                    Toast.makeText(
                        this@MainActivity,
                        "Unexpected: ${ch.toChar()}",
                        Toast.LENGTH_SHORT
                    ).show()
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor() // multiplication
                    else if (eat('/'.toInt())) x /= parseFactor() // division
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus

                var x: Double
                val startPos = pos

                if (eat('('.code)) { // parentheses
                    x = parseExpression()
                    eat(')'.code)
                } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) {
                    while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.code && ch <= 'z'.code) {
                    while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x = when (func) {
                        "sqrt" -> sqrt(x)
                        "sin" -> sinValue(x)
                        "cos" -> cosValue(x)
                        "tan" -> tanValue(x)
                        "tanh" -> tanh(x)
                        "sinh" -> sinh(x)
                        "cosh" -> cosh(x)
                        "asin" -> asin(x)
                        "acos" -> acos(x)
                        "atan" -> atan(x)
                        "log" -> log10(x)
                        "ln" -> ln(x)
                        "1/" -> 1 / x
                        else -> throw RuntimeException("Unknown function: $func")
                    }
                } else {
                    throw RuntimeException("Unexpected: ${ch.toChar()}")
                }

                if (eat('^'.code)) x = x.pow(parseFactor()) // exponentiation
                if (eat('E'.code)) {
                    val exponent = parseFactor().toInt()
                    x *= 10.0.pow(exponent)
                }

                return x
            }
        }.parse()
    }

    private fun onPlusMinus() {
        val value = display.text.toString().toDouble()
        display.text = (-value).toString()

    }

    private fun onPercent() {

        val value = display.text.toString().toDouble()
        display.text = (value / 100).toString()
        lastNumeric = false

    }


    private fun onMemoryRecall() {
        display.text = memoryValue.toString()
        currentInput = memoryValue.toString()
    }

    private fun onMemoryClear() {
        memoryValue = 0.0
        display.text = memoryValue.toString()
    }

    private fun onMemoryAdd() {
        memoryValue += evaluate(currentInput)
        display.text = memoryValue.toString()
    }

    private fun onMemorySubtract() {
        memoryValue -= evaluate(currentInput)
        display.text = memoryValue.toString()
    }


}