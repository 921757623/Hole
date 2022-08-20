package com.example.myhole.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhole.model.Hole

/**
 *@classname SingleHoleViewModel
 * @description:
 * @date :2022/8/20 9:54
 * @version :1.0
 * @author
 */
enum class SingleHoleApiStatus { LOADING, ERROR, DONE }

class SingleHoleViewModel : ViewModel() {
    private val _singleHoleList = MutableLiveData<List<Hole>>()

    val singleHoleList: LiveData<List<Hole>> = _singleHoleList
}