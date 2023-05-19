#include "BigInt.h"

#include <algorithm>

BigInt::BigInt()
{
}

BigInt::BigInt(std::string value)
{
    this->value = value;
}

BigInt::~BigInt()
{
}

BigInt BigInt::operator+(BigInt& other)
{
    std::string result = "";
    int carry = 0;
    int i = this->value.length() - 1;
    int j = other.value.length() - 1;
    while (i >= 0 || j >= 0)
    {
        int a = i >= 0 ? this->value[i] - '0' : 0;
        int b = j >= 0 ? other.value[j] - '0' : 0;
        int sum = a + b + carry;
        carry = sum / 10;
        sum = sum % 10;
        result += std::to_string(sum);
        i--;
        j--;
    }
    if (carry > 0)
    {
        result += std::to_string(carry);
    }
    std::reverse(result.begin(), result.end());
    return BigInt(result);
}

BigInt BigInt::operator-(BigInt& other)
{
    std::string result = "";
    int carry = 0;
    int i = this->value.length() - 1;
    int j = other.value.length() - 1;
    while (i >= 0 || j >= 0)
    {
        int a = i >= 0 ? this->value[i] - '0' : 0;
        int b = j >= 0 ? other.value[j] - '0' : 0;
        int diff = a - b - carry;
        if (diff < 0)
        {
            diff += 10;
            carry = 1;
        }
        else
        {
            carry = 0;
        }
        result += std::to_string(diff);
        i--;
        j--;
    }
    std::reverse(result.begin(), result.end());
    return BigInt(result);
}

BigInt BigInt::operator*(BigInt& other)
{
    std::string result = "";
    int carry = 0;
    int i = this->value.length() - 1;
    int j = other.value.length() - 1;
    while (i >= 0 || j >= 0)
    {
        int a = i >= 0 ? this->value[i] - '0' : 0;
        int b = j >= 0 ? other.value[j] - '0' : 0;
        int prod = a * b + carry;
        carry = prod / 10;
        prod = prod % 10;
        result += std::to_string(prod);
        i--;
        j--;
    }
    if (carry > 0)
    {
        result += std::to_string(carry);
    }
    std::reverse(result.begin(), result.end());
    return BigInt(result);
}

BigInt BigInt::operator/(BigInt& other)
{
    std::string result = "";
    int i = 0;
    BigInt remainder = BigInt(this->value.substr(i, other.value.length()));
    while (i < this->value.length())
    {
        int quotient = 0;
        while (remainder >= other)
        {
            quotient++;
            remainder = remainder - other;
        }
        result += std::to_string(quotient);
        i++;
        if (i < this->value.length())
        {
            remainder = BigInt(remainder.ToString() + this->value[i]);
        }
    }
    return BigInt(result);
}

BigInt BigInt::operator%(BigInt& other)
{
    std::string result = "";
    int i = 0;
    BigInt remainder = BigInt(this->value.substr(i, other.value.length()));
    while (i < this->value.length())
    {
        int quotient = 0;
        while (remainder >= other)
        {
            quotient++;
            remainder = remainder - other;
        }
        result += std::to_string(quotient);
        i++;
        if (i < this->value.length())
        {
            remainder = BigInt(remainder.ToString() + this->value[i]);
        }
    }
    return remainder;
}

BigInt BigInt::operator-()
{
    return BigInt("-" + this->value);
}

bool BigInt::operator==(BigInt& other)
{
    return this->value == other.value;
}

bool BigInt::operator!=(BigInt& other)
{
    return this->value != other.value;
}

bool BigInt::operator<(BigInt& other)
{
    if (this->value.length() < other.value.length())
    {
        return true;
    }
    else if (this->value.length() > other.value.length())
    {
        return false;
    }
    else
    {
        for (int i = 0; i < this->value.length(); i++)
        {
            if (this->value[i] < other.value[i])
            {
                return true;
            }
            else if (this->value[i] > other.value[i])
            {
                return false;
            }
        }
        return false;
    }
}

bool BigInt::operator>(BigInt& other)
{
    if (this->value.length() > other.value.length())
    {
        return true;
    }
    else if (this->value.length() < other.value.length())
    {
        return false;
    }
    else
    {
        for (int i = 0; i < this->value.length(); i++)
        {
            if (this->value[i] > other.value[i])
            {
                return true;
            }
            else if (this->value[i] < other.value[i])
            {
                return false;
            }
        }
        return false;
    }
}

bool BigInt::operator<=(BigInt& other)
{
    if (this->value.length() < other.value.length())
    {
        return true;
    }
    else if (this->value.length() > other.value.length())
    {
        return false;
    }
    else
    {
        for (int i = 0; i < this->value.length(); i++)
        {
            if (this->value[i] < other.value[i])
            {
                return true;
            }
            else if (this->value[i] > other.value[i])
            {
                return false;
            }
        }
        return true;
    }
}

bool BigInt::operator>=(BigInt& other)
{
    if (this->value.length() > other.value.length())
    {
        return true;
    }
    else if (this->value.length() < other.value.length())
    {
        return false;
    }
    else
    {
        for (int i = 0; i < this->value.length(); i++)
        {
            if (this->value[i] > other.value[i])
            {
                return true;
            }
            else if (this->value[i] < other.value[i])
            {
                return false;
            }
        }
        return true;
    }
}

std::string BigInt::ToString()
{
    return this->value;
}
