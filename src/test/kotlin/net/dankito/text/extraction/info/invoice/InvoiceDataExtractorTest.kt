package net.dankito.text.extraction.info.invoice

import net.dankito.text.extraction.info.util.TestBase
import net.dankito.text.extraction.info.util.TestInvoices
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class InvoiceDataExtractorTest : TestBase() {

    private val underTest = InvoiceDataExtractor()


    @Test
    fun extractInvoiceData_GermanWebHostingInvoice() {

        // when
        val result = underTest.extractInvoiceData(TestInvoices.GermanWebHostingInvoice)


        // then
        assertThat(result.error).isNull()

        assertThat(result.allAmounts.map { it.amount }).containsOnly(decimal(2.1), decimal(12.61), decimal(2.39), decimal(15))

        assertThat(result.percentages.map { it.amount }).containsOnly(decimal(19.0))

        assertThat(result.dates.map { it.dateString }).containsOnly("02.05.2019", "13.05.2019")

        assertThat(result.ibans).isEmpty()

        assertThat(result.bics).isEmpty()

        assertThat(result.potentialTotalAmount?.amount).isEqualTo(decimal(15))
        assertThat(result.potentialTotalAmount?.currency).isEqualTo("€")

        assertThat(result.potentialNetAmount?.amount).isEqualTo(decimal(12.61))
        assertThat(result.potentialNetAmount?.currency).isEqualTo("€")

        assertThat(result.potentialValueAddedTax?.amount).isEqualTo(decimal(2.39))
        assertThat(result.potentialValueAddedTax?.currency).isEqualTo("€")

        assertThat(result.potentialValueAddedTaxRate?.amount).isEqualTo(decimal(19.0))
        assertThat(result.potentialValueAddedTaxRate?.currency).isEqualTo("%")
    }

    @Test
    fun extractInvoiceData_GermanMobilePhoneInvoice_CurrencySymbolEUR() {

        // when
        val result = underTest.extractInvoiceData(TestInvoices.GermanMobilePhoneInvoice)


        // then
        assertThat(result.error).isNull()

        assertThat(result.allAmounts.map { it.amount }).containsOnly(decimal(6.99), decimal(1.12), decimal(5.87))

        assertThat(result.percentages.map { it.amount }).containsOnly(decimal(19.0))

        assertThat(result.dates.map { it.dateString }).containsOnly("20.03.2019", "26.03.2019", "20.12.2018", "19.12.2019", "19.09.2019", "20.04.2019", "21.03.2019")

        assertThat(result.ibans).isEmpty()

        assertThat(result.bics).isEmpty()

        assertThat(result.potentialTotalAmount?.amount).isEqualTo(decimal(6.99))
        assertThat(result.potentialTotalAmount?.currency).isEqualTo("EUR")

        assertThat(result.potentialNetAmount?.amount).isEqualTo(decimal(5.87))
        assertThat(result.potentialNetAmount?.currency).isEqualTo("EUR")

        assertThat(result.potentialValueAddedTax?.amount).isEqualTo(decimal(1.12))
        assertThat(result.potentialValueAddedTax?.currency).isEqualTo("EUR")

        assertThat(result.potentialValueAddedTaxRate?.amount).isEqualTo(decimal(19.0))
        assertThat(result.potentialValueAddedTaxRate?.currency).isEqualTo("%")
    }

    @Test
    fun extractInvoiceData_GermanBackendDevelopmentInvoice() {

        // given
        val invoiceText = readFileFromResource("Invoice Backend Development German.txt")

        // when
        val result = underTest.extractInvoiceData(invoiceText)


        // then
        assertThat(result.error).isNull()

        assertThat(result.allAmounts.map { it.amount }).containsOnly(decimal(80), decimal(10903.2), decimal(2071), decimal(12974.2))

        assertThat(result.percentages.map { it.amount }).containsOnly(decimal(19.0))

        assertThat(result.dates.map { it.dateString }).containsOnly("31.01.2020")

        assertThat(result.ibans.map { it.hit }).containsOnly("DE11876543211234567890")

        assertThat(result.bics.map { it.hit }).containsOnly("ABCDDEBBXXX")

        assertThat(result.potentialTotalAmount?.amount).isEqualTo(decimal(12974.2))
        assertThat(result.potentialTotalAmount?.currency).isEqualTo("€")

        assertThat(result.potentialNetAmount?.amount).isEqualTo(decimal(10903.2))
        assertThat(result.potentialNetAmount?.currency).isEqualTo("€")

        assertThat(result.potentialValueAddedTax?.amount).isEqualTo(decimal(2071))
        assertThat(result.potentialValueAddedTax?.currency).isEqualTo("€")

        assertThat(result.potentialValueAddedTaxRate?.amount).isEqualTo(decimal(19.0))
        assertThat(result.potentialValueAddedTaxRate?.currency).isEqualTo("%")
    }


    private fun readFileFromResource(filename: String): String {
        val inputStream = InvoiceDataExtractorTest::class.java.classLoader.getResourceAsStream("test_data/" + filename)

        if (inputStream == null) {
            Assertions.fail<Any>("Could not find file test_data/$filename in resources folder")
        }

        return inputStream.bufferedReader().readText()
    }

}