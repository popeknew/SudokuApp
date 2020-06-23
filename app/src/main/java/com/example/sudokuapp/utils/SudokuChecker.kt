package com.example.sudokuapp.utils

import com.example.sudokuapp.ext.findCell
import com.example.sudokuapp.model.Cell

class SudokuChecker {

    private val sudokuCorrectSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    fun checkSudoku(board: MutableList<Cell>): Boolean {
        val matrix = createMatrix(board)
        return checkSquare(matrix) && checkColumns(matrix) && checkRows(matrix)
    }

    private fun checkSquare(matrix: MutableList<MutableList<Int>>): Boolean {
        val tmpList = mutableListOf<Int>()
        for (column in ZERO until matrix.size step SQUARE_SIZE) {
            for (row in ZERO until matrix.size) {
                for (columnIndex in ZERO until SQUARE_SIZE) {
                    tmpList.add(matrix[column + columnIndex][row])
                }
                if ((row + 1) % SQUARE_SIZE == ZERO) {
                    if (tmpList.toSet() != sudokuCorrectSet) {
                        return false
                    } else {
                        tmpList.clear()
                    }
                }
            }
        }
        return true
    }

    private fun createMatrix(board: MutableList<Cell>): MutableList<MutableList<Int>> {
        val list = mutableListOf<MutableList<Int>>()
        for (column in ZERO until BOARD_SIZE) {
            val columnList = mutableListOf<Int>()
            for (row in ZERO until BOARD_SIZE) {
                columnList.add(board.findCell(row, column).value)
            }
            list.add(columnList)
        }
        return list
    }

    private fun checkColumns(matrix: MutableList<MutableList<Int>>): Boolean {
        val subList = mutableListOf<Int>()
        matrix.forEach { column ->
            if (column.toSet() == sudokuCorrectSet) {
                subList.add(column.lastIndex)
            }
        }
        return (subList.size == BOARD_SIZE)
    }

    private fun checkRows(matrix: MutableList<MutableList<Int>>): Boolean =
        checkColumns(transposeMatrix(matrix))

    private fun transposeMatrix(matrix: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val result = MutableList(matrix[0].size) { MutableList(matrix.size) { 0 } }

        for (i in 0 until matrix.size)
            for (j in 0 until matrix[0].size)
                result[j][i] = matrix[i][j]

        return result
    }
}
