package com.example.sapper.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.sapper.ItemClickListener
import com.example.sapper.R
import java.util.*

class GreedRecyclerViewAdapter internal constructor(
        private val data: MutableList<Int>
) : RecyclerView.Adapter<GreedRecyclerViewAdapter.ViewHolder>() {

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = data[position]
        when (item) {
            // empty unpressed
            -1 -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#797D7F"))
                viewHolder.button.text = ""
            }
            // bomb unpressed
            10 -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#797D7F"))
                viewHolder.button.text = ""
            }
            // empty pressed with number
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#E5E7E9"))
                viewHolder.button.text = item.toString()
            }
            // bomb pressed
            11 -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#E74C3C"))
                viewHolder.button.text = "bomb"
            }
        }
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById<Button>(R.id.cell)
                .apply {
                    setOnClickListener {
                        listener?.onItemClick(adapterPosition)
                    }
                }

    }

    fun setListener(itemClickListener: ItemClickListener?) {
        listener = itemClickListener
    }

    fun cellChanged(position: Int, value: Int) {
        data[position] = value
        notifyDataSetChanged()
    }
}