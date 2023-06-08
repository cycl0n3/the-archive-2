#include "Rational.h"

#include <iostream>
#include <string>

Rational::Rational()
{
    std::cout << "Rational::Rational()" << std::endl;
    this->numerator = 0;
    this->denominator = 1;
}

Rational::Rational(int numerator, int denominator)
{
    std::cout << "Rational::Rational(int numerator, int denominator)" << std::endl;

    int gcd = 1;
    
    // calculate gcd using euclidean algorithm
    int a = std::abs(numerator);
    int b = std::abs(denominator);
    
    while (b != 0)
    {
        int temp = b;
        b = a % b;
        a = temp;
    }
    
    gcd = a;

    this->numerator = numerator / gcd;
    this->denominator = denominator / gcd;
}

Rational::Rational(const Rational& other)
{
    std::cout << "Rational::Rational(const Rational& other)" << std::endl;
    this->numerator = other.numerator;
    this->denominator = other.denominator;
}

Rational& Rational::operator=(const Rational& other)
{
    std::cout << "Rational& Rational::operator=(const Rational& other)" << std::endl;
    if (this != &other)
    {
        this->numerator = other.numerator;
        this->denominator = other.denominator;
    }
    return *this;
}

Rational::~Rational()
{
    std::cout << "Rational::~Rational()" << std::endl;
    this->numerator = 0;
    this->denominator = 1;
}

int Rational::getNumerator() const
{
    return this->numerator;
}

int Rational::getDenominator() const
{
    return this->denominator;
}

Rational Rational::operator+(const Rational& other) const
{
    std::cout << "Rational Rational::operator+(const Rational& other) const" << std::endl;

    Rational result;
    result.numerator = this->numerator * other.denominator + other.numerator * this->denominator;
    result.denominator = this->denominator * other.denominator;
    return result;
}

Rational Rational::operator-(const Rational& other) const
{
    std::cout << "Rational Rational::operator-(const Rational& other) const" << std::endl;

    Rational result;
    result.numerator = this->numerator * other.denominator - other.numerator * this->denominator;
    result.denominator = this->denominator * other.denominator;
    return result;
}

Rational Rational::operator*(const Rational& other) const
{
    std::cout << "Rational Rational::operator*(const Rational& other) const" << std::endl;

    Rational result;
    result.numerator = this->numerator * other.numerator;
    result.denominator = this->denominator * other.denominator;
    return result;
}

Rational Rational::operator/(const Rational& other) const
{
    std::cout << "Rational Rational::operator/(const Rational& other) const" << std::endl;

    Rational result;
    result.numerator = this->numerator * other.denominator;
    result.denominator = this->denominator * other.numerator;
    return result;
}

std::string Rational::toString() const
{
    return std::to_string(this->numerator) + "/" + std::to_string(this->denominator);
}

void Rational::example()
{
    Rational r1(1, 2);
    Rational r2(1, 3);
    
    Rational r3 = r1 + r2;
    Rational r4 = r1 - r2;
    Rational r5 = r1 * r2;
    Rational r6 = r1 / r2;

    std::cout << r1.toString() << " + " << r2.toString() << " = " << r3.toString() << std::endl;
    std::cout << r1.toString() << " - " << r2.toString() << " = " << r4.toString() << std::endl;
    std::cout << r1.toString() << " * " << r2.toString() << " = " << r5.toString() << std::endl;
    std::cout << r1.toString() << " / " << r2.toString() << " = " << r6.toString() << std::endl;
}