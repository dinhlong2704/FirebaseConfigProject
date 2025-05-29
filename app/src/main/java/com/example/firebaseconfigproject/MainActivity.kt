package com.example.firebaseconfigproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseconfigproject.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )

        remoteConfig.setDefaultsAsync(
            mapOf("welcome_message" to "Chào từ default config")
        )

        remoteConfig.fetchAndActivate().addOnCompleteListener {
            val msg = remoteConfig.getString("welcome_message")
            findViewById<TextView>(R.id.tv_appname).text = msg
        }
    }
}

