package com.example.luxe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class homepage : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the correct layout file (activity_homepage.xml)
        setContentView(R.layout.activity_homepage)

        // Adjust for system bars inset (for edge-to-edge UI)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the logged-in username (or "Guest" if not available)
        val loggedInUser = intent.getStringExtra("USERNAME") ?: "Guest"
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"
        val username: TextView = findViewById(R.id.textView6)
        username.text = loggedInUser
        val useraddress: TextView = findViewById(R.id.textView20)
        useraddress.text = loggedInadd

        // Correctly reference buttons by their IDs in the layout
        val signButton: Button = findViewById(R.id.button7)  // Ensure this ID exists in activity_homepage.xml
        val serButton: Button = findViewById(R.id.button8)   // Ensure this ID exists in activity_homepage.xml
        val offerButton: Button = findViewById(R.id.button10)
        val historyButton: Button = findViewById(R.id.button9)
        val updateButton: Button = findViewById(R.id.button11)
        val nButton: Button = findViewById(R.id.button14)
        val lButton: Button = findViewById(R.id.back)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.MainActivity::class.java)

            startActivity(intent)
        }
       nButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.Notfi::class.java)
            intent.putExtra("USERNAME", loggedInUser)
           intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
       updateButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.updateuser::class.java)
            intent.putExtra("USERNAME", loggedInUser)
           intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        historyButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.recyclehistory::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        offerButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.recycleoffer::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        signButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.recycle::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }

        serButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.recycleservice::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }

        // Set up RecyclerView to show notifications/offers

    }
    }

