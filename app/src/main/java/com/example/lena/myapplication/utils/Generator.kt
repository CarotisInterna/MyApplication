package com.example.lena.myapplication.utils

import com.example.lena.myapplication.model.Cell
import java.util.*

object Generator {

    fun generate(bombNumber: Int, size: Int): Array<Array<Cell?>> {
        var num = bombNumber
        // Random for generating numbers
        val r = Random()

        var gridNumbers = Array(size) { IntArray(size) }

        while (num > 0) {
            val x = r.nextInt(size)
            val y = r.nextInt(size)
            // -1 is the bomb
            if (gridNumbers[x][y] != -1) {
                gridNumbers[x][y] = -1
                num--
            }
        }

        gridNumbers = calculateNeighbours(gridNumbers, size)

        val grid: Array<Array<Cell?>> = Array(size) { arrayOfNulls<Cell?>(size) }

        for (x in 0 until size) {
            for (y in 0 until size) {
                grid[x][y] = Cell(x, y, gridNumbers[x][y], y * size + x)
            }
        }
        return grid
    }

    private fun calculateNeighbours(grid: Array<IntArray>, size: Int): Array<IntArray> {
        for (x in 0 until size) {
            for (y in 0 until size) {
                grid[x][y] = getNeighbourNumber(grid, x, y, size)
            }
        }

        return grid
    }

    private fun getNeighbourNumber(grid: Array<IntArray>, x: Int, y: Int, size: Int): Int {
        if (grid[x][y] == -1) {
            return -1
        }

        var count = 0

        if (isMineAt(grid, x - 1, y + 1, size)) count++ // top-left
        if (isMineAt(grid, x, y + 1, size)) count++ // top
        if (isMineAt(grid, x + 1, y + 1, size)) count++ // top-right
        if (isMineAt(grid, x - 1, y, size)) count++ // left
        if (isMineAt(grid, x + 1, y, size)) count++ // right
        if (isMineAt(grid, x - 1, y - 1, size)) count++ // bottom-left
        if (isMineAt(grid, x, y - 1, size)) count++ // bottom
        if (isMineAt(grid, x + 1, y - 1, size)) count++ // bottom-right

        return count
    }

    private fun isMineAt(grid: Array<IntArray>, x: Int, y: Int, size: Int): Boolean {
        if (x >= 0 && y >= 0 && x < size && y < size) {
            if (grid[x][y] == -1) {
                return true
            }
        }
        return false
    }

}