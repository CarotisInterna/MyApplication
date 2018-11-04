package com.example.lena.myapplication.utils

import com.example.lena.myapplication.R
import com.example.lena.myapplication.model.Cell

object ImageResolver {
    fun getImage(cell: Cell): Int {
        var value: Int? = R.drawable.button
        if (cell.isFlagged) value = R.drawable.flag
        if (cell.isClicked) {
            when (cell.value) {
                -1 -> value = getBomb(cell)
                0 -> value = R.drawable.number_0
                1 -> value = R.drawable.number_1
                2 -> value = R.drawable.number_2
                3 -> value = R.drawable.number_3
                4 -> value = R.drawable.number_4
                5 -> value = R.drawable.number_5
                6 -> value = R.drawable.number_6
                7 -> value = R.drawable.number_7
                8 -> value = R.drawable.number_8
            }
        }
        return value!!
    }

    private fun getBomb(cell: Cell): Int {
        if (cell.isClicked) return R.drawable.bomb_exploded
        return R.drawable.bomb_normal
    }
}