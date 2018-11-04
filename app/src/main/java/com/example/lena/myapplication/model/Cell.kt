package com.example.lena.myapplication.model


class Cell {

    var value: Int
        private set
    var isBomb: Boolean
    var isRevealed: Boolean = false
    var isClicked: Boolean = false
    var isFlagged: Boolean = false
    var isLabel: Boolean = false
    var x: Int
        private set
    var y: Int
        private set
    var position: Int = 0

    constructor(x: Int, y: Int, value: Int, position: Int) {
        this.x = x
        this.y = y
        this.value = value
        this.position = position
        this.isBomb = value == -1
        this.isLabel = value > 0
    }
}
