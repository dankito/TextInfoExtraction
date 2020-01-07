package net.dankito.text.extraction.info.invoice

import net.dankito.text.extraction.info.*
import net.dankito.text.extraction.info.model.InvoiceData


open class InvoiceDataExtractor @JvmOverloads constructor(
    protected val amountExtractor: IAmountExtractor = AmountExtractor(),
    protected val amountCategorizer: IAmountCategorizer = AmountCategorizer(),
    protected val dateExtractor: DateExtractor = DateExtractor()
) {


    open fun extractInvoiceData(text: String): InvoiceData? {
        return extractInvoiceData(text.split("\n"))
    }

    open fun extractInvoiceData(lines: List<String>): InvoiceData? {

        val percentages = amountExtractor.extractPercentages(lines)

        val potentialVatRate = amountCategorizer.findValueAddedTaxRate(percentages)

        val amounts = amountExtractor.extractAmountsOfMoney(lines)

        val dates = dateExtractor.extractDates(lines)

        amountCategorizer.findTotalNetAndVatAmount(amounts)?.let { potentialAmounts ->
            return InvoiceData(potentialAmounts.totalAmount, potentialAmounts.netAmount, potentialAmounts.valueAddedTax, potentialVatRate)
        }

        return null
    }

}