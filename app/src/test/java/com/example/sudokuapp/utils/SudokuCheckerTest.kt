package com.example.sudokuapp.utils

import com.example.sudokuapp.ext.toMutableListOfCell
import com.example.sudokuapp.model.Board
import org.junit.Assert.*
import org.junit.Test

class SudokuCheckerTest {

    private val sudokuCheckcer = SudokuChecker()

    private val correctSolvedSudoku = Board(
        mutableListOf(
            mutableListOf(4, 5, 1, 7, 9, 3, 8, 2, 6),
            mutableListOf(2, 3, 6, 1, 4, 8, 5, 7, 9),
            mutableListOf(7, 8, 9, 2, 5, 6, 1, 3, 4),
            mutableListOf(1, 2, 3, 4, 6, 5, 7, 9, 8),
            mutableListOf(5, 4, 7, 8, 1, 9, 2, 6 ,3),
            mutableListOf(6, 9, 8, 3, 2, 7, 4, 1, 5),
            mutableListOf(3, 1, 2, 6, 8, 4, 9, 5, 7),
            mutableListOf(8, 6, 5, 9, 7, 1, 3, 4, 2),
            mutableListOf(9, 7, 4, 5, 3, 2, 6, 8, 1)
        )
    )

    private val wrongSolvedSudoku = Board(
        mutableListOf(
            mutableListOf(4, 5, 1, 7, 9, 3, 8, 2, 6),
            mutableListOf(2, 3, 6, 1, 4, 8, 5, 7, 9),
            mutableListOf(7, 8, 9, 2, 5, 2, 1, 3, 4),
            mutableListOf(1, 2, 3, 4, 6, 5, 7, 9, 8),
            mutableListOf(5, 4, 7, 8, 1, 9, 2, 6 ,3),
            mutableListOf(6, 9, 4, 3, 2, 7, 4, 1, 5),
            mutableListOf(3, 1, 2, 6, 8, 4, 9, 5, 7),
            mutableListOf(8, 6, 5, 9, 7, 1, 3, 4, 2),
            mutableListOf(3, 7, 4, 5, 3, 2, 6, 7, 1)
        )
    )

    @Test
    fun `checkSudoku should return true when received correct board`() {
        assertTrue(sudokuCheckcer.checkSudoku(correctSolvedSudoku.board.toMutableListOfCell()))
    }

    @Test
    fun `checkSudoku should return false when received wrong board`() {
        assertFalse(sudokuCheckcer.checkSudoku(wrongSolvedSudoku.board.toMutableListOfCell()))
    }
}