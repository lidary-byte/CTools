package com.lidary.tools

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform