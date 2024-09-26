package com.codebase.isccal.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codebase.isccal.MainActivity
import com.codebase.isccal.R
import com.codebase.isccal.databinding.ActivityCgpaactivityBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class CGPAActivity : AppCompatActivity() {

    lateinit var binding: ActivityCgpaactivityBinding
    private var totalCGPA = 0.0
    lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCgpaactivityBinding.inflate(layoutInflater)
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

    override fun onResume() {
        super.onResume()
        loadBannerAd()
    }

    private fun verifyFields() {
        val sumOfGPAs = binding.gpasTIET.text.toString()
        val noOfSem = binding.noSemesterTIET.text.toString()

        if (sumOfGPAs.isEmpty() && noOfSem.isEmpty()) {
            binding.gpasTIET.error = "Field required"
            binding.noSemesterTIET.error = "Field required"
        } else if (sumOfGPAs.isEmpty()) {
            binding.gpasTIET.error = "Field required"

        } else if(noOfSem.isEmpty()) {
            binding.noSemesterTIET.error = "Field required"

        } else {
            totalCGPA = sumOfGPAs.toDouble() / noOfSem.toDouble()
            Log.e("total gpa ", totalCGPA.toString())
            binding.totalGPA.text = totalCGPA.toString()
        }
    }

    private fun loadBannerAd() {
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView = binding.adViewId
        adView.loadAd(adRequest)

        adView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
//                Toast.makeText(this@DashBoardActivity, "Returned to the app", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
//                Toast.makeText(this@DashBoardActivity, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }
    }
}