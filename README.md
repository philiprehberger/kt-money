# money

[![Tests](https://github.com/philiprehberger/kt-money/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-money/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/money.svg)](https://central.sonatype.com/artifact/com.philiprehberger/money)
[![Last updated](https://img.shields.io/github/last-commit/philiprehberger/kt-money)](https://github.com/philiprehberger/kt-money/commits/main)

Type-safe monetary values with currency support and precise arithmetic.

## Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("com.philiprehberger:money:0.2.0")
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>money</artifactId>
    <version>0.2.0</version>
</dependency>
```

## Usage

```kotlin
import com.philiprehberger.money.*

val price = Money.of(19.99, "USD")
val tax = Money.of(1.60, "USD")
val total = price + tax // USD 21.59

// No floating point errors
Money.of(0.1, "USD") + Money.of(0.2, "USD") // USD 0.30

// Fair allocation
Money.of(100, "USD").allocate(3) // [33.34, 33.33, 33.33]

// Percentage and discount
Money.of(200, "USD").percentage(BigDecimal("15")) // USD 30.00
Money.of(200, "USD").discount(BigDecimal("15"))   // USD 170.00

total.format() // "$21.59"
```

## API

| Function / Class | Description |
|------------------|-------------|
| `Money.of(amount, currencyCode)` | Create a monetary value |
| `Money + Money`, `Money - Money` | Arithmetic (same currency only) |
| `Money * factor`, `Money / factor` | Scalar multiplication/division |
| `Money.allocate(n)` | Split fairly without losing cents |
| `Money.percentage(percent)` | Calculate percentage of amount |
| `Money.discount(percent)` | Subtract percentage from amount |
| `Money.format(locale)` | Locale-aware currency formatting |
| `Money.isZero()`, `isPositive()`, `isNegative()` | Value checks |
| `Money.convertTo(code, rate)` | Currency conversion |
| `Currency.of(code)` | Look up ISO 4217 currency |

## Development

```bash
./gradlew test
./gradlew build
```

## Support

If you find this project useful:

⭐ [Star the repo](https://github.com/philiprehberger/kt-money)

🐛 [Report issues](https://github.com/philiprehberger/kt-money/issues?q=is%3Aissue+is%3Aopen+label%3Abug)

💡 [Suggest features](https://github.com/philiprehberger/kt-money/issues?q=is%3Aissue+is%3Aopen+label%3Aenhancement)

❤️ [Sponsor development](https://github.com/sponsors/philiprehberger)

🌐 [All Open Source Projects](https://philiprehberger.com/open-source-packages)

💻 [GitHub Profile](https://github.com/philiprehberger)

🔗 [LinkedIn Profile](https://www.linkedin.com/in/philiprehberger)

## License

[MIT](LICENSE)
