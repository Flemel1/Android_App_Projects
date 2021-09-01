package com.example.coronaapp.data.util

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LayoutManagerCustom(
    context: Context,
    orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {
    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams? {
        return spanLayoutSize(super.generateLayoutParams(lp))
    }

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        return super.checkLayoutParams(lp)
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun generateLayoutParams(
        c: Context?,
        attrs: AttributeSet?
    ): RecyclerView.LayoutParams? {
        return spanLayoutSize(super.generateLayoutParams(c, attrs))
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams? {
        return spanLayoutSize(super.generateDefaultLayoutParams())
    }

    private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams? {
        if (orientation == HORIZONTAL) {
            layoutParams.width = Math.round(getHorizontalSpace() / itemCount.toDouble()).toInt()
        } else if (orientation == VERTICAL) {
            layoutParams.height = Math.round(getVerticalSpace() / itemCount.toDouble()).toInt()
        }
        return layoutParams
    }

    private fun getHorizontalSpace(): Int {
        return width - paddingRight - paddingLeft
    }

    private fun getVerticalSpace(): Int {
        return height - paddingBottom - paddingTop
    }
}