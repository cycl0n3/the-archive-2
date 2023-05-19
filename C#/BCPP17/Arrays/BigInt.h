#pragma once

#include<string>

class BigInt
{
public:
    BigInt();
    BigInt(std::string value);
    BigInt(int value);
    ~BigInt();
    
    BigInt operator+(BigInt& other);
    BigInt operator-(BigInt& other);
    BigInt operator*(BigInt& other);
    BigInt operator/(BigInt& other);
    BigInt operator%(BigInt& other);

    BigInt operator-();
    
    bool operator==(BigInt& other);
    bool operator!=(BigInt& other);
    bool operator<(BigInt& other);
    bool operator>(BigInt& other);
    bool operator<=(BigInt& other);
    bool operator>=(BigInt& other);
    
    std::string ToString();
private:
    std::string value;
};
