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

class Notfi : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notfi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loggedInUser = intent.getStringExtra("USERNAME") ?: "Guest"
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"
        val lButton: Button = findViewById(R.id.back)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.homepage::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView4)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dummy data
        val notifications= listOf(
            notification("Luxury Experience", "Immerse yourself in a world of unparalleled elegance and comfort. As our valued VIP guest, enjoy complimentary upgrades to premium luxury rooms designed with opulence and tranquility in mind. Experience personalized services tailored to your preferences, such as private dining options, spa treatments, and more. Revel in the exclusive privileges of being our honored guest. Make your stay extraordinary and unforgettable!", "android.resource://com.example.luxe/drawable/c1"),
            notification("Last-Minute Deal", "Don’t miss this limited-time offer for last-minute bookings. Enjoy up to 30% off on luxurious accommodations, perfect for a quick retreat or an unplanned escape. Whether it’s a weekend adventure or a surprise staycation, our exclusive deal ensures you can indulge in comfort and relaxation without breaking the bank. Hurry—these savings are only available while rooms last!", "android.resource://com.example.luxe/drawable/c2"),
            notification("Summer Special", "This summer, make memories that will last a lifetime. Book your stay and enjoy 10% off our luxurious rooms, complemented by complimentary pool access to beat the heat in style. Perfect for families, couples, or solo travelers, this offer lets you relax and rejuvenate in our serene environment. Don’t let the summer pass without indulging in this special treat designed for your ultimate comfort and joy.", "android.resource://com.example.luxe/drawable/c3"),
        )

        recyclerView.adapter = homeAdapter(notifications)
    }

}