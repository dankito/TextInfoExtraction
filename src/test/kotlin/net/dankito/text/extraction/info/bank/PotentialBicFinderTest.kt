package net.dankito.text.extraction.info.bank

import net.dankito.text.extraction.info.model.StringSearchResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class PotentialBicFinderTest {

    private val underTest = PotentialBicFinder()


    @Test
    fun `Line also contains valid IBAN`() {

        // given
        val searchResults = listOf(
            StringSearchResult("ABCDEFGHXXX", "Der Rechnungsbetrag wird am 12.11.2019 per SEPA-Lastschrift vom Konto IBAN DE11**************5557 (BIC ABCDEFGHXXX) abgebucht."),
            StringSearchResult("SALADE51WND", "Kreissparkasse St. Wendel BIC: SALADE51WND IBAN: DE15 5925 1020 0120 1151 26")
        )


        // when
        val result = underTest.findPotentialBic(searchResults)


        // then
        assertThat(result).isEqualTo("SALADE51WND")
    }

    @Test
    fun `Not too many white spaces between BIC string and BIC`() {

        // given
        val searchResults = listOf(
            StringSearchResult("ABCDEFGHXXX", "BIC:      ABCDEFGHXXX"),
            StringSearchResult("PBNKDEFFXXX", "BIC  PBNKDEFFXXX")
        )


        // when
        val result = underTest.findPotentialBic(searchResults)


        // then
        assertThat(result).isEqualTo("PBNKDEFFXXX")
    }

}