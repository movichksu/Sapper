package com.example.sapper.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.sapper.ItemClickListener
import com.example.sapper.R

class GridRecyclerViewAdapter internal constructor(
        private val data: MutableList<Int>
) : RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolder>() {

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = data[position]
        when (item) {
            CellStates.EMPTY_UNPRESSED.value -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#797D7F"))
                viewHolder.button.text = ""
            }
            CellStates.BOMB_UNPRESSED.value -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#797D7F"))
                viewHolder.button.text = ""
            }
            CellStates.BOMB_PRESSED.value -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#E74C3C"))
                viewHolder.button.text = "bomb"
            }
            CellStates.EMPTY_UNPRESSED_AFTER_BOMB_CLICK.value -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#797D7F"))
                viewHolder.button.text = ""
            }
            else -> {
                viewHolder.button.setBackgroundColor(Color.parseColor("#E5E7E9"))
                viewHolder.button.text = item.toString()
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

    fun cellChanged(position: Int) {
       notifyItemChanged(position)
    }

}