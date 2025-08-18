package com.example.simplesharing

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform