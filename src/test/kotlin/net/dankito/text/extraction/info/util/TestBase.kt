package net.dankito.text.extraction.info.util

import java.math.BigDecimal


abstract class TestBase {

    protected fun decimal(value: Double): BigDecimal {
        return BigDecimal.valueOf(value)
    }

    protected fun decimal(value: Long): BigDecimal {
        return BigDecimal.valueOf(value)
    }

}