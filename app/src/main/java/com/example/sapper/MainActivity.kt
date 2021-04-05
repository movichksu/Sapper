package com.example.sapper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapper.adapter.GreedRecyclerViewAdapter
import java.lang.Integer.parseInt
import java.util.*

class MainActivity : AppCompatActivity(), ItemClickListener {

    companion object {
        const val TAG = Constants.TAG + " MainActivity"
    }

    private var columns = 0
    private lateinit var grid: RecyclerView
    var adapter: GreedRecyclerViewAdapter? = null
    private lateinit var field: MutableList<Int>
    private lateinit var columnsInput: EditText
    private lateinit var startGameBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        grid = findViewById(R.id.grid)
        columnsInput = findViewById(R.id.num_of_columns_input)
        startGameBtn = findViewById(R.id.start_game_btn)

        startGameBtn.setOnClickListener {
            if (parseInt(columnsInput.text.toString()) <= Constants.COLUMNS_MAX && parseInt(columnsInput.text.toString()) > 0) {
                columns = parseInt(columnsInput.text.toString())
                field = generateField(columns)
                val layoutManager = GridLayoutManager(this, columns)
                grid.layoutManager = layoutManager
                adapter = GreedRecyclerViewAdapter(field)
                        .apply {
                            setListener(this@MainActivity)
                        }
                grid.adapter = adapter
            } else {
                val toast = Toast.makeText(this@MainActivity, getString(R.string.wrong_cells_input_toast), Toast.LENGTH_LONG)
                toast.show()
            }
        }
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

    private fun countBombsNearby(position: Int) : Int{

        val topLeft = if(try { field[position - columns - 1] } catch (ex: IndexOutOfBoundsException) { 0 } == 10 && position % columns != 0) 1 else 0
        val top = if (try { field[position - columns ] } catch (ex: IndexOutOfBoundsException) { 0 } == 10) 1 else 0
        val topRight = if (try { field[position - columns + 1] } catch (ex: IndexOutOfBoundsException) { 0 } == 10 && (position + 1) % columns != 0 ) 1 else 0

        val left = if (try { field[position - 1] } catch (ex: IndexOutOfBoundsException) { 0 } == 10 && position % columns != 0) 1 else 0
        val right = if (try { field[position + 1] } catch (ex: IndexOutOfBoundsException) { 0 } == 10 && (position + 1) % columns != 0) 1 else 0

        val bottomLeft = if (try { field[position + columns - 1] } catch (ex: IndexOutOfBoundsException) { 0 } == 10 && position % columns != 0) 1 else 0
        val bottom = if (try { field[position + columns ] } catch (ex: IndexOutOfBoundsException) { 0 } == 10) 1 else 0
        val bottomRight = if (try { field[position + columns + 1] } catch (ex: IndexOutOfBoundsException) { 0 } == 10 && (position + 1) % columns != 0) 1 else 0

        return topLeft + top + topRight + left + right + bottomLeft + bottom + bottomRight
    }

    override fun onItemClick(position: Int) {
        val value = field[position]
        if (value == -1) {
            adapter?.cellChanged(position, countBombsNearby(position))
        } else if (value == 10) {
            for (i in 0 until field.size) {
                if (field[i] == 10) {
                    adapter?.cellChanged(i, 11)
                } else if (field[i] == -1) {
                    adapter?.cellChanged(i, 12)
                }
            }
        }
        Log.d(TAG, " itemClickListener")
    }
}