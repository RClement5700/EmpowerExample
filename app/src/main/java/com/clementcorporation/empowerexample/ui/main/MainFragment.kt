package com.clementcorporation.empowerexample.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.clementcorporation.empowerexample.databinding.FragmentMainBinding

data class EmpowerObject(val color: Int, val title: String)
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        val weights = listOf(3,2,4,1)
        renderTreeChart(weights, binding.mainLayout)
        return binding.root
    }

    private fun renderTreeChart(weights: List<Int>, parent: ViewGroup) {
        val tools = listOf(
            EmpowerObject(requireContext().getColor(android.R.color.holo_red_dark), "Stocks"),
            EmpowerObject(requireContext().getColor(android.R.color.holo_blue_dark), "Bonds"),
            EmpowerObject(requireContext().getColor(android.R.color.holo_purple), "Crypto"),
            EmpowerObject(requireContext().getColor(android.R.color.holo_orange_dark), "Cash")
            //ADD COLOR PER WEIGHT
        )
        val grid = GridLayout(requireContext()).apply {
            rowCount = weights.size/2
            columnCount = weights.size/2
            orientation = GridLayout.VERTICAL
            alignmentMode = GridLayout.ALIGN_MARGINS
            isColumnOrderPreserved = false
            isRowOrderPreserved = false
            useDefaultMargins = false
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
            )
        }
        weights.forEachIndexed { index, weight ->
            val tool = tools[index]
            val text = TextView(requireContext()).apply {
                text = tool.title
                textSize = 14f
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                gravity = Gravity.CENTER
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                setTextColor(resources.getColor(android.R.color.white, requireContext().theme))
            }
            val newView = LinearLayout(requireContext()).apply {
                addView(text)
                setBackgroundColor(tool.color)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
            }
            val rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, weight.toFloat())
            val colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, weight.toFloat())
            val params = GridLayout.LayoutParams(rowSpan, colSpan)
            grid.addView(newView, params)
        }
        parent.addView(grid)
    }
}