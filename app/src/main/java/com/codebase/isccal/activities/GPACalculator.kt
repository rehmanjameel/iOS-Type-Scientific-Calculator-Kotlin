package com.codebase.isccal.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.codebase.isccal.MainActivity
import com.codebase.isccal.databinding.ActivityGpacalculatorBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class GPACalculator : AppCompatActivity() {

    lateinit var binding: ActivityGpacalculatorBinding
    private var totalGPA = 0.0
    lateinit var adView: AdView

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

    override fun onResume() {
        super.onResume()
        loadBannerAd()
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