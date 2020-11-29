package com.example.quwiclient.model

import com.google.gson.annotations.SerializedName

data class NewNameRequest(
    val name: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse (
    val token: String,

    @SerializedName("app_init")
    val appInit: AppInit,

    @SerializedName("just_signup")
    val justSignup: Boolean
)


data class AppInit (
    val user: User,

    @SerializedName("user_ids")
    val userIDS: List<Long>,


    @SerializedName("auth_items")
    val authItems: AuthItems,
    @SerializedName("chats_count_unread")
    val chatsCountUnread: Long,
    @SerializedName("id_channel_main")
    val idChannelMain: Long,
    @SerializedName("has_projects")
    val hasProjects: Boolean,
    @SerializedName("has_own_company")
    val hasOwnCompany: Boolean,
    @SerializedName("hidden_projects")
    val hiddenProjects: List<Any?>
)

data class AuthItems (
    val owner: Boolean,
    val coder: Boolean,
    val nub: Boolean,

    @SerializedName("disc_space")
    val discSpace: Boolean,
    @SerializedName("tracked_timer_month")
    val trackedTimerMonth: Boolean,
    @SerializedName("screens_month")
    val screensMonth: Boolean,

    val chat: Boolean,

    @SerializedName("instant_screen")
    val instantScreen: Boolean,
    @SerializedName("timelapse_video")
    val timelapseVideo: Boolean,
    @SerializedName("member_area")
    val memberArea: Boolean
)



data class User (
    val id: Long,

    @SerializedName("id_company")
    val idCompany: Long,

    val role: String,
    val name: String,

    @SerializedName("dta_create")
    val dtaCreate: String,
    @SerializedName("avatar_url")
    val avatarURL: Any? = null,
    @SerializedName("is_online")
    val isOnline: Long,
    @SerializedName("dta_activity")
    val dtaActivity: String,
    @SerializedName("is_timer_online")
    val isTimerOnline: Long,
    @SerializedName("dta_timer_activity")
    val dtaTimerActivity: Any? = null,
    @SerializedName("timer_last")
    val timerLast: Any? = null,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("timezone_offset")
    val timezoneOffset: Long,
    @SerializedName("due_penalties")
    val duePenalties: Long,
    @SerializedName("allow_timer_screens")
    val allowTimerScreens: Boolean,
    @SerializedName("allow_timer_autostop")
    val allowTimerAutostop: Boolean,
    @SerializedName("is_shared_from_me")
    val isSharedFromMe: Boolean,
    @SerializedName("is_shared_for_me")
    val isSharedForMe: Boolean,

    val projects: List<Project>,
    val email: String,

    @SerializedName("email_signature")
    val emailSignature: Any? = null
)


