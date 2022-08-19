package com.example.myhole.adapter

import android.graphics.Rect
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhole.R
import com.example.myhole.data.HustHoleApiStatus
import com.example.myhole.model.Hole
import com.example.myhole.network.HustHoleApiService


/**
 *@classname CustomBindingAdapter
 * @description:
 * @date :2022/8/17 20:28
 * @version :1.0
 * @author
 */

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Hole>?
) {
    val adapter = recyclerView.adapter as ItemAdapter
    adapter.submitList(data)
}

/**
 * @description:自定义设置item间距
 */
class SpaceItemDecoration(
    private val leftRight: Int,
    private val topBottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutManager: LinearLayoutManager = parent.layoutManager as LinearLayoutManager
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
            //最后一项需要 bottom
            if (parent.getChildAdapterPosition(view) == layoutManager.itemCount - 1) {
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.right = leftRight;
        } else {
            //最后一项需要right
            if (parent.getChildAdapterPosition(view) == layoutManager.itemCount - 1) {
                outRect.right = leftRight;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.bottom = topBottom;
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(
    statusView: ImageView,
    status: HustHoleApiStatus?) {
    when(status) {
        HustHoleApiStatus.LOADING -> {
            statusView.visibility = View.INVISIBLE
        }
        HustHoleApiStatus.DONE -> {
            statusView.visibility = View.GONE
        }
        HustHoleApiStatus.ERROR -> {
            statusView.visibility = View.VISIBLE
            statusView.setImageResource(R.drawable.error)
        }
    }
}
@BindingAdapter("apiStatus")
fun bindStatus(
    statusView: TextView,
    status: HustHoleApiStatus?) {
    when(status) {
        HustHoleApiStatus.LOADING -> {
            statusView.visibility = View.INVISIBLE
        }
        HustHoleApiStatus.DONE -> {
            statusView.visibility = View.GONE
        }
        HustHoleApiStatus.ERROR -> {
            statusView.visibility = View.VISIBLE
        }
    }
}
