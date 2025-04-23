package com.example.luxe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class recycleoffer : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycleoffer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loggedInUser = intent.getStringExtra("USERNAME") ?: ""
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"
        val lButton: Button = findViewById(R.id.Banana)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.homepage::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Dummy data
        val offers = listOf(
            offer("Business Stay Deal", "Maximize your productivity and enjoy a seamless stay with our Business Stay Deal. This offer includes exclusive discounts on premium rooms, ensuring a comfortable environment for work and rest. Benefit from early check-in and other thoughtful amenities designed to meet the needs of busy professionals. Whether it’s a short trip or an extended stay, we’ve got you covered with the ideal balance of luxury and practicality.", "android.resource://com.example.luxe/drawable/a11"),
            offer("Honeymoon Bliss", "Celebrate love with unforgettable experiences!\n" +
                    "Make your honeymoon truly magical with our Honeymoon Bliss package. Enjoy a romantic suite upgrade, complete with breathtaking views and intimate settings. To enhance your experience, indulge in a complimentary couple’s massage, perfect for unwinding together. Relish the joy of new beginnings with exclusive perks, personalized services, and memories that will last a lifetime.", "android.resource://com.example.luxe/drawable/a2"),
            offer("Long Stay Discount", "Planning an extended getaway? Book a stay of 7 nights or more and receive a generous 25% discount on your total booking. Whether you’re working remotely, enjoying a relaxing vacation, or exploring the local attractions, this offer ensures great value while you enjoy the luxury of our world-class amenities. Make yourself at home and let us take care of the rest!\n" +
                    "\n", "android.resource://com.example.luxe/drawable/a3"),
        )

        recyclerView.adapter = OfferAdapter(offers)

    }
}