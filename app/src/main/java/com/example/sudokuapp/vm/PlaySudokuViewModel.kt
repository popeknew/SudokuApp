package com.example.sudokuapp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sudokuapp.ext.findCell
import com.example.sudokuapp.ext.toMutableListOfCell
import com.example.sudokuapp.model.Cell
import com.example.sudokuapp.repo.SudokuRepository
import com.example.sudokuapp.utils.SudokuChecker
import kotlinx.coroutines.launch

class PlaySudokuViewModel(private val repository: SudokuRepository) : ViewModel() {

    val gameBoard = MutableLiveData<MutableList<Cell>>()

    var selectedCell: Cell? = null

    fun updateSelectedCell(row: Int, column: Int) {
        selectedCell = gameBoard.value?.findCell(row, column)
    }

    fun updateSelectedCellValue(buttonValue: Int) {
        selectedCell?.let { cell ->
            if (cell.isStartingCell.not()) {
                val currentList = gameBoard.value
                currentList?.findCell(cell.row, cell.column).apply { cell.value = buttonValue }
                gameBoard.postValue(currentList)
            }
        }
        viewModelScope.launch {
            if (gameBoard.value != null) {
                repository.saveBoardToSharedPrefs(gameBoard.value!!)
            }
        }
    }

    fun startGame(startNewGame: Boolean) {
        if (startNewGame) {
            viewModelScope.launch {
                val remoteData = repository.getSudokuBoardFromApi().board
                gameBoard.postValue(remoteData.toMutableListOfCell())
            }
        } else {
            gameBoard.postValue(repository.getBoardFromSharedPrefs())
        }
    }

    fun checkSolution(): Boolean {
        return if (gameBoard.value != null) {
            SudokuChecker().checkSudoku(gameBoard.value!!)
        } else false
    }
}