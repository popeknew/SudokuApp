package com.example.sudokuapp.model

class Cell(
    val row: Int,
    val column: Int,
    var value: Int,
    var isStartingCell: Boolean = false
)