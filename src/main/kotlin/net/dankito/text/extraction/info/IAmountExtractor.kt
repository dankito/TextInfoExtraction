package net.dankito.text.extraction.info

import net.dankito.text.extraction.info.model.AmountOfMoney


interface IAmountExtractor {

    fun extractAmountsOfMoney(text: String): List<AmountOfMoney>

    fun extractAmountsOfMoney(lines: List<String>): List<AmountOfMoney>


    fun extractPercentages(text: String): List<AmountOfMoney>

    fun extractPercentages(lines: List<String>): List<AmountOfMoney>

}