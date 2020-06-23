package com.example.sudokuapp.net

import com.example.sudokuapp.model.Board
import retrofit2.http.GET

interface SudokuRestApi {

    @GET(value = "/board?difficulty=easy")
    suspend fun getBoard(): Board
}