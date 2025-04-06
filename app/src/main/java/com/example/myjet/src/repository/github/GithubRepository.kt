package com.example.myjet.src.repository.github

import com.example.myjet.src.models.GithubSearchModel
import com.example.myjet.src.models.GithubUserModel

interface GithubRepository {
    suspend fun getUser(searchString: String): GithubSearchModel
    suspend fun getSingleUser(username: String): GithubUserModel
    suspend fun getUserFollowers(username: String): List<GithubUserModel>
    suspend fun getUserFollowing(username: String): List<GithubUserModel>
}
