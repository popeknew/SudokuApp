package com.example.sudokuapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sudokuapp.R
import com.example.sudokuapp.databinding.ActivityMenuBinding
import com.example.sudokuapp.vm.MenuViewModel
import org.koin.android.ext.android.get

class MenuActivity : AppCompatActivity() {

    private val viewModel: MenuViewModel = get()
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_menu
        )

        with(binding) {
            viewModel = this@MenuActivity.viewModel
            newGameButton.setOnClickListener {
                startGame(startNewGame = true)
            }
            continueButton.setOnClickListener {
                startGame(startNewGame = false)
            }
        }
    }

    private fun startGame(startNewGame: Boolean) {
        PlaySudokuActivity.getIntent(
            this,
            startNewGame
        ).run { startActivity(this) }
    }
}