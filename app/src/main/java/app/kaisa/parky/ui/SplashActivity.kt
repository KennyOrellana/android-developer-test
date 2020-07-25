package app.kaisa.parky.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.kaisa.parky.R
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            setContentView(R.layout.activity_splash)
        }

        Timer().schedule(object : TimerTask(){
            override fun run() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY = 1_500L
    }
}