package net.dankito.text.extraction.info.bank

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class IbanExtractorTest {

    private val underTest = IbanExtractor()


    @Test
    fun `IBAN with minimal count places`() {

        // when
        val result = underTest.extractIbans("DE0012345678901234")

        // then
        assertThat(result).hasSize(1)
        assertThat(result.map { it.hit }).containsExactly("DE0012345678901234")
    }

    @Test
    fun `IBAN with maximal count places`() {

        // when
        val result = underTest.extractIbans("DE00123456789012345678901234567890")

        // then
        assertThat(result).hasSize(1)
        assertThat(result.map { it.hit }).containsExactly("DE00123456789012345678901234567890")
    }


    @Test
    fun `Not a IBAN - less than 14 characters`() {

        // when
        val result = underTest.extractIbans("DE00123456789")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - more than 34 characters`() {

        // when
        val result = underTest.extractIbans("DE001234567890123456789012345678901")

        // then
        assertThat(result).isEmpty()
    }


    @Test
    fun `Not a IBAN - lower case letter in 1st place`() {

        // when
        val result = underTest.extractIbans("dE12345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - digit in 1st place`() {

        // when
        val result = underTest.extractIbans("1E12345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - lower case letter in 2nd place`() {

        // when
        val result = underTest.extractIbans("De12345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - digit in 2nd place`() {

        // when
        val result = underTest.extractIbans("D112345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - upper case letter in 3rd place`() {

        // when
        val result = underTest.extractIbans("DEA2345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - lower case letter in 3rd place`() {

        // when
        val result = underTest.extractIbans("DEa2345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - upper case letter in 4th place`() {

        // when
        val result = underTest.extractIbans("DE1A345678901234")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Not a IBAN - lower case letter in 4th place`() {

        // when
        val result = underTest.extractIbans("DE1a345678901234")

        // then
        assertThat(result).isEmpty()
    }



    @Test
    fun `Not a IBAN - German IBAN with letter after 4th place`() {

        // when
        val result = underTest.extractIbans("DE97ZZZ00000169525")

        // then
        assertThat(result).isEmpty()
    }

    @Test
    fun `Feasible German IBAN with spaces`() {

        // when
        val result = underTest.extractIbans("IBAN: DE22 2501 0030 0123 4567 89")

        // then
        assertThat(result).hasSize(1)
        assertThat(result.map { it.hit }).containsExactly("DE22 2501 0030 0123 4567 89")
    }

}