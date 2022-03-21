package net.dankito.text.extraction.info.extensions


fun String.countOccurrences(charToFind: Char): Int {
    var countOccurrences = 0

    for (char in this) {
        if (char == charToFind) {
            countOccurrences++
        }
    }

    return countOccurrences
}