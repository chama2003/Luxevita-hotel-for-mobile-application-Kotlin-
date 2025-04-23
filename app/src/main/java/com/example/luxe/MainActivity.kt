package com.example.luxe


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.luxe.signupdb.signdb
import com.example.luxe.signupdb.signviewmodel
import com.example.luxe.signupdb.signviewmodelfactory



import java.io.File



class MainActivity : AppCompatActivity() {
    private lateinit var snameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var viewModel: signviewmodel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        snameInput = findViewById(R.id.editTextText)
        passwordInput = findViewById(R.id.editTextTextPassword)

        val dao = signdb.getInstance(application).sign_dao()
        val factory = signviewmodelfactory(dao)
        viewModel = ViewModelProvider(this, factory).get(signviewmodel::class.java)
        val signButton: Button = findViewById(R.id.button2)
        signButton.setOnClickListener{

            val intent = Intent(this, com.example.luxe.signup::class.java)
            startActivity(intent)
        }
        val loginButton = findViewById<Button>(R.id.button)
        loginButton.setOnClickListener {
            val sname = snameInput.text.toString()
            val password = passwordInput.text.toString()

            if (sname.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = viewModel.getUser(sname, password)
            val address=viewModel.getaddress(sname,password)
            if (user != null) {

                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                val intent1 = Intent(this, homepage::class.java)
                intent1.putExtra("USERNAME", sname)
                intent1.putExtra("ADDRESS", address)
                startActivity(intent1)

                // Inside MainActivity after successful login



                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        val updateButton: Button = findViewById(R.id.button6)
        updateButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.lernmore::class.java)

            startActivity(intent)
        }

    }


}