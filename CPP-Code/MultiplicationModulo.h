#pragma once

#include<string>

class MultiplicationModulo
{
public:
    MultiplicationModulo();
    MultiplicationModulo(int modulo);
    MultiplicationModulo(int value, int modulo);
    MultiplicationModulo(const MultiplicationModulo& other);
    ~MultiplicationModulo();
    
    MultiplicationModulo& operator=(const MultiplicationModulo& other);
    MultiplicationModulo operator*(const MultiplicationModulo& other) const;
    MultiplicationModulo operator/(const MultiplicationModulo& other) const;
    MultiplicationModulo operator*(int scalar) const;
    MultiplicationModulo operator/(int scalar) const;
    
    int getValue() const;
    int getModulo() const;
    
    MultiplicationModulo inverse() const;
    MultiplicationModulo identity() const;
    
    bool operator==(const MultiplicationModulo& other) const;
    bool operator!=(const MultiplicationModulo& other) const;
    
    std::string toString() const;
    static void example();

    // Add operator <<
    friend std::ostream& operator<<(std::ostream& os, const MultiplicationModulo& other);
private:
    int value;
    int modulo;
};
