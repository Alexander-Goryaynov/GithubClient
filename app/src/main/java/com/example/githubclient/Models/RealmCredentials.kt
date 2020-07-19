package com.example.githubclient.Models

import io.realm.RealmObject

open class RealmCredentials : RealmObject() {
    lateinit var login: String
    lateinit var password: String
}