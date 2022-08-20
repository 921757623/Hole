package com.example.myhole.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhole.model.Hole
import com.example.myhole.model.Interact
import com.example.myhole.model.ReplyOuterVO
import com.example.myhole.network.HustHoleApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 *@classname SingleHoleViewModel
 * @description:
 * @date :2022/8/20 9:54
 * @version :1.0
 * @author
 */
enum class SingleHoleApiStatus { LOADING, ERROR, DONE }

class SingleHoleViewModel : ViewModel() {
    private val _hole = MutableLiveData<Hole>()
    private val _replyList = MutableLiveData<List<ReplyOuterVO>>()
    private val _status = MutableLiveData<SingleHoleApiStatus>()

    val hole: LiveData<Hole> = _hole
    val replyList: LiveData<List<ReplyOuterVO>> = _replyList
    val status: LiveData<SingleHoleApiStatus> = _status

    fun getHole(holeID: String) {
        _status.value = SingleHoleApiStatus.LOADING
        viewModelScope.launch {
            try {
                _hole.value = HustHoleApi.retrofitService.getOneHole(holeID)
                _status.value = SingleHoleApiStatus.DONE
            } catch (e: Exception) {
                _status.value = SingleHoleApiStatus.ERROR
                Log.e("Single","getHole")
                e.printStackTrace()
            }
        }
    }

    fun getReply(holeID: String?) {
        val currentTime = getCurrentTime()
        _status.value = SingleHoleApiStatus.LOADING
        viewModelScope.launch {
            try {
                _replyList.value = HustHoleApi.retrofitService.getHoleReply(holeID, currentTime)
                _status.value = SingleHoleApiStatus.DONE
            }catch(e: Exception) {
                _replyList.value = listOf()
                _status.value = SingleHoleApiStatus.ERROR
                Log.e("Single","getReply")
                e.printStackTrace()
            }
        }
    }

    private fun getCurrentTime(): String {
        val date = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        return sdf.format(date).toString()
    }
}