package com.dixit.ecommerceapp.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dixit.ecommerceapp.R
import com.dixit.ecommerceapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        binding.register.setOnClickListener {
            val fullName = binding.ETFullNameRA.text.toString()
            val email = binding.ETemailRA.text.toString()
            val password = binding.ETPasswordRA.text.toString()
            val confirmPassword = binding.ETConfirmPasswordRA.text.toString()

            if (validateInputs(fullName, email, password, confirmPassword)) {
                registerUser(email, password)
            }
        }

        // The rest of your code remains unchanged
        val textAlreadyHaveAccount = binding.AlreadyHaveAccount


        textAlreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerUser(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { verificationTask ->
                            if (verificationTask.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Account created. Verification email sent.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Failed to send verification email. Try again.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Registration Failed, try Again", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun validateInputs(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 8) {
            Toast.makeText(
                this,
                "Password should be at least 8 characters long",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).*\$")
        if (!regex.matches(password)) {
            Toast.makeText(
                this,
                "Password should contain at least one uppercase letter, one lowercase letter, and one special character",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}