#pragma once

#include <string>

class Rational
{
public:
    Rational();
    Rational(int numerator, int denominator);
    Rational(const Rational& other);
    Rational& operator=(const Rational& other);
    ~Rational();

    int getNumerator() const;
    int getDenominator() const;

    // add two rationals
    Rational operator+(const Rational& other) const;
    // subtract two rationals
    Rational operator-(const Rational& other) const;
    // multiply two rationals
    Rational operator*(const Rational& other) const;
    // divide two rationals
    Rational operator/(const Rational& other) const;

    // convert to string
    std::string toString() const;

    // static method for examples
    static void example();
private:
    int numerator;
    int denominator;
};
