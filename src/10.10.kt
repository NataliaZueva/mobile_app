fun smoothData(sequence: List<Double>): List<Double> {
    val smoothedSequence = mutableListOf<Double>()
    smoothedSequence.add(sequence[0]) // Add the first value as it is
    for (i in 1 until sequence.size - 1) {
        val average = (sequence[i - 1] + sequence[i] + sequence[i + 1]) / 3
        smoothedSequence.add(average)
    }
    smoothedSequence.add(sequence.last())
    return smoothedSequence
}

fun main() {
    val input = "7\n32.6 31.2 35.2 37.4 44.9 42.1 44.1"
    val sequence = input.lines()[1].split(" ").map { it.toDouble() }
    val smoothedSequence = smoothData(sequence)
    println(smoothedSequence.joinToString(" "))
}