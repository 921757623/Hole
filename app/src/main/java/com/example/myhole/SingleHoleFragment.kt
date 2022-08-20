package com.example.myhole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myhole.adapter.SingleItemAdapter
import com.example.myhole.adapter.SpaceItemDecoration
import com.example.myhole.data.SingleHoleViewModel
import com.example.myhole.databinding.SingleHoleListFragmentBinding
import com.example.myhole.model.Hole


/**
 *@classname SingleHoleFragment
 * @description:
 * @date :2022/8/20 9:53
 * @version :1.0
 * @author
 */
class SingleHoleFragment : Fragment() {
    private lateinit var binding: SingleHoleListFragmentBinding
    private val viewModel: SingleHoleViewModel by viewModels()
    private var ownerHoleId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ownerHoleId = it.getString("holeId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SingleHoleListFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.singleRecycleView.adapter = SingleItemAdapter(this, ownerHoleId, viewModel)
        binding.singleRecycleView.addItemDecoration(SpaceItemDecoration(0, 20))
    }
}