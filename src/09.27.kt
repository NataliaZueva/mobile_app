fun findLongestRepeatingCycle(): Int {
    var longestCycle = 0
    var result = 0
    for (i in 2..1000) {
        val remainderList = mutableListOf<Int>()
        var numerator = 1
        var remainder = numerator % i
        while (remainder != 0 && remainder !in remainderList) {
            remainderList.add(remainder)
            numerator = remainder * 10
            remainder = numerator % i
        }
        if (remainder != 0 && remainderList.size > longestCycle) {
            longestCycle = remainderList.size
            result = i
        }
    }
    return result
}

fun main() {
    val longestRepeatingCycle = findLongestRepeatingCycle()
    println("Знаменатель с самым длинным повторяющимся циклом: $longestRepeatingCycle")
}
