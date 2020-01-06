package net.dankito.text.extraction.info.model


open class TotalNetAndVatAmount(val totalAmount: AmountOfMoney,
                                val netAmount: AmountOfMoney?,
                                val valueAddedTax: AmountOfMoney?) {


    override fun toString(): String {
        return "Total: $totalAmount, net: $netAmount, vat: $valueAddedTax"
    }

}