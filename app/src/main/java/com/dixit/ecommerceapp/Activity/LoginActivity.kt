package com.dixit.ecommerceapp.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dixit.ecommerceapp.R
import com.dixit.ecommerceapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()

//         Check if the user is already authenticated
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            // User is already logged in, navigate to the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.textInputEditText.text.toString()
            val password = binding.textInputEditText2.text.toString()

            // You can add your login logic here, for example:
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Email/Password Can't Be Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val verification = firebaseAuth.currentUser?.isEmailVerified
                        if (verification == true) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Email not verified. Please check your email.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.textdonthaveacount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.textForgetpass.setOnClickListener {
            val intent = Intent(this, ForgetActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
