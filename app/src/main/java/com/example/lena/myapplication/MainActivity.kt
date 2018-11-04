package com.example.lena.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var gridView: GridView
    lateinit var newGameButton: Button
    lateinit var flagsLeftTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newGameButton = findViewById(R.id.newGameButton)
        gridView = findViewById(R.id.grid) as GridView
        flagsLeftTextView = findViewById(R.id.flagsLeftTextView)

        ContextHolder.getInstance().context = this
        ContextHolder.getInstance().gridView = gridView
        ContextHolder.getInstance().flagsLeftTextView = flagsLeftTextView

        newGameButton.setOnClickListener { ContextHolder.getInstance().startNewGame() }

        ContextHolder.getInstance().startNewGame()

    }

    fun updateView() {
        runOnUiThread{
            val adapter = ContextHolder.getInstance().gridView.adapter as ImageAdapter
            adapter.notifyDataSetChanged()
            ContextHolder.getInstance().gridView.invalidateViews()
        }
    }

}
