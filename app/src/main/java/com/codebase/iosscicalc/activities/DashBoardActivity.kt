package com.codebase.iosscicalc.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codebase.iosscicalc.MainActivity
import com.codebase.iosscicalc.R
import com.codebase.iosscicalc.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sGPACard.setOnClickListener {
            val intent = Intent(this, GPACalculator::class.java)
            startActivity(intent)
        }

        // open the simple calculator
        binding.calculatorCard.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}