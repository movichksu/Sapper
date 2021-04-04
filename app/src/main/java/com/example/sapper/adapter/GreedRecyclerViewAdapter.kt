package com.example.sapper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.sapper.R

class GreedRecyclerViewAdapter internal constructor(
    private val data: MutableList<Int>
) : RecyclerView.Adapter<GreedRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = data[position]
        viewHolder.button.text = item.toString()
    }

    override fun getItemCount() = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = itemView.findViewById(R.id.cell)
    }
}