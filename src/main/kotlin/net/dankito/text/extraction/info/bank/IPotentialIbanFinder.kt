package net.dankito.text.extraction.info.bank

import net.dankito.text.extraction.info.model.StringSearchResult


interface IPotentialIbanFinder {

    fun findPotentialIban(searchResults: List<StringSearchResult>): String?

}