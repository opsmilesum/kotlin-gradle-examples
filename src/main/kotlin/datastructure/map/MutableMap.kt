package datastructure.map

class Site(val map: Map<String, Any?>) {
    val name: String by map
    val url: String by map
}

fun main() {
    val map = mutableMapOf(
        "name" to "baidu",
        "url" to "www.baidu.com"
    )
    val site = Site(map)

    println(site.name)
    println(site.url)

    map.put("name", "Tencent")
    println(site.name)
    println(site.url)
}