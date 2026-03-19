package com.philiprehberger.money

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale

/** Immutable monetary value with precise arithmetic. */
public class Money private constructor(
    public val amount: BigDecimal,
    public val currency: Currency,
) : Comparable<Money> {

    public companion object {
        /** Create a Money value. */
        public fun of(amount: Number, currencyCode: String): Money {
            val cur = Currency.of(currencyCode)
            return Money(BigDecimal(amount.toString()).setScale(cur.minorUnits, RoundingMode.HALF_EVEN), cur)
        }
    }

    private fun requireSameCurrency(other: Money) {
        if (currency.code != other.currency.code) throw CurrencyMismatchException(currency.code, other.currency.code)
    }

    public operator fun plus(other: Money): Money { requireSameCurrency(other); return Money(amount.add(other.amount), currency) }
    public operator fun minus(other: Money): Money { requireSameCurrency(other); return Money(amount.subtract(other.amount), currency) }
    public operator fun times(factor: Number): Money = Money(amount.multiply(BigDecimal(factor.toString())).setScale(currency.minorUnits, RoundingMode.HALF_EVEN), currency)
    public operator fun div(factor: Number): Money = Money(amount.divide(BigDecimal(factor.toString()), currency.minorUnits, RoundingMode.HALF_EVEN), currency)
    public operator fun unaryMinus(): Money = Money(amount.negate(), currency)

    public fun abs(): Money = Money(amount.abs(), currency)
    public fun negate(): Money = Money(amount.negate(), currency)
    public fun isZero(): Boolean = amount.compareTo(BigDecimal.ZERO) == 0
    public fun isPositive(): Boolean = amount > BigDecimal.ZERO
    public fun isNegative(): Boolean = amount < BigDecimal.ZERO

    public fun roundTo(decimalPlaces: Int): Money = Money(amount.setScale(decimalPlaces, RoundingMode.HALF_EVEN), currency)

    /** Split into [n] parts, distributing remainder from the first share. */
    public fun allocate(n: Int): List<Money> {
        require(n > 0) { "n must be > 0" }
        val base = amount.divide(BigDecimal(n), currency.minorUnits, RoundingMode.DOWN)
        val remainder = amount.subtract(base.multiply(BigDecimal(n)))
        val unit = BigDecimal.ONE.movePointLeft(currency.minorUnits)
        val results = mutableListOf<Money>()
        var rem = remainder
        for (i in 0 until n) {
            if (rem > BigDecimal.ZERO) { results.add(Money(base.add(unit), currency)); rem = rem.subtract(unit) }
            else results.add(Money(base, currency))
        }
        return results
    }

    public fun format(locale: Locale = Locale.US): String {
        val fmt = NumberFormat.getCurrencyInstance(locale)
        fmt.currency = java.util.Currency.getInstance(currency.code)
        return fmt.format(amount)
    }

    public fun convertTo(currencyCode: String, rate: BigDecimal): Money {
        val cur = Currency.of(currencyCode)
        return Money(amount.multiply(rate).setScale(cur.minorUnits, RoundingMode.HALF_EVEN), cur)
    }

    override fun compareTo(other: Money): Int { requireSameCurrency(other); return amount.compareTo(other.amount) }
    override fun equals(other: Any?): Boolean = other is Money && currency.code == other.currency.code && amount.compareTo(other.amount) == 0
    override fun hashCode(): Int = 31 * amount.stripTrailingZeros().hashCode() + currency.code.hashCode()
    override fun toString(): String = "${currency.code} $amount"
}
