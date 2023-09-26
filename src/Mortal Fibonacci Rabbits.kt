fun calculateRabbits(months: Int, ratio: Int, live_rabbit: Int): Long {
    if (months == 1 || months == 2) return 1
    var live = Array<Long>(live_rabbit) { 0 }
    live[0] = 1
    for (i in 2 until months+1) {
        live = calculateRabbits2(live, ratio)
    }
    var sum = 0L
    for (i in 0 until live.size) {
        sum += live[i]
    }
    return sum
}

fun calculateRabbits2(pedigree: Array<Long>, ratio: Int): Array<Long> {
    var result = pedigree.copyOf()
    var sum = 0L
    var k = result[0]
    for (i in 1 until pedigree.size) {
        sum += pedigree[i] * ratio
        result[i] = pedigree[i-1]
    }
    result[0] = sum
    return result
}

fun main() {
    val months = 85
    val ratio = 1
    val live_rabbit = 19
    val totalRabbits = calculateRabbits(months, ratio, live_rabbit)
    println("Total number of rabbits after $months months: $totalRabbits")
}
