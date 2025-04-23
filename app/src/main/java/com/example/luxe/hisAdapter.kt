package com.example.luxe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luxe.servicedb.service_info
import java.text.SimpleDateFormat
import java.util.*

class hisAdapter(private var serviceList: List<service_info>, private val onDeleteClick: (service_info) -> Unit) : RecyclerView.Adapter<hisAdapter.ServiceViewHolder>() {

    // ViewHolder class to hold the individual item view
    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.roomTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.date) // Use TextView for date
        val priceTextView: TextView = itemView.findViewById(R.id.roomPrice)
        val deleteButton: Button = itemView.findViewById(R.id.button12) // Delete Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service1_item, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]

        // Set title
        holder.titleTextView.text = service.title

        // Format and set date
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = formatter.format(Date(service.date)) // Assuming `date` is a Long (timestamp)
        holder.dateTextView.text = formattedDate

        // Set price
        holder.priceTextView.text = service.price.toString()

        // Set delete button click listener
        holder.deleteButton.setOnClickListener {
            onDeleteClick(service) // Call the passed in onDeleteClick function
        }
    }

    override fun getItemCount(): Int = serviceList.size

    // Method to update the list of services after deletion
    fun updateList(newServiceList: List<service_info>) {
        serviceList = newServiceList
        notifyDataSetChanged()
    }
}
