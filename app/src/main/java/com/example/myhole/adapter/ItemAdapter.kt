package com.example.myhole.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myhole.HomeScreenFragment
import com.example.myhole.R
import com.example.myhole.data.HomeScreenViewModel
import com.example.myhole.databinding.ListItemBinding
import com.example.myhole.model.Hole
import com.example.myhole.model.Interact
import com.example.myhole.network.HustHoleApi
import com.example.myhole.network.HustHoleApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

/**
 *@classname ItemAdapter
 * @description:
 * @date :2022/8/6 12:51
 * @version :1.0
 * @author
 */
class ItemAdapter(private val viewModel: HomeScreenViewModel) : ListAdapter<Hole,
        ItemAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(
        private var binding: ListItemBinding,
        private val viewModel: HomeScreenViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hole: Hole) {
            binding.apply {
                thumbsUp.setOnClickListener {
                    if (binding.hole?.isThumb == true) {
                        binding.thumbsUp.setImageResource(R.drawable.ic_thumb_inactive)
                        binding.hole?.thumb = binding.hole?.thumb!! - 1
                        binding.upNum.text = binding.hole?.thumb.toString()
                        viewModel.postUnLike(Interact(hole.holeID))
                    } else {
                        binding.thumbsUp.setImageResource(R.drawable.ic_thumbs_active)
                        binding.hole?.thumb = binding.hole?.thumb!! + 1
                        binding.upNum.text = binding.hole?.thumb.toString()
                        viewModel.postLike(Interact(hole.holeID))
                    }
                    binding.hole?.isThumb = !binding.hole?.isThumb!!
                }
                imgStar.setOnClickListener {
                    if (binding.hole?.isFollow == true) {
                        binding.imgStar.setImageResource(R.drawable.ic_thumb_inactive)
                        binding.hole?.follow = binding.hole?.follow!! - 1
                        binding.textStar.text = binding.hole?.follow.toString()
                        viewModel.postUnFollow(Interact(hole.holeID))
                    } else {
                        binding.imgStar.setImageResource(R.drawable.ic_thumbs_active)
                        binding.hole?.follow = binding.hole?.follow!! + 1
                        binding.textStar.text = binding.hole?.follow.toString()
                        viewModel.postFollow(Interact(hole.holeID))
                    }
                    binding.hole?.isFollow= !binding.hole?.isFollow!!
                }
                this.hole = hole
                executePendingBindings()
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
            ), viewModel
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