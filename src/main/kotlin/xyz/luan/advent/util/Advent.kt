package xyz.luan.advent.util

import java.net.URL

fun readFile(path: String): List<String> {
    return loadResource("xyz/luan/advent/$path").readText().lines()
}

private fun loadResource(path: String): URL {
    val resource = Thread.currentThread().contextClassLoader.getResource(path)
    requireNotNull(resource) { "Resource $path not found" }
    return resource
}
