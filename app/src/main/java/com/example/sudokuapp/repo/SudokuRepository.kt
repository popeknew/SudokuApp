package com.example.sudokuapp.repo

import android.content.SharedPreferences
import com.example.sudokuapp.ext.empty
import com.example.sudokuapp.model.Board
import com.example.sudokuapp.model.Cell
import com.example.sudokuapp.net.SudokuRestApi
import com.example.sudokuapp.utils.BOARD_PREFS_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SudokuRepository(
    private val api: SudokuRestApi,
    private val sharedPrefs: SharedPreferences
) {

    suspend fun getSudokuBoardFromApi(): Board = api.getBoard()

    suspend fun saveBoardToSharedPrefs(board: MutableList<Cell>) =
        withContext(Dispatchers.IO) {
            val gsonBoard = Gson().toJson(board)
            sharedPrefs.edit().putString(BOARD_PREFS_KEY, gsonBoard).apply()
        }

    fun getBoardFromSharedPrefs(): MutableList<Cell> {
        val gsonBoard = sharedPrefs.getString(BOARD_PREFS_KEY, String.empty)
        return Gson().fromJson(gsonBoard, object: TypeToken<MutableList<Cell>>() {}.type) ?: mutableListOf()
    }
}