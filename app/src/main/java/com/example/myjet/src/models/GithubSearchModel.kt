package com.example.myjet.src.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubSearchModel(
    @SerializedName("total_count")
    @Expose
    val totalCount: Long?,
    @SerializedName("incomplete_results")
    @Expose
    val incompleteResults: Boolean?,
    val items: List<GithubUserModel>?,
)

data class GithubUserModel(
    val login: String,
    val id: Long,
    val name: String?,
    val bio: String?,
    val followers: Long?,
    val following: Long?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String?,
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("followers_url")
    val followersUrl: String?,
    @SerializedName("following_url")
    val followingUrl: String?,
    @SerializedName("gists_url")
    val gistsUrl: String?,
    @SerializedName("starred_url")
    val starredUrl: String?,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerializedName("organizations_url")
    val organizationsUrl: String?,
    @SerializedName("repos_url")
    val reposUrl: String?,
    @SerializedName("events_url")
    val eventsUrl: String?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String?,
    val type: String?,
    @SerializedName("user_view_type")
    val userViewType: String?,
    @SerializedName("site_admin")
    val siteAdmin: Boolean?,
    val score: Double?,
)
