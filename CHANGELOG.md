# Changelog

## [0.1.1] - 2026-03-18

- Upgrade to Kotlin 2.0.21 and Gradle 8.12
- Enable explicitApi() for stricter public API surface
- Add issueManagement to POM metadata

## [0.1.0] - 2026-03-18

### Added

- `Money.of()` factory with BigDecimal precision

- Arithmetic operators: +, -, *, /

- Currency mismatch prevention

- `allocate(n)` for fair splitting without losing cents

- `format()` with locale support

- 20 built-in ISO 4217 currencies
