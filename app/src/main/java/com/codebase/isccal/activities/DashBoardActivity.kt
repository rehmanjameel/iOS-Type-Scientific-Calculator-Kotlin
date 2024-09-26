package com.codebase.isccal.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codebase.isccal.MainActivity
import com.codebase.isccal.databinding.ActivityDashBoardBinding
import com.google.android.gms.ads.AdView
import com.codebase.isccal.R
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class DashBoardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashBoardBinding
    lateinit var adView: AdView
    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()

        // open the simple calculator
        binding.calculatorCard.setOnClickListener{
            showInterstitialAd(MainActivity::class.java)
        }

        loadBannerAd()

        loadInterstitialAd()

        binding.sGPACard.setOnClickListener {
            showInterstitialAd(GPACalculator::class.java)
        }

        binding.cGPACard.setOnClickListener {
            showInterstitialAd(CGPAActivity::class.java)
        }

        binding.guideCard.setOnClickListener {
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showInterstitialAd(activity: Class<*>) {

        if (interstitialAd != null) {
            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    val intent = Intent(this@DashBoardActivity, activity)
                    startActivity(intent)
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                }
            }
            interstitialAd?.show(this)
        } else {
            val intent = Intent(this, activity)
            startActivity(intent)
        }
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, resources.getString(R.string.admob_interstitial_unit_id), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.e("failed inter", loadAdError.message)
//                Toast.makeText(this@DashBoardActivity, "not Loaded", Toast.LENGTH_SHORT).show()

                interstitialAd = null
            }

            override fun onAdLoaded(intersAd: InterstitialAd) {
                Log.e("ok inter", "Ad was loaded")
//                Toast.makeText(this@DashBoardActivity, "Loaded", Toast.LENGTH_SHORT).show()

                interstitialAd = intersAd
            }
        })
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