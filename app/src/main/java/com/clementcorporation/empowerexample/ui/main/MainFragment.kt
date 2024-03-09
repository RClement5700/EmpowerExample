package com.clementcorporation.empowerexample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.RelativeLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import com.clementcorporation.empowerexample.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        container?.let {
            renderTreeChart(listOf(3,2,4,1), it)
        }
        return view
    }

    private fun renderTreeChart(list: List<Int>, parent: ViewGroup) {
        val height = parent.height
        val width  = parent.width
        val sum = list.sum()
        var divideVertically = true
        var x = 0
        var y = 0
        list.forEachIndexed { index, weight ->
            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            params.leftMargin = x
            params.topMargin = y
            val textView = TextView(requireContext()).apply {
                text = "This text"
            }
            parent.addView(textView, params)
            if (divideVertically) x = width / 2
            if (!divideVertically) y = height / 2
            divideVertically = index % 3 != 0
        }
    }
}