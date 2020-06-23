package com.example.sudokuapp.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sudokuapp.repo.SudokuRepository

class MenuViewModel(repository: SudokuRepository): ViewModel() {

    val isSavedBoard = MutableLiveData<Boolean>()

    init {
        val board = repository.getBoardFromSharedPrefs()

        if (board.isNullOrEmpty()) {
            isSavedBoard.postValue(false)
        } else {
            isSavedBoard.postValue(true)
        }
    }
}