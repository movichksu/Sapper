package com.example.sapper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapper.adapter.GreedRecyclerViewAdapter
import java.util.*

class MainActivity : AppCompatActivity(), ItemClickListener {

    companion object {
        const val TAG = Constants.TAG + " MainActivity"
        private const val columns = 5
    }

    private lateinit var grid: RecyclerView
    var adapter: GreedRecyclerViewAdapter? = null
    private val field: MutableList<Int> = generateField(columns)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        grid = findViewById(R.id.grid)

        val layoutManager = GridLayoutManager(this, columns)
        grid.layoutManager = layoutManager
        adapter = GreedRecyclerViewAdapter(field)
                .apply {
                    setListener(this@MainActivity)
                }
        grid.adapter = adapter

    }

    private fun generateField(columns: Int): MutableList<Int> {
        val random = Random()
        val field: MutableList<Int> = mutableListOf()
        for (i in 0 until columns * columns) {
            field.add(-1)
        }

        val bombs: MutableSet<Int> = mutableSetOf()
        for (i in 0..columns) {
            val nextCell = random.nextInt(columns * columns)
            bombs.add(nextCell)
        }

        bombs.forEach {
            field[it] = 10
        }

        return field
    }

    override fun onItemClick(position: Int) {
        Log.d(TAG, " itemClickListener")
    }
}