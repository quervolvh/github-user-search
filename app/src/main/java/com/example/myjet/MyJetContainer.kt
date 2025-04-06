package com.example.myjet

import android.content.Context
import com.example.myjet.src.repository.github.GithubRepository
import com.example.myjet.src.repository.github.impl.GithubRepositoryImpl

interface MyJetInterface {
    val githubRepository: GithubRepository
}

class MyJetContainer(private val applicationContext: Context) : MyJetInterface {

    override val githubRepository: GithubRepository by lazy {
        GithubRepositoryImpl()
    }

}