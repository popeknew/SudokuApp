package com.example.sudokuapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.sudokuapp.R
import com.example.sudokuapp.databinding.ActivityPlaySudokuBinding
import com.example.sudokuapp.model.Cell
import com.example.sudokuapp.view.CheckGameDialogFragment
import com.example.sudokuapp.vm.PlaySudokuViewModel
import org.koin.android.ext.android.get

class PlaySudokuActivity : AppCompatActivity() {

    private val viewModel: PlaySudokuViewModel = get()
    private lateinit var binding: ActivityPlaySudokuBinding

    private val gameBoardObserver = Observer<MutableList<Cell>> { cells ->
        binding.sudokuBoardView.updateCells(cells)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startNewGame: Boolean = intent.getBooleanExtra(START_NEW_GAME, true)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_play_sudoku
        )

        binding.viewModel = this@PlaySudokuActivity.viewModel

        binding.sudokuBoardView.onSelectCellCallback = { selectedCell ->
            viewModel.updateSelectedCell(selectedCell.first, selectedCell.second)
        }

        with(viewModel) {
            startGame(startNewGame)
            gameBoard.observe(this@PlaySudokuActivity, gameBoardObserver)
        }

        handleCheckButton()
    }

    private fun handleCheckButton() {
        binding.checkButton.setOnClickListener {
            CheckGameDialogFragment(viewModel.checkSolution())
                .show(
                supportFragmentManager,
                    CHECK_GAME_DIALOG_TAG
            )
        }
    }

    companion object {
        private const val START_NEW_GAME = "startNewGame"
        private const val CHECK_GAME_DIALOG_TAG = "CheckGameDialogTAG"

        fun getIntent(context: Context, startNewGame: Boolean) =
            Intent(context, PlaySudokuActivity::class.java).apply {
                putExtra(START_NEW_GAME, startNewGame)
            }
    }
}