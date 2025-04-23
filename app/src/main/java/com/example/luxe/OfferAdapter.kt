package com.example.luxe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OfferAdapter(
    private val offerList: List<offer>
) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.offerTitle)
        private val description: TextView = itemView.findViewById(R.id.offerDescription)
        private val image: ImageView = itemView.findViewById(R.id.offerImage)

        fun bind(offer: offer) {
            title.text = offer.title
            description.text = offer.description

            // Load image from drawable resources
            val resourceName = offer.imageUrl.split("/").last()
            val context = itemView.context
            val resourceId = context.resources.getIdentifier(
                resourceName,
                "drawable",
                context.packageName
            )
            if (resourceId != 0) {
                image.setImageResource(resourceId)
            } else {
                image.setImageResource(R.drawable.default_image) // Fallback image
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(offerList[position])
    }

    override fun getItemCount(): Int = offerList.size
}
