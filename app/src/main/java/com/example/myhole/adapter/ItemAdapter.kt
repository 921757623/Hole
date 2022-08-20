package com.example.myhole.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myhole.HomeScreenFragment
import com.example.myhole.R
import com.example.myhole.data.HomeScreenViewModel
import com.example.myhole.data.HustHoleApiStatus
import com.example.myhole.databinding.ListItemBinding
import com.example.myhole.model.Hole
import com.example.myhole.model.Interact
import com.example.myhole.network.HustHoleApi
import com.example.myhole.network.HustHoleApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.ResponseBody
import java.lang.Exception
import kotlin.coroutines.suspendCoroutine

/**
 *@classname ItemAdapter
 * @description:
 * @date :2022/8/6 12:51
 * @version :1.0
 * @author
 */
private const val TAG = "ItemAdapter"

class ItemAdapter(
    private val homeScreenFragment: HomeScreenFragment
) : ListAdapter<Hole,
        ItemAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(
        private var binding: ListItemBinding,
        private val homeScreenFragment: HomeScreenFragment,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hole: Hole) {
            binding.apply {
                thumbsUp.setOnClickListener {
                    postLike(Interact(hole.holeID), binding.hole?.isThumb)
                }
                imgStar.setOnClickListener {
                    postFollow(Interact(hole.holeID), binding.hole?.isFollow)
                }
                this.hole = hole
                executePendingBindings()
            }
        }

        private fun postLike(
            data: Interact,
            isLike: Boolean?
        ) {
            try {
                if (isLike == true) {
                    CoroutineScope(IO).launch {
                        HustHoleApi.retrofitService.postInteractUnLike(data).execute()
                    }
                    binding.thumbsUp.setImageResource(R.drawable.ic_thumb_inactive)
                    binding.hole?.thumb = binding.hole?.thumb!! - 1
                } else {
                    CoroutineScope(IO).launch {
                        HustHoleApi.retrofitService.postInteractLike(data).execute()
                    }
                    binding.thumbsUp.setImageResource(R.drawable.ic_thumbs_active)
                    binding.hole?.thumb = binding.hole?.thumb!! + 1
                }
                binding.upNum.text = binding.hole?.thumb.toString()
                binding.hole?.isThumb = !binding.hole?.isThumb!!
            } catch (e: Exception) {
                Log.e(TAG, "POST LIKE ERROR")
                Toast.makeText(homeScreenFragment.context, "点赞失败!", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        private fun postFollow(
            data: Interact,
            isFollow: Boolean?
        ) {
            try {
                if (isFollow == true) {
                    CoroutineScope(IO).launch {
                        HustHoleApi.retrofitService.postInteractUnFollow(data).execute()
                    }
                    binding.imgStar.setImageResource(R.drawable.ic_follow_inactive)
                    binding.hole?.follow = binding.hole?.follow!! - 1
                } else {
                    CoroutineScope(IO).launch {
                        HustHoleApi.retrofitService.postInteractFollow(data).execute()
                    }
                    binding.imgStar.setImageResource(R.drawable.ic_follow_active)
                    binding.hole?.follow = binding.hole?.follow!! + 1
                }
                binding.textStar.text = binding.hole?.follow.toString()
                binding.hole?.isFollow = !binding.hole?.isFollow!!
            } catch (e: Exception) {
                Log.e(TAG, "POST FOLLOW ERROR")
                Toast.makeText(homeScreenFragment.context, "收藏失败!", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            ), homeScreenFragment
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Hole>() {
        override fun areItemsTheSame(oldItem: Hole, newItem: Hole): Boolean {
            return oldItem.holeID.equals(newItem.holeID)
        }

        override fun areContentsTheSame(oldItem: Hole, newItem: Hole): Boolean {
            return oldItem == newItem
        }
    }
}