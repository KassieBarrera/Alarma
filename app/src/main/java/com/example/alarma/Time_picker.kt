package com.example.alarma

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.time_picker.*
import kotlinx.android.synthetic.main.time_picker.view.*

class Time_picker : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val miVista = inflater.inflate(R.layout.time_picker, container, false)
        val btnHecho = miVista.btnHecho

        btnHecho.setOnClickListener {
            val mainActivity = activity as MainActivity
            if (Build.VERSION.SDK_INT >= 23) {
                mainActivity.setearHora(time_picker.hour, time_picker.minute)
            } else {
                mainActivity.setearHora(time_picker.currentHour, time_picker.currentMinute)
            }
            this.dismiss()
        }
        return miVista
    }

}
