package peirce.derek.benchmark.scrabble

import peirce.derek.benchmark.scrabble.count.Count
import peirce.derek.benchmark.scrabble.count.counts
import java.util.*
import kotlin.math.min

class KotlinScrabbleImperative(val scrabbleBase: ScrabbleBase) {

    private fun nBlanks(word: String): Int {
        return word.counts().entries.sumOf { entry: Map.Entry<Char, Count> ->
            (entry.value.count - scrabbleAvailableLetters[entry.key - 'A']).coerceAtLeast(0)
        }
    }

    private fun score2(word: String): Int {
        return word.counts().entries
            .sumOf { entry: Map.Entry<Char, Count> ->
                letterScores[entry.key - 'A'] *
                        min(
                            entry.value.count,
                            scrabbleAvailableLetters[entry.key - 'A']
                        )
            }
    }


    // score of the word put on the board
    private fun score3(word: String): Int {
        return (2 * (score2(word) + bonusForDoubleLetter(word))
                + if (word.length == 7) 50 else 0)
    }

    private fun bonusForDoubleLetter(word: String): Int {
        val length = word.length
        return if (length == 0) {
            0
        } else if (length > 6) {
            0.coerceAtLeast(letterScores[word[0] - 'A'])
                .coerceAtLeast(letterScores[word[1] - 'A'])
                .coerceAtLeast(letterScores[word[2] - 'A'])
                .coerceAtLeast(letterScores[word[length - 3] - 'A'])
                .coerceAtLeast(letterScores[word[length - 2] - 'A'])
                .coerceAtLeast(letterScores[word[length - 1] - 'A'])
        } else {
            word.maxOf { char ->
                letterScores[char - 'A']
            }.coerceAtLeast(0)
        }
    }

    private inline fun buildHistoOnScore(score: (String) -> Int): TreeMap<Int, MutableList<String>> {
        val histogram = TreeMap<Int, MutableList<String>>(Comparator.reverseOrder())

        scrabbleBase.availableWords.forEach { lowerCaseWord ->
            val word = lowerCaseWord.uppercase()
            if (isAlphabetical(word) && scrabbleBase.scrabbleWords.contains(word) && nBlanks(word) <= 2) {
                val score = score(word)
                histogram.getOrPut(score) { ArrayList() }.add(word)
            }
        }

        return histogram

    }


    fun run(): List<Map.Entry<Int, List<String>>> { // Function to compute the score of a given word

        // Placing the word on the board
// Building the streams of first and last letters
        // Stream to be maxed
        // Stream to be maxed
        // Stream to be maxed




        // best key / value pairs
        return buildHistoOnScore(::score3).entries.take(3)
    }

    companion object {
        private val letterScores = intArrayOf(
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
        )
        private val scrabbleAvailableLetters = intArrayOf(
            9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1
        )
    }

}

fun main() {
    println(KotlinScrabbleImperative(readScrabbleBaseFrom("/shakespeare.txt", "/scrabble.txt")).run())
}