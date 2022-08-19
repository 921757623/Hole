package com.example.myhole.data

import com.example.myhole.R
import com.example.myhole.model.Hole
import java.text.SimpleDateFormat
import kotlin.random.Random

/**
 *@classname DataSource
 * @description:
 * @date :2022/8/6 12:53
 * @version :1.0
 * @author
 */
//class DataSource {
//    fun loadData() : List<Hole> {
//        return listOf(
//            Hole(getRandomDate(),R.string.content_1,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_2,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_3,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_4,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_5,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_6,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_7,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_8,(0..60).random(),(0..20).random(),(0..10).random()) ,
//            Hole(getRandomDate(),R.string.content_9,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_10,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_11,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_12,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_13,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_14,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_15,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_16,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_17,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_18,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_19,(0..60).random(),(0..20).random(),(0..10).random()),
//            Hole(getRandomDate(),R.string.content_20,(0..60).random(),(0..20).random(),(0..10).random()),
//        )
//    }
//
//    private fun getRandomDate(): String {
//        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
//        val start: Long = sdf.parse("2022-08-06 00:00").time
//        val end: Long = sdf.parse("2022-08-06 00:01").time
//        return sdf.format(start + (Random.nextDouble() * (end - start + 1)))
//    }
//
//}