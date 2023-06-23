package com.example.independentsecondarydisplay

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import com.example.independentsecondarydisplay.databinding.PresentationSecondaryBinding

class SecondaryPresentation(context: Context, display: Display) : Presentation(context, display) {
    private val TAG = "SecondaryPresentation"

    private lateinit var _binding: PresentationSecondaryBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = PresentationSecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setSecondaryText(receiveText: String) {
        binding.secondaryTv.text = receiveText
    }
}
