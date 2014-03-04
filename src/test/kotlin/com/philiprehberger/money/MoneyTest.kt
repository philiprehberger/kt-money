package com.philiprehberger.money

import kotlin.test.*

class MoneyTest {
    @Test fun `creation`() = assertEquals("USD 19.99", Money.of(19.99, "USD").toString())
    @Test fun `addition`() = assertEquals(Money.of(30, "USD"), Money.of(10, "USD") + Money.of(20, "USD"))
    @Test fun `subtraction`() = assertEquals(Money.of(5, "USD"), Money.of(15, "USD") - Money.of(10, "USD"))
    @Test fun `multiplication`() = assertEquals(Money.of(20, "USD"), Money.of(10, "USD") * 2)
    @Test fun `division`() = assertEquals(Money.of(5, "USD"), Money.of(10, "USD") / 2)
    @Test fun `currency mismatch`() { assertFailsWith<CurrencyMismatchException> { Money.of(10, "USD") + Money.of(10, "EUR") } }
    @Test fun `isZero`() { assertTrue(Money.of(0, "USD").isZero()); assertFalse(Money.of(1, "USD").isZero()) }
    @Test fun `isPositive isNegative`() { assertTrue(Money.of(1, "USD").isPositive()); assertTrue(Money.of(-1, "USD").isNegative()) }
    @Test fun `comparison`() { assertTrue(Money.of(10, "USD") > Money.of(5, "USD")) }
    @Test fun `no floating point error`() = assertEquals(Money.of(0.30, "USD"), Money.of(0.1, "USD") + Money.of(0.2, "USD"))
}
