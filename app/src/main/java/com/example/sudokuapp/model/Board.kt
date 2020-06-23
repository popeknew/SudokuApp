package com.example.sudokuapp.model

import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("board")
    val board: MutableList<MutableList<Int>>
)