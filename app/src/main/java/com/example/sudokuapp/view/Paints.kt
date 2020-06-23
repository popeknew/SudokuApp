package com.example.sudokuapp.view

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import com.example.sudokuapp.utils.BOARD_DIGITS_SIZE
import com.example.sudokuapp.utils.BOARD_THICK_LINE_STROKE_WIDTH
import com.example.sudokuapp.utils.BOARD_THIN_LINE_STROKE_WIDTH

val thickLinePaint = Paint().apply {
    style = Paint.Style.STROKE
    color = Color.BLACK
    strokeWidth = BOARD_THICK_LINE_STROKE_WIDTH
}

val thinLinePaint = Paint().apply {
    style = Paint.Style.STROKE
    color = Color.BLACK
    strokeWidth = BOARD_THIN_LINE_STROKE_WIDTH
}

val selectedCellPaint = Paint().apply {
    style = Paint.Style.FILL_AND_STROKE
    color = Color.GREEN
}

val conflictCellPaint = Paint().apply {
    style = Paint.Style.FILL_AND_STROKE
    color = Color.YELLOW
}

val textPaint = Paint().apply {
    style = Paint.Style.FILL_AND_STROKE
    color = Color.BLACK
    textSize = BOARD_DIGITS_SIZE
}

val startingCellTextPaint = Paint().apply {
    style = Paint.Style.FILL_AND_STROKE
    color = Color.BLACK
    textSize = BOARD_DIGITS_SIZE
    typeface = Typeface.DEFAULT_BOLD
}

val startingCellPaint = Paint().apply {
    style = Paint.Style.FILL_AND_STROKE
    color = Color.GRAY
}