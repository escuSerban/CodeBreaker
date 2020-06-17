data class Analyse(val rightLocation: Int, val wrongLocation: Int)

fun analyseGuess(secret: String, guess: String): Analyse {
    val rightColor = getColorInRightLocation(secret, guess)
    val wrongColor = getColorMisplaced(secret, guess)

    return Analyse(rightColor, wrongColor)
}

fun getColorMisplaced(secret: String, guess: String): Int {
    var letters = 0;
    var position = "";
    var check = ""

    for (i in secret.indices) {
        if (secret[i] != guess[i]) {
            position += secret[i]
            check += guess[i]
        }
    }

    val evaluateColor = mutableListOf<Char>()
    if (position.isNotEmpty()) {
        for (i in guess.indices) {
            val letter = guess[i]
            if (!evaluateColor.contains(letter)) {
                val secretLetters = triesCount(position, letter)
                val guessLetters = triesCount(check, letter)
                letters += if (secretLetters >= guessLetters) guessLetters else secretLetters
                evaluateColor.add(letter)
            }
        }
    }
    return letters
}

fun triesCount(position: String, letter: Char): Int {
    var count = 0
    for (i in position.indices) {
        if (position[i] == letter) count++
    }
    return count
}

fun getColorInRightLocation(secret: String, guess: String): Int {
    var locations = 0
    for (i in secret.indices) {
        if (secret[i] == guess[i]) locations++
    }
    return locations
}