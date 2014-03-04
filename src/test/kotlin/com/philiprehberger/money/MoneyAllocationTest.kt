package com.philiprehberger.money

import kotlin.test.*
import java.math.BigDecimal

class MoneyAllocationTest {
    @Test fun `allocate evenly`() {
        val parts = Money.of(100, "USD").allocate(4)
        assertEquals(4, parts.size)
        assertEquals(Money.of(100, "USD"), parts.reduce { a, b -> a + b })
    }
    @Test fun `allocate with remainder`() {
        val parts = Money.of(100, "USD").allocate(3)
        assertEquals(3, parts.size)
        assertEquals(Money.of(100, "USD"), parts.reduce { a, b -> a + b })
    }
}
