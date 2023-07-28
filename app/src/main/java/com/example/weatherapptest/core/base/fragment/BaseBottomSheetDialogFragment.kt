package com.example.weatherapptest.core.base.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.weatherapptest.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BaseLayout_BottomSheetDialog)
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = inflateDialogContentView()
        dialog.setContentView(view)
        val bottomSheet =
            dialog.findViewById<ViewGroup>(com.google.android.material.R.id.design_bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        setupDialogContentView(view, dialog, bottomSheet, bottomSheetBehavior)
    }

    protected abstract fun inflateDialogContentView(): View
    protected abstract fun setupDialogContentView(
        view: View,
        dialog: Dialog,
        bottomSheet: View,
        bottomSheetBehavior: BottomSheetBehavior<*>
    )

    protected fun showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), msg, length).show()
    }

    fun showSnackBar(
        msg: String,
        view: View = requireActivity().window.decorView.rootView,
        snackBarLength: Int = Snackbar.LENGTH_SHORT
    ) {
        Snackbar.make(
            view, msg, snackBarLength
        ).show()
    }

}