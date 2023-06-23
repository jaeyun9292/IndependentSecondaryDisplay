package com.example.independentsecondarydisplay

import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.appcompat.app.AppCompatActivity
import com.example.independentsecondarydisplay.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), PresentationInteraction {
    private val TAG = "MainActivity"

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var secondaryPresentation: SecondaryPresentation
    private lateinit var tertiaryPresentation: TertiaryPresentation

    private lateinit var displays: Array<Display>
    private val screenManager = ScreenManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenManager.setFullScreen()

        val displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        displays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)

        if (displays.isNotEmpty()) {
            for ((i, display) in displays.withIndex()) {
                Log.e(TAG, "display[$i] : $display ")
                val presentationDisplay = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)[i]
                when (i) {
                    0 -> secondaryPresentation = SecondaryPresentation(this, presentationDisplay)
                    1 -> tertiaryPresentation = TertiaryPresentation(this, presentationDisplay, this)
                    else -> continue
                }
            }
        }

        secondaryPresentation.show()
        tertiaryPresentation.show()

        binding.secondaryBtn.setOnClickListener {
            val isSecondaryBtnSelected = binding.secondaryBtn.isSelected
            binding.secondaryBtn.isSelected = !isSecondaryBtnSelected
            val secondaryText = if (isSecondaryBtnSelected) "Change!!!!!!" else "Change"
            secondaryPresentation.setSecondaryText(secondaryText)
        }

        binding.tertiaryBtn.setOnClickListener {
            val isTertiaryBtnSelected = binding.tertiaryBtn.isSelected
            binding.tertiaryBtn.isSelected = !isTertiaryBtnSelected
            val tertiaryText = if (isTertiaryBtnSelected) "Change!!!!!!" else "Change"
            tertiaryPresentation.setTertiaryText(tertiaryText)
        }

    }

    override fun setMainText(text: String) {
        binding.mainTv.text = text
    }
}