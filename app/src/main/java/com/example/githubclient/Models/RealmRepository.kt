package com.example.githubclient.Models

import io.realm.RealmObject

open class RealmRepository : RealmObject() {
    var id: Int? = null
    lateinit var name: String
    lateinit var description: String
    lateinit var language: String
    var forksCount: Int? = null
    var stargazersCount: Int? = null
    lateinit var ownerLogin: String
    lateinit var ownerAvatarUrl: String
    lateinit var commitsUrl: String
}