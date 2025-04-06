package com.example.myjet.src.network
import com.example.myjet.src.models.GithubSearchModel
import com.example.myjet.src.models.GithubUserModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GithubUserApi {

    @GET("search/users")
    suspend fun findUser(@QueryMap parameters: Map<String, String>): GithubSearchModel

    @GET("/users/{user}")
    suspend fun findSingleUser(@Path("user") user : String): GithubUserModel

    @GET("/users/{user}/followers")
    suspend fun getUserFollowers(@Path("user") user : String): List<GithubUserModel>

    @GET("/users/{user}/following")
    suspend fun getUserFollowing(@Path("user") user : String): List<GithubUserModel>
}

