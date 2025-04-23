package com.example.luxe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luxe.roodb.room_info
class historyAdapter(
    private var roomList: List<room_info>,
    private val onDeleteClick: (room_info) -> Unit
) : RecyclerView.Adapter<historyAdapter.RoomViewHolder>() {

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.roomTitle)
        val priceTextView: TextView = itemView.findViewById(R.id.roomPrice)
        val deleteButton: Button = itemView.findViewById(R.id.button12)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room1_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.titleTextView.text = room.title
        holder.priceTextView.text = room.price.toString()

        // Set the click listener for the Delete button
        holder.deleteButton.setOnClickListener {
            onDeleteClick(room) // Trigger the callback
        }
    }

    override fun getItemCount(): Int = roomList.size

    fun updateList(newList: List<room_info>) {
        roomList = newList
        notifyDataSetChanged()
    }
}

