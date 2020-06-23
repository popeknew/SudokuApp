package com.example.sudokuapp.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.sudokuapp.R

class CheckGameDialogFragment(private val solved: Boolean): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it).apply {
                setTitle(if (solved) R.string.dialog_congratulations else R.string.dialog_not_this_time)
                setMessage(if (solved) R.string.dialog_solved_message else R.string.dialog_unsolved_message)
                setPositiveButton(if (solved) R.string.dialog_solved_button_text else R.string.dialog_unsolved_button_text) {
                    dialog, _ -> dialog.dismiss()
                }
            }
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}