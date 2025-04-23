package com.example.luxe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luxe.roodb.roomviewmodel
import com.example.luxe.roodb.roomviewmodelfactory
import com.example.luxe.servicedb.serviceviewmodel
import com.example.luxe.servicedb.serviceviewmodelfactory

class recyclehistory : AppCompatActivity() {

    private lateinit var roomViewModel: roomviewmodel
    private lateinit var serviceViewModel: serviceviewmodel
    private lateinit var roomAdapter: historyAdapter
    private lateinit var serviceAdapter: hisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclehistory)

        // Get the logged-in username from the Intent
        val loggedInUser = intent.getStringExtra("USERNAME") ?: ""
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"
        // Set up RecyclerView for room history
        val recyclerViewRoom = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewRoom.layoutManager = LinearLayoutManager(this)

        // Get the RoomViewModel
        roomViewModel = ViewModelProvider(this, roomviewmodelfactory(application)).get(roomviewmodel::class.java)

        // Adapter with delete functionality for room history
        roomAdapter = historyAdapter(emptyList()) { room ->
            roomViewModel.deleteroom(room) // Delete the room using ViewModel
        }

        val lButton: Button = findViewById(R.id.back)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.homepage::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
        recyclerViewRoom.adapter = roomAdapter

        // Observe room data and update the adapter
        roomViewModel.room.observe(this, Observer { roomList ->
            val filteredRoomList = roomList.filter { it.username == loggedInUser }
            roomAdapter.updateList(filteredRoomList)
        })

        // Set up RecyclerView for service history
        val recyclerViewService = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerViewService.layoutManager = LinearLayoutManager(this)

        // Get the ServiceViewModel
        serviceViewModel = ViewModelProvider(this, serviceviewmodelfactory(application)).get(serviceviewmodel::class.java)

        // Adapter with delete functionality for service history
        serviceViewModel.service.observe(this, Observer { serviceList ->
            val filteredServiceList = serviceList.filter { it.username == loggedInUser }

            // Initialize the serviceAdapter with delete functionality
            serviceAdapter = hisAdapter(filteredServiceList) { service ->
                serviceViewModel.deleteservice(service) // Delete the service using ViewModel
            }

            recyclerViewService.adapter = serviceAdapter
        })
    }
}



