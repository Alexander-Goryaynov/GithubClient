package com.example.githubclient.Models

import java.time.LocalDate

data class Commit (
    val message: String?,
    val date: String?,
    val authorLogin: String?,
    val authorAvatarUrl: String?
)