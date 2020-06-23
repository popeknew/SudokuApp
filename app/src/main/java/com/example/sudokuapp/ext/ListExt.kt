package com.example.sudokuapp.ext

import com.example.sudokuapp.model.Cell

fun List<Cell>.findCell(row: Int, column: Int) =
    first { cell -> cell.row == row && cell.column == column }

fun MutableList<MutableList<Int>>.toMutableListOfCell(): MutableList<Cell> {
    val list = flatten()
    return MutableList(list.size) { i ->
        Cell(i % 9, i / 9, list[i], list[i] != 0)
    }
}