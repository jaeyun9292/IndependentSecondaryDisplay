package com.example.independentsecondarydisplay

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Display
import com.example.independentsecondarydisplay.databinding.PresentationTertiaryBinding

class TertiaryPresentation(context: Context, display: Display, private val presentationInteraction: PresentationInteraction) : Presentation(context, display) {
    private val TAG = "TertiaryPresentation"

    private lateinit var _binding: PresentationTertiaryBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = PresentationTertiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBtn.setOnClickListener {
            val isMainBtnSelected = binding.mainBtn.isSelected
            binding.mainBtn.isSelected = !isMainBtnSelected
            val mainText = if (isMainBtnSelected) "Change!!!!!!" else "Change"
            Log.e(TAG, "presentationInteraction: !!!")
            presentationInteraction.setMainText(mainText)
        }
    }

    fun setTertiaryText(receiveText: String) {
        binding.tertiaryTv.text = receiveText
    }
}
