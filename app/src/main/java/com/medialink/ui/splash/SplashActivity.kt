package com.medialink.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.medialink.pokemonapp.databinding.ActivitySplashBinding
import com.medialink.ui.main.MainActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private lateinit var mScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        mScope = CoroutineScope(Dispatchers.IO)
        withCoroutine(3_000L)
    }

    private fun launchPostSplashActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun withCoroutine(delay: Long) {
        mScope.launch {
            delay(delay)
            withContext(Dispatchers.Main) {
                launchPostSplashActivity()
                mScope.cancel(null)
            }
        }
    }

}