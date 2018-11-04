package com.example.lena.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.lena.myapplication.model.Cell
import com.example.lena.myapplication.utils.ImageResolver

class ImageAdapter(private var context: Context, private var grid: Array<Array<Cell?>>) : BaseAdapter() {
    private var inflater: LayoutInflater

    init {
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        if (convertView == null) {
            view = ImageView(context)
            view.layoutParams = ViewGroup.LayoutParams(200, 200)
            view.scaleType = ImageView.ScaleType.CENTER_CROP
            view.setPadding(5, 5, 5, 5)
            view.setOnClickListener {

                //TODO : do not allow click if flagged
                var cell = grid.at(position)!!

                ContextHolder.getInstance().mineSweeper.click(cell, grid)

                (it as ImageView).setImageResource(ImageResolver.getImage(cell))

                ContextHolder.getInstance().updateView()
            }

            view.setOnLongClickListener {

                var cell = grid.at(position)!!

                ContextHolder.getInstance().mineSweeper.flag(cell)

                ContextHolder.getInstance().updateFlagsLeftText()

                (it as ImageView).setImageResource(ImageResolver.getImage(cell))

                true
            }
        } else {
            view = convertView as ImageView
        }
        view.setImageResource(ImageResolver.getImage(grid.at(position)!!))

        // TODO : inactivate buttons after loss
        return view

    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return grid.size * grid.size
    }

    fun Array<Array<Cell?>>.at(position: Int): Cell? {
        val x = position % size
        val y = position / size

        return this[x][y]
    }
}
