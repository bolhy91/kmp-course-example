package xyz.bolhy91.kmp_kodeco_example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform