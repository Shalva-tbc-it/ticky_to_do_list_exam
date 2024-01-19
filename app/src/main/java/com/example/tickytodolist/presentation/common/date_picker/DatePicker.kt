package com.example.tickytodolist.presentation.common.date_picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment

class DatePicker(private val dateSelected: DataSelected) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, dayOfMonth)


    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateSelected.receiveDate(year, month, dayOfMonth)
        Log.d("MyTag", "Got the date")
    }
}