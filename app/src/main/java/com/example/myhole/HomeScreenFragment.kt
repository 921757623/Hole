package com.example.myhole

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myhole.adapter.ItemAdapter
import com.example.myhole.adapter.SpaceItemDecoration
import com.example.myhole.data.HomeScreenViewModel
import com.example.myhole.databinding.HomeScreenFragmentBinding
import com.example.myhole.network.HustHoleApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment() {
    private lateinit var binding: HomeScreenFragmentBinding
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeScreenFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = ItemAdapter()
        binding.recyclerView.addItemDecoration(SpaceItemDecoration(0, 20))
        initRefresh()
    }

    private fun initRefresh() {
        binding.refreshLayout.apply {
//            setRefreshHeader(StandardRefreshHeader(activity)) //设置自定义刷新头
//            setRefreshFooter(StandardRefreshFooter(activity)) //设置自定义刷新底
            setOnRefreshListener {    //下拉刷新触发
                viewModel.getHomeScreenHoles()
                finishRefresh()
            }
            setOnLoadMoreListener {
                if (viewModel.holeList.value == null) { //特殊情况，首次加载没加载出来又选择上拉加载
                    viewModel.getHomeScreenHoles()
                } else
                    viewModel.getMoreHoles(viewModel.time.value!!.plus(1))
                finishLoadMore()
            }
        }
    }
}
