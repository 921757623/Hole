package com.example.myhole.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.leanback.widget.DiffCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myhole.R
import com.example.myhole.SingleHoleFragment
import com.example.myhole.data.SingleHoleViewModel
import com.example.myhole.databinding.HeadItemBinding
import com.example.myhole.databinding.ReplyItemBinding
import com.example.myhole.model.Hole
import com.example.myhole.model.Interact
import com.example.myhole.model.ReplyOuterVO
import com.example.myhole.network.HustHoleApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *@classname SingleItemAdapter
 * @description:
 * @date :2022/8/20 16:05
 * @version :1.0
 * @author
 */
private const val TAG = "SingleItemAdapter"

enum class ITEMTYPE {
    ITEM_HEAD,
    ITEM_REPLY
}

class SingleItemAdapter(
    private val singleHoleFragment: SingleHoleFragment,
    private val holeId: String?,
    private val viewModel: SingleHoleViewModel
) : ListAdapter<ReplyOuterVO,
        RecyclerView.ViewHolder>(DiffCallback) {

    init{
        viewModel.getReply(holeId)
    }
    class HeadItemHolder(
        private var binding: HeadItemBinding,
        private val singleHoleFragment: SingleHoleFragment,
        private val viewModel: SingleHoleViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hole) {
            binding.apply {
                hole = item
                thumbsUp.setOnClickListener {
                    postLike(Interact(hole?.holeID), binding.hole?.isThumb)
                }
                imgStar.setOnClickListener {
                    postFollow(Interact(hole?.holeID), binding.hole?.isFollow)
                }
                executePendingBindings()
            }

        }

        private fun postLike(
            data: Interact,
            isLike: Boolean?
        ) {
            try {
                if (isLike == true) {
                    CoroutineScope(Dispatchers.IO).launch {
                        HustHoleApi.retrofitService.postInteractUnLike(data).execute()
                    }
                    binding.thumbsUp.setImageResource(R.drawable.ic_thumb_inactive)
                    binding.hole?.thumb = binding.hole?.thumb!! - 1
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        HustHoleApi.retrofitService.postInteractLike(data).execute()
                    }
                    binding.thumbsUp.setImageResource(R.drawable.ic_thumbs_active)
                    binding.hole?.thumb = binding.hole?.thumb!! + 1
                }
                binding.upNum.text = binding.hole?.thumb.toString()
                binding.hole?.isThumb = !binding.hole?.isThumb!!
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "POST LIKE ERROR")
                Toast.makeText(singleHoleFragment.context, "点赞失败!", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        private fun postFollow(
            data: Interact,
            isFollow: Boolean?
        ) {
            try {
                if (isFollow == true) {
                    CoroutineScope(Dispatchers.IO).launch {
                        HustHoleApi.retrofitService.postInteractUnFollow(data).execute()
                    }
                    binding.imgStar.setImageResource(R.drawable.ic_follow_inactive)
                    binding.hole?.follow = binding.hole?.follow!! - 1
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        HustHoleApi.retrofitService.postInteractFollow(data).execute()
                    }
                    binding.imgStar.setImageResource(R.drawable.ic_follow_active)
                    binding.hole?.follow = binding.hole?.follow!! + 1
                }
                binding.textStar.text = binding.hole?.follow.toString()
                binding.hole?.isFollow = !binding.hole?.isFollow!!
            } catch (e: java.lang.Exception) {
                Log.e(TAG, "POST FOLLOW ERROR")
                Toast.makeText(singleHoleFragment.context, "收藏失败!", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    class ReplyItemHolder(
        private var binding: ReplyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReplyOuterVO) {
            binding.apply {
                replys = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEMTYPE.ITEM_HEAD.ordinal)
            HeadItemHolder(
                HeadItemBinding.inflate(LayoutInflater.from(parent.context)),
                singleHoleFragment,
                viewModel
            )
        else
            ReplyItemHolder(ReplyItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is HeadItemHolder) {
            holder.bind(viewModel.hole.value!!)
        } else if (holder is ReplyItemHolder) {
            val item = getItem(position)
            holder.bind(item)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            ITEMTYPE.ITEM_HEAD.ordinal
        else
            ITEMTYPE.ITEM_REPLY.ordinal
    }

    override fun getItem(position: Int): ReplyOuterVO {
        return super.getItem(position - 1)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ReplyOuterVO>() {
        override fun areItemsTheSame(oldItem: ReplyOuterVO, newItem: ReplyOuterVO): Boolean {
            return oldItem.self?.holeID == newItem.self?.holeID
        }

        override fun areContentsTheSame(oldItem: ReplyOuterVO, newItem: ReplyOuterVO): Boolean {
            return oldItem == newItem
        }

    }
}