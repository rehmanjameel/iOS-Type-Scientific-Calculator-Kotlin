package com.codebase.isccal.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codebase.isccal.MainActivity
import com.codebase.isccal.databinding.ActivityGpacalculatorBinding

class GPACalculator : AppCompatActivity() {

    lateinit var binding: ActivityGpacalculatorBinding
    private var totalGPA = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGpacalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            verifyFields()
        }

        binding.calculator.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.backArrow.setOnClickListener {
            onBackPressed()
        }

    }

    private fun verifyFields() {
        val qualityPoints = binding.qualityPointsTIET.text.toString()
        val creditHours = binding.creditHoursTIET.text.toString()

        if (qualityPoints.isEmpty() && creditHours.isEmpty()) {
            binding.qualityPointsTIET.error = "Field required"
            binding.creditHoursTIET.error = "Field required"
        } else if (qualityPoints.isEmpty()) {
            binding.qualityPointsTIET.error = "Field required"

        } else if(creditHours.isEmpty()) {
            binding.creditHoursTIET.error = "Field required"

        } else {
            totalGPA = qualityPoints.toDouble() / creditHours.toDouble()
            Log.e("total gpa ", totalGPA.toString())
            binding.totalGPA.text = totalGPA.toString()
        }
    }

}