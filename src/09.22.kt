fun calculateRabbits(months: Int, ratio: Int): Long {
    if (months == 1 || months == 2) return 1
    var adults = 1L
    var babies = 0L
    for (i in 3..months) {
        val newBabies = adults * ratio
        val newAdults = babies + adults
        adults = newAdults
        babies = newBabies
    }
    return adults + babies
}

fun main() {
    println("Enter the number of months (otherwise it will be 32): ")
    val months = readLine()?.toIntOrNull() ?: 32
    println("Enter the ratio (otherwise it will be 5): ")
    val ratio = readLine()?.toIntOrNull() ?: 5
    val totalRabbits = calculateRabbits(months, ratio)
    println("Total number of rabbits after $months months: $totalRabbits")
}