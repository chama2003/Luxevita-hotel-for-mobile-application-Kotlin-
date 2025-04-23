package com.example.luxe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luxe.roodb.room_info
import com.example.luxe.roodb.roomviewmodel
import com.example.luxe.roodb.roomviewmodelfactory

class recycle : AppCompatActivity() {

    private lateinit var adapter: RoomAdapter
    private lateinit var viewModel: roomviewmodel
    private val roomList = listOf(
        Room("Executive Room", "An executive room with modern amenities and a work desk.", 200.0, "android.resource://com.example.luxe/drawable/z1", "Executive", true),
        Room("Presidential Suite", "A lavish suite with breathtaking views and high-end services.", 500.0, "android.resource://com.example.luxe/drawable/z2", "Presidential", false),
        Room("Family Room", "A large room with two queen-sized beds and extra space.", 180.0, "android.resource://com.example.luxe/drawable/z3", "Family", true),
        Room("Single Room", "A small, comfortable room ideal for solo travelers.", 80.0, "android.resource://com.example.luxe/drawable/z4", "Single", true),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        val loggedInUser = intent.getStringExtra("USERNAME") ?: "Guest"
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"

        val lButton: Button = findViewById(R.id.back)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.homepage::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        viewModel = ViewModelProvider(this, roomviewmodelfactory(application)).get(roomviewmodel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RoomAdapter(roomList) { selectedRoom ->
            handleRoomBooking(selectedRoom, loggedInUser)
        }
        recyclerView.adapter = adapter

        // Spinners and CheckBox setup
        val roomTypeSpinner: Spinner = findViewById(R.id.roomTypeSpinner)
        val priceRangeSpinner: Spinner = findViewById(R.id.priceRangeSpinner)
        val availabilityCheckBox: CheckBox = findViewById(R.id.availabilityCheckBox)

        // Room Type Spinner listener
        roomTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                applyFilter(
                    roomTypeSpinner.selectedItem.toString(),
                    priceRangeSpinner.selectedItem.toString(),
                    availabilityCheckBox.isChecked
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Price Range Spinner listener
        priceRangeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                applyFilter(
                    roomTypeSpinner.selectedItem.toString(),
                    priceRangeSpinner.selectedItem.toString(),
                    availabilityCheckBox.isChecked
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Availability Checkbox listener
        availabilityCheckBox.setOnCheckedChangeListener { _, _ ->
            applyFilter(
                roomTypeSpinner.selectedItem.toString(),
                priceRangeSpinner.selectedItem.toString(),
                availabilityCheckBox.isChecked
            )
        }
    }

    // Function to apply filters based on the selected criteria
    private fun applyFilter(roomType: String, priceRange: String, isAvailable: Boolean) {
        val filteredList = roomList.filter { room ->
            val matchesRoomType = roomType == "All" || room.roomType == roomType
            val matchesPriceRange = room.price <= priceRange.toDoubleOrNull() ?: Double.MAX_VALUE
            val matchesAvailability = !isAvailable || room.isAvailable
            matchesRoomType && matchesPriceRange && matchesAvailability
        }
        adapter.updateRooms(filteredList)
    }

    private fun handleRoomBooking(room: Room, loggedInUser: String) {
        val roomInfo = room_info(
            username = loggedInUser,
            title = room.title,
            price = room.price
        )
        viewModel.insertroom(roomInfo)
        Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show()
    }
}
