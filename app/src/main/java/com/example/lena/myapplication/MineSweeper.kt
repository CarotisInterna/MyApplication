package com.example.lena.myapplication

import android.content.Context
import android.widget.Toast
import com.example.lena.myapplication.model.Cell
import com.example.lena.myapplication.utils.Generator


class MineSweeper(size: Int, bombNumber: Int) {
    var bombNumber: Int
        private set
    var size: Int
        private set
    var area: Int
        private set
    var flagsLeft: Int


    init {
        this.size = size
        this.area = size * size
        this.bombNumber = this.area / 4;
        this.flagsLeft = bombNumber
    }


    fun click(cell: Cell, grid: Array<Array<Cell?>>): Boolean {
        if (cell.x >= 0 && cell.y >= 0 && cell.x < size && cell.y < size && !cell.isClicked) {
            cell.isClicked = true
            if (!cell.isLabel) {
                revealNeighbours(cell, grid)
            }
            if (cell.isBomb) {
                onGameLost(grid)
                return false
            }


        }

        return checkEnd(grid)
    }

    fun revealNeighbours(cell: Cell, grid: Array<Array<Cell?>>) {

        val neighbours = getNeighbours(cell, grid).filter { !cell.isBomb }

        for (neighbour in neighbours) {
            if (!neighbour.isClicked) {
                neighbour.isClicked = true
                if (neighbour.value == 0) {
                    revealNeighbours(neighbour, grid)
                }
            }
        }

    }

    fun getNeighbours(cell: Cell, grid: Array<Array<Cell?>>): List<Cell> {
        val neighbours = arrayOf(
                Pair(-1, -1),
                Pair(-1, 0),
                Pair(-1, 1),
                Pair(0, -1),
                Pair(0, 1),
                Pair(1, -1),
                Pair(1, 0),
                Pair(1, 1)
        )
        return neighbours
                .map { pair -> Pair(pair.first + cell.x, pair.second + cell.y) }
                .filter { pair -> pair.first >= 0 && pair.second >= 0 && pair.first < size && pair.second < size }
                .map { pair -> grid[pair.first][pair.second]!! }
                .filter { cell -> !cell.isBomb }
                .toList()

    }

    private fun checkEnd(grid: Array<Array<Cell?>>): Boolean {
        var bombNotFound = bombNumber
        var notRevealed = size * size
        for (x in 0 until size) {
            for (y in 0 until size) {

                if (grid[x][y]!!.isRevealed || grid[x][y]!!.isFlagged) {
                    notRevealed--
                }

                if (grid[x][y]!!.isFlagged && grid[x][y]!!.isBomb) {
                    bombNotFound--
                }
            }
        }

        if (bombNotFound == 0 && notRevealed == 0) {
            ContextHolder.getInstance().showText("Game won")
        }
        return false
    }

    fun flag(cell: Cell) {
        if (!cell.isFlagged && flagsLeft == 0) {
            ContextHolder.getInstance().showText("No more flags")
        } else {
            cell.isFlagged = !cell.isFlagged
            flagsLeft = flagsLeft + if (cell.isFlagged) -1 else 1
        }
    }

    private fun onGameLost(grid: Array<Array<Cell?>>) {
        ContextHolder.getInstance().showText("Game lost")

        for (x in 0 until size) {
            for (y in 0 until size) {
                grid[x][y]!!.isRevealed = true
            }
        }
    }

}