package com.example.shadowfoxlogin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shadowfoxlogin.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    // 1. Declare the binding variable
    private lateinit var binding: ActivityMainBinding

    // 2. SharedPreferences setup
    private lateinit var sharedPref: SharedPreferences
    private val PREFS_NAME = "ShadowFoxPrefs"
    private val KEY_LAST_USER = "LAST_LOGGED_IN_USER"
    private val KEY_ATTEMPTS = "LOGIN_ATTEMPT_COUNT"
    private val MAX_ATTEMPTS = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 4. Initialize SharedPreferences
        sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // 5. Load last user and check for locked account
        loadLastLoggedInUser()
        checkLoginAttempts()

        // 6. Set the Login button click listener
        binding.loginButton.setOnClickListener {
            handleLogin()
        }

        // 7. NEW: Set the Forgot Password button click listener
        binding.forgotPasswordButton.setOnClickListener {
            showForgotPasswordDialog()
        }
    }

    private fun handleLogin() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val editor = sharedPref.edit()

        // --- Validation Skill ---
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show()
            return
        }

        // --- Demo Login Logic ---
        // For this demo, we'll pretend the correct password for any user is "1234"
        if (password == "1234") {
            // LOGIN SUCCESS
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

            // --- SharedPreferences Skill (Store User & Reset Attempts) ---
            editor.putString(KEY_LAST_USER, username)
            editor.putInt(KEY_ATTEMPTS, 0) // Reset attempts on success
            editor.apply()

            // --- Intents Skill ---
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)

        } else {
            // LOGIN FAIL
            // --- SharedPreferences Skill (Limit Attempts) ---
            val attempts = sharedPref.getInt(KEY_ATTEMPTS, 0) + 1
            editor.putInt(KEY_ATTEMPTS, attempts)
            editor.apply()

            checkLoginAttempts()
        }
    }

    private fun loadLastLoggedInUser() {
        val lastUser = sharedPref.getString(KEY_LAST_USER, null)
        if (lastUser != null) {
            binding.usernameEditText.setText(lastUser)
        }
    }

    private fun checkLoginAttempts() {
        val attempts = sharedPref.getInt(KEY_ATTEMPTS, 0)
        if (attempts >= MAX_ATTEMPTS) {
            binding.loginButton.isEnabled = false
            Toast.makeText(this, "Too many failed attempts. Login disabled.", Toast.LENGTH_LONG).show()
        } else if (attempts > 0) {
            val remaining = MAX_ATTEMPTS - attempts
            Toast.makeText(this, "Wrong password. $remaining attempts left.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showForgotPasswordDialog() {
        // Inflate the custom dialog layout
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_forgot_password, null)
        val emailInput = dialogView.findViewById<EditText>(R.id.email_edit_text_dialog)

        // Build the Material 3 Dialog
        MaterialAlertDialogBuilder(this)
            .setTitle("Forgot Password")
            .setMessage("Enter your email to receive a password reset link.")
            .setView(dialogView)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Send") { dialog, _ ->
                val email = emailInput.text.toString()

                // --- Validation Skill (for email) ---
                if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    // --- Toasts Skill ---
                    Toast.makeText(this, "Reset link sent to $email", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                }
            }
            .show()
    }
}