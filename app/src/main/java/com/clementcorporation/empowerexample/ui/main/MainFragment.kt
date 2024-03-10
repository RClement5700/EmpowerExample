package com.clementcorporation.empowerexample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.clementcorporation.empowerexample.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        renderTreeChart(listOf(3,2,4,1), binding.mainLayout)
        return binding.root
    }

    private fun renderTreeChart(list: List<Int>, parent: ViewGroup) {
        val colors = listOf(
            requireContext().getColor(android.R.color.holo_red_dark),
            requireContext().getColor(android.R.color.holo_blue_dark),
            requireContext().getColor(android.R.color.holo_green_dark),
            requireContext().getColor(android.R.color.black),
            requireContext().getColor(android.R.color.holo_purple),
            requireContext().getColor(android.R.color.holo_orange_dark)
        )
        parent.viewTreeObserver.addOnGlobalLayoutListener {
            var height = parent.height
            var width = parent.width
            var divideVertically = true
            var colorsIndex = 0
            val subView = RelativeLayout(requireContext()).apply {
                setBackgroundColor(colors[colorsIndex])
                layoutParams = RelativeLayout.LayoutParams(width, height)
            }
            parent.addView(subView)
            list.forEach { _ ->
                if (divideVertically) width /= 2
                else height /= 2
                divideVertically = !divideVertically
                if (colorsIndex > colors.size - 1) colorsIndex = 0 else colorsIndex++
                subView.addView(RelativeLayout(requireContext()).apply {
                    setBackgroundColor(colors[colorsIndex])
                    layoutParams = RelativeLayout.LayoutParams(width, height)
                })
            }
        }
    }
}