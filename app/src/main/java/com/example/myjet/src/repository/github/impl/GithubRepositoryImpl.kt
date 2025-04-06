package com.example.myjet.src.repository.github.impl

import com.example.myjet.src.models.GithubSearchModel
import com.example.myjet.src.models.GithubUserModel
import com.example.myjet.src.network.RetrofitInstance
import com.example.myjet.src.repository.github.GithubRepository

class GithubRepositoryImpl : GithubRepository {

    override suspend fun getUser(searchString: String): GithubSearchModel {
        return RetrofitInstance.retrofitService.findUser(mapOf("q" to searchString))
    }

    override suspend fun getSingleUser(username: String): GithubUserModel {
        return RetrofitInstance.retrofitService.findSingleUser(username)
    }

    override suspend fun getUserFollowers(username: String): List<GithubUserModel> {
        return RetrofitInstance.retrofitService.getUserFollowers(username)
    }

    override suspend fun getUserFollowing(username: String): List<GithubUserModel> {
        return RetrofitInstance.retrofitService.getUserFollowing(username)
    }

}
