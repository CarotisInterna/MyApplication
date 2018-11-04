package com.example.lena.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.example.lena.myapplication.utils.Generator

class ContextHolder {
    lateinit var context: Context
    lateinit var gridView: GridView
    lateinit var flagsLeftTextView: TextView
    lateinit var mineSweeper: MineSweeper
        private set


    fun startNewGame() {

        //TODO: custom num of bombs and size

        val bombNumber = 4
        val size = 10

        val generatedGrid = Generator.generate(bombNumber, size)

        mineSweeper = MineSweeper(size, bombNumber)

        flagsLeftTextView.text = mineSweeper.flagsLeft.toString()

        gridView.adapter = ImageAdapter(context, generatedGrid)
    }

    fun updateFlagsLeftText() {
        flagsLeftTextView.text = mineSweeper.flagsLeft.toString()
    }

    fun showText(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun updateView() {
        (context as MainActivity).updateView()
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ContextHolder? = null

        fun getInstance(): ContextHolder {
            if (instance == null) {
                instance = ContextHolder()
            }
            return instance as ContextHolder
        }

    }
}