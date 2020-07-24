package app.kaisa.parky.ui.utils

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.kaisa.parky.R


class VerticalDivider(val context: Context?) : RecyclerView.ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if(context==null) { return }

        val divider = ContextCompat.getDrawable(context, R.drawable.divider_vertical)

        divider?.let {
            val dividerLeft: Int = context.resources.getDimension(R.dimen.spacing_normal).toInt()
            val dividerRight: Int = parent.width - dividerLeft

            val childCount: Int = parent.childCount
            for (i in 0..childCount - 2) {
                val child: View = parent.getChildAt(i)
                val params =
                    child.layoutParams as RecyclerView.LayoutParams
                val dividerTop = child.bottom + params.bottomMargin
                val dividerBottom: Int = dividerTop + divider.intrinsicHeight
                divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                divider.draw(canvas)
            }
        }
    }
}