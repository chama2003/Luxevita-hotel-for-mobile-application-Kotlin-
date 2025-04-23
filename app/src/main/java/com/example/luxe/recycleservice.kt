package com.example.luxe

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luxe.servicedb.service_info
import com.example.luxe.servicedb.serviceviewmodel
import com.example.luxe.servicedb.serviceviewmodelfactory
import kotlinx.coroutines.launch
import java.util.*

class recycleservice : AppCompatActivity() {

    private lateinit var serviceAdapter: serviceAdapter
    private lateinit var serviceViewModel: serviceviewmodel
    private lateinit var serviceList: List<service>  // Complete list of services
    private lateinit var filteredServiceList: List<service>  // Filtered list of services
    private var selectedDate: Calendar = Calendar.getInstance()  // Selected date for availability filter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycleservice)

        // Handle insets for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve logged-in user or set "Guest" if no username passed
        val loggedInUser = intent.getStringExtra("USERNAME") ?: "Guest"
        val loggedInadd = intent.getStringExtra("ADDRESS") ?: "Guest"
        // Initialize the ViewModel
        serviceViewModel = ViewModelProvider(this, serviceviewmodelfactory(application)).get(serviceviewmodel::class.java)

        // Set up RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycle1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Sample service list with available dates
        serviceList = listOf(
              service("Yoga Session", "Unwind and rejuvenate with yoga sessions.", 30.0, "android.resource://com.example.luxe/drawable/q1", "Service", true),
        service("Helicopter Tour", "Take a scenic helicopter tour over the city.", 400.0, "android.resource://com.example.luxe/drawable/q2", "Service", true),
            service("Personal Shopping", "Get personalized shopping assistance with a stylist.", 120.0, "android.resource://com.example.luxe/drawable/q3", "Service", true),
            service("Candlelit Dinner", "Enjoy a romantic candlelit dinner at the beach.", 180.0, "android.resource://com.example.luxe/drawable/q4", "Service", true)
        )

        // Initialize the adapter
        serviceAdapter = serviceAdapter(serviceList) { selectedService, selectedDate ->
            handleServiceBooking(selectedService, loggedInUser, selectedDate)
        }

        // Set the adapter to the RecyclerView
        recyclerView.adapter = serviceAdapter

        // Setup date filter button to open DatePickerDialog
        findViewById<View>(R.id.dateFilterButton).setOnClickListener {
            openDatePickerDialog()
        }
        val lButton: Button = findViewById(R.id.Ba)
        lButton.setOnClickListener {
            val intent = Intent(this, com.example.luxe.homepage::class.java)
            intent.putExtra("USERNAME", loggedInUser)
            intent.putExtra("ADDRESS", loggedInadd)
            startActivity(intent)
        }
    }

    // Function to open the DatePickerDialog
    private fun openDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            selectedDate.set(year, month, dayOfMonth)
            filterServicesByAvailability()  // Reapply filter with selected date
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }

    // Function to check if a service is available on the selected date
    private suspend fun isServiceAvailableOnDate(service: service, date: Calendar): Boolean {
        // Fetch booked dates for the service from the database
        val bookedDates = serviceViewModel.getBookedDatesForService(service.title)

        // Check if the selected date matches any of the booked dates
        return bookedDates.none { bookedService ->
            isSameDay(bookedService.date, date)
        }
    }

    // Helper function to check if two Calendar dates are the same day
    private fun isSameDay(bookedDate: Long, date: Calendar): Boolean {
        val bookedCalendar = Calendar.getInstance().apply { timeInMillis = bookedDate }
        return bookedCalendar.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                bookedCalendar.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                bookedCalendar.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)
    }

    // Function to filter services based on availability
    private fun filterServicesByAvailability() {
        lifecycleScope.launch {
            filteredServiceList = serviceList.filter { service ->
                // Check availability based on the selected date
                isServiceAvailableOnDate(service, selectedDate)
            }

            // Update the adapter with the filtered list
            serviceAdapter.updateService(filteredServiceList)
        }
    }

    private fun handleServiceBooking(
        selectedService: service,
        loggedInUser: String,
        date: Calendar
    ) {
        // Convert selectedDate (Calendar) to Date object
        val bookingDate: Long = date.timeInMillis

        // Create a new service_info object with booking details, including the date
        val serviceInfo = service_info(
            username = loggedInUser,
            title = selectedService.title,
            price = selectedService.price,
            date = bookingDate // Pass the selected date to the service_info object
        )

        // Insert the service booking into the database
        serviceViewModel.insertservice(serviceInfo)

        // Show a toast message indicating the booking was successful
        Toast.makeText(this, "Booking successful for ${selectedService.title} on ${bookingDate}", Toast.LENGTH_SHORT).show()
    }
}
