package net.dankito.text.extraction.info.bank

import net.dankito.text.extraction.info.model.StringSearchResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PotentialIbanFinderTest {

    private val underTest = PotentialIbanFinder()


    @Test
    fun findPotentialIban() {

        // given
        val searchResults = listOf(
            StringSearchResult("DE97ZZZ00000169525", "Mandatsreferenz 201042277 und unserer Gläubiger-Identifikationsnummer DE97ZZZ00000169525  von Ihrem Konto IBAN"),
            StringSearchResult("DE22250100300540160303", "IBAN: DE22 2501 0030 0540 1603 03")
        )


        // when
        val result = underTest.findPotentialIban(searchResults)


        // then
        assertThat(result).isEqualTo("DE22250100300540160303")
    }

}