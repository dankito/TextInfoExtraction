package net.dankito.text.extraction.info.bank

import net.dankito.text.extraction.info.model.StringSearchResult


interface IPotentialBicFinder {

    fun findPotentialBic(searchResults: List<StringSearchResult>): String?

}