package com.gooseok.sample.network

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "GitUserInfo")
data class GitUserInfo(
    @PrimaryKey(autoGenerate = true) var idx : Int = 0,
    @Ignore @SerializedName("avatar_url") var avatarUrl: String = "",
//    var bio: Any? = null,
    @Ignore var blog: String = "",
//    var company: Any? = null,
    @SerializedName("created_at") var createdAt: String? = "",
    var email: String? = "",
    @Ignore @SerializedName("events_url") var eventsUrl: String = "",
    var followers: Int = 0,
    @Ignore @SerializedName("followersUrl") var followersUrl: String = "",
    var following: Int = 0,
    @Ignore @SerializedName("following_url") var followingUrl: String = "",
    @Ignore @SerializedName("gists_url") var gistsUrl: String = "",
    @Ignore @SerializedName("gravatar_id") var gravatarId: String = "",
//    var hireable: Any? = null,
    @SerializedName("html_url") var htmlUrl: String? = "",
    var id: Int = 0,
//    var location: Any? = null,
    @SerializedName("login") var userId : String = "",
    @SerializedName("name") var userName : String? = "",
    @SerializedName("node_id_") var nodeId: String? = "",
    @Ignore @SerializedName("organizations_url") var organizationsUrl: String = "",
    @SerializedName("public_gists") var publicGists: Int = 0,
    @SerializedName("public_repos")var publicRepos: Int = 0,
    @Ignore @SerializedName("received_events_url")var receivedEventsUrl: String = "",
    @SerializedName("repos_url")var reposUrl: String? = "",
    @Ignore @SerializedName("site_admin")var siteAdmin: Boolean = false,
    @Ignore @SerializedName("starred_url")var starredUrl: String = "",
    @Ignore @SerializedName("subscriptions_url")var subscriptionsUrl: String = "",
    @SerializedName("twitter_username")var twitterUsername: String? = "",
    var type: String? = "",
    @SerializedName("updated_at")var updatedAt: String? = "",
    var url: String? = ""
)