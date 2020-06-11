package net.dankito.text.extraction.info.bank

import net.dankito.text.extraction.info.model.StringSearchResult


open class PotentialBicFinder(protected val ibanExtractor: IIbanExtractor = IbanExtractor()) : IPotentialBicFinder {

    companion object {
        /**
         * Labelled with string "BIC" with optionally a ':' and up to white spaces before BIC.
         */
        val BicAfterBicStringPattern = Regex("\\bBIC:? {0,2}" + BicExtractor.BicPatternString)
    }


    override fun findPotentialBic(searchResults: List<StringSearchResult>): String? {
        // it's more likely that it's invoice's BIC if it's labelled with 'BIC(: )'
        val bicsWithBicLabel = searchResults.filter { BicAfterBicStringPattern.containsMatchIn(it.foundInLine) }

        if (bicsWithBicLabel.size == 1) {
            return bicsWithBicLabel.first().hit
        }
        else if (bicsWithBicLabel.isNotEmpty()) {
            // it's more likely that it's invoice's BIC if there's also an IBAN on the same line
            val alsoContainingValidIban = bicsWithBicLabel.filter { ibanExtractor.extractIbans(it.foundInLine).isNotEmpty() }

            if (alsoContainingValidIban.isNotEmpty()) {
                return alsoContainingValidIban.first().hit
            }
        }

        return searchResults.firstOrNull()?.hit
    }

}