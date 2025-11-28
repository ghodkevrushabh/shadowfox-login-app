package com.example.shadowfoxlogin

import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.shadowfoxlogin.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    // 1. Declare the binding variable
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inflate the layout
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Get the saved username from SharedPreferences
        val sharedPref = getSharedPreferences("ShadowFoxPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("LAST_LOGGED_IN_USER", "User")

        // 4. Create the SpannableString for "WELCOME"
        val welcomeText = "WELCOME " // 7 letters + 1 space
        val spannable = SpannableString(welcomeText)

        // Apply a color to each letter in the correct order
        // Make sure you have defined these colors in your res/values/colors.xml
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_red)),
            0, 1, 0 // W
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_orange)),
            1, 2, 0 // E
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_yellow)),
            2, 3, 0 // L
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_green)),
            3, 4, 0 // C
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_blue)),
            4, 5, 0 // O
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_indigo)),
            5, 6, 0 // M
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_violet)),
            6, 7, 0 // E
        )

        // 5. Combine the colored "WELCOME " with the plain username
        val fullText = TextUtils.concat(spannable, "$username!")

        // 6. Set the final text
        binding.welcomeText.text = fullText
    }
}
