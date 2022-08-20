package com.example.myhole.model

import android.widget.ImageView
import com.example.myhole.R
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

/**
 *@classname Hole
 * @description:
 * @date :2022/8/6 12:54
 * @version :1.0
 * @author
 */

class Interact(
    val holeId: String?
) {
}

/**
 * Hole
 */
data class Hole(
    /**
     * 内容
     */
    val content: String? = null,

    /**
     * 发布时间
     */
    val creatAt: String? = null,

    /**
     * 收藏数
     */
    var follow: Int? = null,

    /**
     * 小树林id
     */
    @Json(name = "forestId")
    val forestID: String? = null,

    /**
     * 小树林名字
     */
    val forestName: String? = null,

    /**
     * 树洞id
     */
    @Json(name = "holeId")
    val holeID: String? = null,

    /**
     * 是否收藏
     */
    var isFollow: Boolean? = null,

    /**
     * 是否属于自己
     */
    var isMine: Boolean? = null,

    /**
     * 是否评论
     */
    var isReply: Boolean? = null,

    /**
     * 是否点赞
     */
    var isThumb: Boolean? = null,

    /**
     * 最新评论时间
     */
    val lastReplyAt: String? = null,

    /**
     * 评论数
     */
    var reply: Int? = null,

    /**
     * 点赞数
     */
    var thumb: Int? = null
) {

}

/**
 * SignInRequest
 */
data class SignIn(
    /**
     * 邮箱
     */
    val email: String? = null,

    /**
     * 密码
     */
    val password: String? = null,

    /**
     * 角色
     */
    val role: Role? = null
) {}

/**
 * 角色
 */
enum class Role(val value: String) {
    Admin("ADMIN"),
    All("ALL"),
    Ccps("CCPS"),
    Official("OFFICIAL"),
    RoleSYSTEM("SYSTEM"),
    User("USER");

    companion object {
        public fun fromValue(value: String): Role = when (value) {
            "ADMIN" -> Admin
            "ALL" -> All
            "CCPS" -> Ccps
            "OFFICIAL" -> Official
            "SYSTEM" -> RoleSYSTEM
            "USER" -> User
            else -> throw IllegalArgumentException()
        }
    }
}

data class ReplyOuterVO(
    /**
     * 子评论总数
     */
    val count: String? = null,

    /**
     * 子评论
     */
    val innerList: List<ReplyVO>? = null,

    val self: ReplyVO? = null
)

/**
 * ReplyVO
 */
data class ReplyVO(
    /**
     * 树洞内容
     */
    val content: String? = null,

    /**
     * 发布时间
     */
    val creatAt: String? = null,

    val hole: HoleDto? = null,

    /**
     * 树洞id
     */
    @Json(name = "holeId")
    val holeID: String? = null,

    /**
     * 点赞数
     */
    val likeCount: Long? = null,

    /**
     * 是否我的
     */
    val mine: Boolean? = null,

    /**
     * 昵称
     */
    val nickname: String? = null,

    val replied: ReplyDto? = null,

    /**
     * 评论id
     */
    @Json(name = "replyId")
    val replyID: String? = null,

    /**
     * 回复的评论id
     */
    val replyTo: String? = null,

    /**
     * 楼内回复的评论id
     */
    val replyToInner: String? = null,

    /**
     * 是否点赞
     */
    val thumb: Boolean? = null
)

/**
 * HoleDto
 */
data class HoleDto(
    val content: String? = null,
    val createAt: String? = null,
    val deleteAt: String? = null,
    val followCount: Long? = null,

    @Json(name = "forestId")
    val forestID: String? = null,

    @Json(name = "holeId")
    val holeID: String? = null,

    val lastReplyAt: String? = null,
    val likeCount: Long? = null,

    @Json(name = "ownerId")
    val ownerID: String? = null,

    val participant: Long? = null,
    val replyCount: Long? = null,
    val updateAt: String? = null
)

/**
 * ReplyDto
 */
data class ReplyDto(
    val content: String? = null,
    val createAt: String? = null,
    val deleteAt: String? = null,

    @Json(name = "holeId")
    val holeID: String? = null,

    val likeCount: Long? = null,
    val nickname: String? = null,

    @Json(name = "repliedUserId")
    val repliedUserID: String? = null,

    @Json(name = "replyId")
    val replyID: String? = null,

    val replyTo: String? = null,
    val replyToInner: String? = null,
    val updateAt: String? = null,

    @Json(name = "userId")
    val userID: String? = null
)