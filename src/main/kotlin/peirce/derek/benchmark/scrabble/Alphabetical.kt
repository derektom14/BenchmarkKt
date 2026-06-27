package peirce.derek.benchmark.scrabble

import java.util.regex.Pattern

private val nonAlphabetRegex = Pattern.compile(".*[^A-Z].*")

fun isAlphabeticalRegex(word: String): Boolean {
    return !nonAlphabetRegex.matcher(word).find()
}

fun isAlphabetical(word: String): Boolean {
    return word.all { it in 'A'..'Z' }
}