package com.philiprehberger.money

/** Represents an ISO 4217 currency. */
public data class Currency(
    public val code: String,
    public val symbol: String,
    public val minorUnits: Int,
    public val displayName: String,
) {
    public companion object {
        private val currencies = mapOf(
            "USD" to Currency("USD", "$", 2, "US Dollar"),
            "EUR" to Currency("EUR", "\u20ac", 2, "Euro"),
            "GBP" to Currency("GBP", "\u00a3", 2, "British Pound"),
            "JPY" to Currency("JPY", "\u00a5", 0, "Japanese Yen"),
            "CHF" to Currency("CHF", "CHF", 2, "Swiss Franc"),
            "CAD" to Currency("CAD", "C$", 2, "Canadian Dollar"),
            "AUD" to Currency("AUD", "A$", 2, "Australian Dollar"),
            "CNY" to Currency("CNY", "\u00a5", 2, "Chinese Yuan"),
            "SEK" to Currency("SEK", "kr", 2, "Swedish Krona"),
            "NOK" to Currency("NOK", "kr", 2, "Norwegian Krone"),
            "DKK" to Currency("DKK", "kr", 2, "Danish Krone"),
            "PLN" to Currency("PLN", "z\u0142", 2, "Polish Zloty"),
            "INR" to Currency("INR", "\u20b9", 2, "Indian Rupee"),
            "BRL" to Currency("BRL", "R$", 2, "Brazilian Real"),
            "KRW" to Currency("KRW", "\u20a9", 0, "South Korean Won"),
            "SGD" to Currency("SGD", "S$", 2, "Singapore Dollar"),
            "HKD" to Currency("HKD", "HK$", 2, "Hong Kong Dollar"),
            "MXN" to Currency("MXN", "MX$", 2, "Mexican Peso"),
            "CZK" to Currency("CZK", "K\u010d", 2, "Czech Koruna"),
            "HUF" to Currency("HUF", "Ft", 2, "Hungarian Forint"),
        )
        /** Look up a currency by ISO code. */
        public fun of(code: String): Currency = currencies[code.uppercase()] ?: throw IllegalArgumentException("Unknown currency: $code")
    }
}
