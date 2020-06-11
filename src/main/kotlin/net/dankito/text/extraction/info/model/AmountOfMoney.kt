package net.dankito.text.extraction.info.model

import java.math.BigDecimal


class AmountOfMoney(val amount: BigDecimal, val currency: String, val amountWithCurrency: String, foundInLine: String) :
    SearchResult(foundInLine) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AmountOfMoney

        if (amount.compareTo(other.amount) != 0) return false
        if (currency != other.currency) return false
        if (amountWithCurrency != other.amountWithCurrency) return false

        return true
    }

    override fun hashCode(): Int {
        var result = amount.hashCode()
        result = 31 * result + currency.hashCode()
        result = 31 * result + amountWithCurrency.hashCode()
        return result
    }



    override fun toString(): String {
        return amountWithCurrency
    }

}