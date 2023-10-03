fun countVowels(s: String): Int {
    return s.count { char -> "aAeEyYuUiIoO".contains(char) }
}

fun main() {
    val s = "mtnhsugfer"
    println("Количество гласных: ${countVowels(s)}")
}
