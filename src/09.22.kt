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
    val months = 32
    val ratio = 5
    val totalRabbits = calculateRabbits(months, ratio)
    println("Total number of rabbits after $months months: $totalRabbits")
}