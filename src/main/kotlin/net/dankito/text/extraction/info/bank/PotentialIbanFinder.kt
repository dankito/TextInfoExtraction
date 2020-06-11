package net.dankito.text.extraction.info.bank

import net.dankito.text.extraction.info.model.StringSearchResult


open class PotentialIbanFinder : IPotentialIbanFinder {

    companion object {
        /**
         * Labelled with string "IBAN" with optionally a ':' and up to white spaces before IBAN.
         */
        val IbanAfterIbanStringPattern = Regex("\\bIBAN:? {0,2}[A-Z]{2}\\d{2}\\s?([A-Z0-9]{4}\\s?){3}[A-Z0-9\\s?]{1,18}\\b")
    }


    override fun findPotentialIban(searchResults: List<StringSearchResult>): String? {
        // it's more likely that it's invoice's IBAN if it's labelled with 'IBAN(: )'
        return (searchResults.firstOrNull { IbanAfterIbanStringPattern.containsMatchIn(it.foundInLine) }
                ?: searchResults.firstOrNull()
                )?.hit
    }

}