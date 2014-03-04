package com.philiprehberger.money

/** Thrown when arithmetic is attempted between different currencies. */
public class CurrencyMismatchException(left: String, right: String) :
    RuntimeException("Cannot combine $left and $right")
