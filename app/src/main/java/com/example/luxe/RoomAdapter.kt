package com.example.luxe

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class RoomAdapter(
    private var roomList: List<Room>,
    private val onBookNowClick: (Room) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.bind(room)
    }

    override fun getItemCount(): Int = roomList.size

    fun updateRooms(newRooms: List<Room>) {
        roomList = newRooms
        notifyDataSetChanged()
    }
    fun updateFilteredRooms(filteredRooms: List<Room>) {
        roomList = filteredRooms
        notifyDataSetChanged()
    }

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val roomImage: ImageView = itemView.findViewById(R.id.roomImage)
        private val roomTitle: TextView = itemView.findViewById(R.id.roomTitle)
        private val roomDescription: TextView = itemView.findViewById(R.id.roomDescription)
        private val roomPrice: TextView = itemView.findViewById(R.id.roomPrice)

        fun bind(room: Room) {
            roomTitle.text = room.title
            roomDescription.text = room.description
            roomPrice.text = "$${room.price}"

            // Load image
            loadImage(room.imageUrl)

            // Set "Book Now" button click listener
            itemView.findViewById<View>(R.id.button3).setOnClickListener {
                onBookNowClick(room)
            }
        }

        private fun loadImage(imagePath: String) {
            try {
                val file = File(imagePath)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    roomImage.setImageBitmap(bitmap)
                } else {
                    val resourceName = imagePath.split("/").last()
                    val context = itemView.context
                    val resourceId = context.resources.getIdentifier(
                        resourceName,
                        "drawable",
                        context.packageName
                    )
                    if (resourceId != 0) roomImage.setImageResource(resourceId)
                    else roomImage.setImageResource(R.drawable.default_image)
                }
            } catch (e: Exception) {
                roomImage.setImageResource(R.drawable.default_image)
            }
        }

    }
}
