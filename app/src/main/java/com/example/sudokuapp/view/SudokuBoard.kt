package com.example.sudokuapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.sudokuapp.ext.empty
import com.example.sudokuapp.model.Cell
import com.example.sudokuapp.utils.BOARD_SIZE
import com.example.sudokuapp.utils.SQUARE_SIZE
import com.example.sudokuapp.utils.START_SELECTED_CELL_VALUE
import com.example.sudokuapp.utils.ZERO
import kotlin.math.min

typealias onSelectCellCallback = ((Pair<Int, Int>) -> Unit)

class SudokuBoard(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var cellSizePixels = 0F

    private var selectedRow = START_SELECTED_CELL_VALUE
    private var selectedColumn = START_SELECTED_CELL_VALUE

    private var cells = mutableListOf<Cell>()

    var onSelectCellCallback: onSelectCellCallback? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val sizePixels = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(sizePixels, sizePixels)
    }

    override fun onDraw(canvas: Canvas) {
        cellSizePixels = (width / BOARD_SIZE).toFloat()

        fillCells(canvas)
        drawLines(canvas)
        drawsSudokuDigits(canvas)
    }

    private fun drawsSudokuDigits(canvas: Canvas) {
        cells.forEach { cell ->
            val row = cell.row
            val column = cell.column
            val valueString = if (cell.value == ZERO) String.empty else cell.value.toString()

            val paintToUse = if (cell.isStartingCell) startingCellTextPaint else textPaint
            val textBounds = Rect()
            paintToUse.getTextBounds(valueString, ZERO, valueString.length, textBounds)
            val textWidth = paintToUse.measureText(valueString)
            val textHeight = textBounds.height()

            canvas.drawText(
                valueString,
                (column * cellSizePixels) + cellSizePixels / 2 - textWidth / 2,
                (row * cellSizePixels) + cellSizePixels / 2 + textHeight / 2,
                paintToUse
            )
        }
    }

    private fun fillCells(canvas: Canvas) {
        cells.forEach { cell ->
            val row = cell.row
            val column = cell.column

            if (cell.isStartingCell) {
                fillCell(canvas, row, column, startingCellPaint)
            } else if (row == selectedRow && column == selectedColumn) {
                fillCell(canvas, row, column, selectedCellPaint)
            } else if (row == selectedRow || column == selectedColumn) {
                fillCell(canvas, row, column, conflictCellPaint)
            } else if (row / SQUARE_SIZE == selectedRow / SQUARE_SIZE && column / SQUARE_SIZE == selectedColumn / SQUARE_SIZE) {
                fillCell(canvas, row, column, conflictCellPaint)
            }
        }
    }

    private fun fillCell(canvas: Canvas, r: Int, c: Int, paint: Paint) {
        canvas.drawRect(
            c * cellSizePixels,
            r * cellSizePixels,
            (c + 1) * cellSizePixels,
            (r + 1) * cellSizePixels,
            paint
        )
    }

    private fun drawLines(canvas: Canvas) {
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), thickLinePaint)

        for (i in 1 until BOARD_SIZE) {
            val paintToUse = when (i % SQUARE_SIZE) {
                0 -> thickLinePaint
                else -> thinLinePaint
            }

            canvas.drawLine(
                i * cellSizePixels,
                0F,
                i * cellSizePixels,
                height.toFloat(),
                paintToUse
            )

            canvas.drawLine(
                0F,
                i * cellSizePixels,
                width.toFloat(),
                i * cellSizePixels,
                paintToUse
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchEvent(event.x, event.y)
                true
            }
            else -> false
        }
    }

    private fun handleTouchEvent(x: Float, y: Float) {
        selectedRow = (y / cellSizePixels).toInt()
        selectedColumn = (x / cellSizePixels).toInt()
        onSelectCellCallback?.invoke(Pair(selectedRow, selectedColumn))
        invalidate()
    }

    fun updateCells(newCells: MutableList<Cell>) {
        cells.clear()
        cells.addAll(newCells)
        invalidate()
    }
}