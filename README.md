**Project Description**:

**ShadowFoxLogin** is a modern Android authentication application built with Kotlin and Material Design 3. It features a secure login interface with validation, session management using SharedPreferences, and advanced UI elements. The app demonstrates core Android development skills including Intent navigation, Dialogs, View Binding, and dynamic text styling (Rainbow effect), while implementing security features like login attempt limiting.

## 📱 Features

### Core Functionality
* **Secure Login Interface:** Validates username and password input.
* **State Management:** Uses `SharedPreferences` to remember the last logged-in user and auto-fill the username on launch.
* **Navigation:** Seamless transition from Login Activity to Welcome Activity using Intents.

### Advanced Features (Bonus)
* **🔒 Security Lockout:** Limits login attempts to 5. After 5 failed attempts, the login button is disabled to prevent brute-force attacks.
* **🌈 Rainbow Welcome Message:** Custom `SpannableString` implementation that renders the "WELCOME" text in standard rainbow order (ROYGBIV) on the dashboard.
* **🔑 Forgot Password Dialog:** A custom Material Design 3 dialog that accepts an email address and includes regex validation.
* **🎨 Material Design 3:** Fully themed using Google's latest design standards, including `TextInputLayout` with floating labels and password visibility toggles.

## 🛠️ Tech Stack

* **Language:** Kotlin
* **IDE:** Android Studio Ladybug (2024.2.1+)
* **UI Toolkit:** XML / View System
* **Design System:** Material Design 3 (M3)
* **Architecture:** View Binding

## 📸 Screenshots

| Login Screen | Forgot Password | Welcome (Rainbow) | Security Lockout |
|:---:|:---:|:---:|:---:|
| <img src="screenshots/login.png" width="180" alt="Login Screen"> | <img src="screenshots/forgot_password.png" width="180" alt="Forgot Password"> | <img src="screenshots/welcome.png" width="180" alt="Welcome Screen"> | <img src="screenshots/error.png" width="180" alt="Security Lockout"> |

## 🚀 How to Run

1.  Clone the repository:
    ```bash
    git clone [https://github.com/YourUsername/ShadowFox.git](https://github.com/YourUsername/ShadowFox.git)
    ```
2.  Open **Android Studio**.
3.  Select **File > Open** and navigate to the cloned directory.
4.  Let Gradle sync finish.
5.  Press the **Run** button (▶) to launch on an emulator or physical device.

## 📝 Key Code Highlights

**Login Limit Logic (SharedPreferences):**
```
private fun checkLoginAttempts() {
    val attempts = sharedPref.getInt(KEY_ATTEMPTS, 0)
    if (attempts >= MAX_ATTEMPTS) {
        binding.loginButton.isEnabled = false
        Toast.makeText(this, "Too many failed attempts. Login disabled.", Toast.LENGTH_LONG).show()
    }
}
```

**Rainbow Text Implementation:**

```
val spannable = SpannableString("WELCOME ")
spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.rainbow_red)), 0, 1, 0)
// ... sets colors for remaining letters

```

_________________________________________________________________________________________________________
**👨‍💻 Author**: Vrushabh Rajkumar Ghodke

Role: Android App Development Intern @ ShadowFox

LinkedIn: [www.linkedin.com/in/vrushabh-ghodke-2873ba228]

GitHub: [[Vrushabh Ghodke](https://github.com/ghodkevrushabh/)]
