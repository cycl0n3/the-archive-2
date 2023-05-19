#include "Row2.h"

#include <iostream>

Row2::Row2()
    : a(0.0f), b(0.0f)
{
}

Row2::Row2(float a, float b)
    : a(a), b(b)
{
}

Row2::Row2(const Row2& other)
    : a(other.a), b(other.b)
{
}

Row2::~Row2()
{
}

float& Row2::operator[](int index)
{
    if (index == 0)
    {
        return a;
    }
    else if (index == 1)
    {
        return b;
    }
    else
    {
        std::cout << "Error: index out of range\n";
        return a;
    }
}

const float& Row2::operator[](int index) const
{
    if (index == 0)
    {
        return a;
    }
    else if (index == 1)
    {
        return b;
    }
    else
    {
        std::cout << "Error: index out of range\n";
        return a;
    }
}

Row2& Row2::operator=(const Row2& other)
{
    a = other.a;
    b = other.b;
    return *this;
}

Row2& Row2::operator+=(const Row2& other)
{
    a += other.a;
    b += other.b;
    return *this;
}

Row2& Row2::operator-=(const Row2& other)
{
    a -= other.a;
    b -= other.b;
    return *this;
}

Row2 Row2::operator+(const Row2& other) const
{
    Row2 result = *this;
    result += other;
    return result;
}

Row2 Row2::operator-(const Row2& other) const
{
    Row2 result = *this;
    result -= other;
    return result;
}

Row2 Row2::operator*(float scalar) const
{
    Row2 result = *this;
    result.a *= scalar;
    result.b *= scalar;
    return result;
}

bool Row2::operator==(const Row2& other) const
{
    return a == other.a && b == other.b;
}

bool Row2::operator!=(const Row2& other) const
{
    return !(*this == other);
}

std::ostream& operator<<(std::ostream& os, const Row2& row)
{
    os << "[" << row[0] << ", " << row[1] << "]";
    return os;
}
