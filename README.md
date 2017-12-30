# money

[![Tests](https://github.com/philiprehberger/kt-money/actions/workflows/publish.yml/badge.svg)](https://github.com/philiprehberger/kt-money/actions/workflows/publish.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/money)](https://central.sonatype.com/artifact/com.philiprehberger/money)
[![License](https://img.shields.io/github/license/philiprehberger/kt-money)](LICENSE)
[![Sponsor](https://img.shields.io/badge/sponsor-GitHub%20Sponsors-ec6cb9)](https://github.com/sponsors/philiprehberger)

Type-safe monetary values with currency support and precise arithmetic.

## Installation

### Gradle (Kotlin DSL)

```kotlin
implementation("com.philiprehberger:money:0.1.3")
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>money</artifactId>
    <version>0.1.3</version>
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

total.format() // "$21.59"
```

## API

| Function / Class | Description |
|------------------|-------------|
| `Money.of(amount, currencyCode)` | Create a monetary value |
| `Money + Money`, `Money - Money` | Arithmetic (same currency only) |
| `Money * factor`, `Money / factor` | Scalar multiplication/division |
| `Money.allocate(n)` | Split fairly without losing cents |
| `Money.format(locale)` | Locale-aware currency formatting |
| `Money.isZero()`, `isPositive()`, `isNegative()` | Value checks |
| `Money.convertTo(code, rate)` | Currency conversion |
| `Currency.of(code)` | Look up ISO 4217 currency |

## Development

```bash
./gradlew test
./gradlew build
```

## License

MIT
