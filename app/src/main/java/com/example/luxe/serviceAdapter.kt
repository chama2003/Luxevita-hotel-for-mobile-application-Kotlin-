package com.example.luxe

import android.app.DatePickerDialog
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.util.*

class serviceAdapter(
    private var serviceList: List<service>,
    private val onBookNowClick: (service, selectedDate: Calendar) -> Unit // Corrected function signature
) : RecyclerView.Adapter<serviceAdapter.serviceViewHolder>() {

    // This method will update the list of services and notify the adapter
    fun updateService(newServiceList: List<service>) {
        serviceList = newServiceList
        notifyDataSetChanged()  // Notify the adapter that the data has changed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): serviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return serviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: serviceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int = serviceList.size

    inner class serviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val serviceImage: ImageView = itemView.findViewById(R.id.roomImage)
        private val serviceTitle: TextView = itemView.findViewById(R.id.roomTitle)
        private val serviceDescription: TextView = itemView.findViewById(R.id.roomDescription)
        private val servicePrice: TextView = itemView.findViewById(R.id.roomPrice)
        private val dateTextView: TextView = itemView.findViewById(R.id.selectedDate) // Fixed the date view
        private var selectedDate: Calendar = Calendar.getInstance() // Initialize selectedDate once

        fun bind(service: service) {
            serviceTitle.text = service.title
            serviceDescription.text = service.description
            servicePrice.text = "$${service.price}"

            // Load image
            loadImage(service.imageUrl)

            // Set "Book Now" button click listener
            itemView.findViewById<View>(R.id.button3).setOnClickListener {
                onBookNowClick(service, selectedDate) // Pass selected date properly
            }

            // Set Date Picker button click listener
            itemView.findViewById<View>(R.id.dateFilterButton).setOnClickListener {
                openDatePickerDialog() // Open the Date Picker dialog
            }

            // Set the selected date text view to display selected date
            dateTextView.text = "${selectedDate.get(Calendar.DAY_OF_MONTH)}/${selectedDate.get(Calendar.MONTH) + 1}/${selectedDate.get(Calendar.YEAR)}"
        }

        private fun openDatePickerDialog() {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(itemView.context, { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth) // Set the selected date
                dateTextView.text = "${selectedDate.get(Calendar.DAY_OF_MONTH)}/${selectedDate.get(Calendar.MONTH) + 1}/${selectedDate.get(Calendar.YEAR)}" // Update the date text view
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show() // Show the Date Picker dialog
        }

        private fun loadImage(imagePath: String) {
            try {
                val file = File(imagePath)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    serviceImage.setImageBitmap(bitmap)
                } else {
                    val resourceName = imagePath.split("/").last()
                    val context = itemView.context
                    val resourceId = context.resources.getIdentifier(
                        resourceName,
                        "drawable",
                        context.packageName
                    )
                    if (resourceId != 0) serviceImage.setImageResource(resourceId)
                    else serviceImage.setImageResource(R.drawable.default_image)
                }
            } catch (e: Exception) {
                serviceImage.setImageResource(R.drawable.default_image)
            }
        }
    }
}
