package com.example.luxe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.luxe.signupdb.sign_info
import com.example.luxe.signupdb.signdb
import com.example.luxe.signupdb.signviewmodel
import com.example.luxe.signupdb.signviewmodelfactory
import kotlinx.coroutines.launch

class updateuser : AppCompatActivity() {
    private lateinit var signId: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var viewModel: signviewmodel
    private lateinit var loggedInUser: String  // Logged-in user
    private lateinit var currentUsername:String
    private lateinit var currentAddress:String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_updateuser)

        // Retrieve the logged-in username from the Intent
        loggedInUser = intent.getStringExtra("USERNAME") ?: ""
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"
        // Check if the logged-in user exists
        if (loggedInUser.isEmpty()) {
            Toast.makeText(this, "No logged-in user found", Toast.LENGTH_SHORT).show()
            finish()  // Close the activity if no logged-in user is found
            return
        }

        // Initialize EditText fields
        signId = findViewById(R.id.signId)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        addressEditText = findViewById(R.id.addressEditText)

        // Set up ViewModel and DAO
        val dao = signdb.getInstance(application).sign_dao()
        val factory = signviewmodelfactory(dao)
        viewModel = ViewModelProvider(this, factory).get(signviewmodel::class.java)

        // Set up the button to update user data
        val updateBtn = findViewById<Button>(R.id.update_button)
        updateBtn.setOnClickListener {
            updateUserData()
        }

        // Pre-fill the current username, password, and address
        prefillUserData()
        val lButton: Button = findViewById(R.id.Ba)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.homepage::class.java)
            intent.putExtra("USERNAME",currentUsername )
            intent.putExtra("ADDRESS", currentAddress)
            startActivity(intent)
        }
    }

    private fun prefillUserData() {
        // Use a coroutine to retrieve the user data and pre-fill the fields
        lifecycleScope.launch {
            val user = viewModel.getUsername(loggedInUser)
            user?.let {
                signId.setText(it.id.toString())
                usernameEditText.setText(it.sname)
                passwordEditText.setText(it.spass)
                addressEditText.setText(it.saddress)
            }
        }
    }

    private fun updateUserData() {
        val currentId = signId.text.toString().toIntOrNull()  // Validate if the ID is a valid integer
           currentUsername = usernameEditText.text.toString()
        val currentPassword = passwordEditText.text.toString()
        currentAddress = addressEditText.text.toString()

        // Proceed with updating the data if all fields are valid
        if (currentUsername.isNotBlank() && currentPassword.isNotBlank() && currentAddress.isNotBlank() && currentId != null) {
            // Create a new sign_info object with updated details
            val updatedSignInfo = sign_info(
                id = currentId,  // Using the existing ID, even if the username is changed
                sname = currentUsername,
                saddress = currentAddress,
                sgender = "", // You can also add a gender field if needed
                spass = currentPassword
            )

            // Use a coroutine to update the user data in the database
            lifecycleScope.launch {
                viewModel.updatesign(updatedSignInfo)  // Update the user data in the database
                Toast.makeText(this@updateuser, "User details updated successfully", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill in all fields with valid data", Toast.LENGTH_SHORT).show()
        }
    }
}
