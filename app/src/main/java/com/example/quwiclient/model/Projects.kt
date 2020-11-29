package com.example.quwiclient.model

import com.google.gson.annotations.SerializedName

data class ProjectList(
    @SerializedName("projects")
    val projects: List<Project>
)

data class Project (
    val id: Long,
    val name: String,
    val uid: String,

    @SerializedName("logo_url")
    val logoURL: String? = null,

    val position: Long,

    @SerializedName("is_active")
    var isActive: Long,

    @SerializedName("has_starred")
    val hasStarred: Boolean
)

data class Info(
    val project: ProjectInfo
)

data class ProjectInfo(
    val id: Long,
    val name: String,
    val uid: String,

    @SerializedName("logo_url")
    val logoURL: String,

    val position: Long,

    @SerializedName("is_active")
    val isActive: Long,

    @SerializedName("is_owner_watcher")
    val isOwnerWatcher: Long,

    @SerializedName("dta_user_since")
    val dtaUserSince: String,

    val users: List<Users>
)

data class Users(
    val id: Long,
    val name: String,

    @SerializedName("avatar_url")
    val avatarURL: Any? = null,

    @SerializedName("is_online")
    val isOnline: Long,

    @SerializedName("dta_activity")
    val dtaActivity: String,

    @SerializedName("dta_since")
    val dtaSince: String? = null
)
