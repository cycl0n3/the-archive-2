#pragma once

#include<string>

class AdditionModulo
{
public:
    AdditionModulo();
    AdditionModulo(int modulo);
    AdditionModulo(int value, int modulo);
    AdditionModulo(const AdditionModulo& other);
    ~AdditionModulo();

    AdditionModulo& operator=(const AdditionModulo& other);
    AdditionModulo operator+(const AdditionModulo& other) const;
    AdditionModulo operator-(const AdditionModulo& other) const;
    
    AdditionModulo operator+(int scalar) const;
    AdditionModulo operator-(int scalar) const;
    
    int getValue() const;
    int getModulo() const;
    
    AdditionModulo inverse() const;
    AdditionModulo identity() const;
    AdditionModulo operator-() const;
    
    bool operator==(const AdditionModulo& other) const;
    bool operator!=(const AdditionModulo& other) const;
    
    std::string toString() const;
    static void example();

    // Add operator<<
    friend std::ostream& operator<<(std::ostream& os, const AdditionModulo& additionModulo);
private:
    int value;
    int modulo;
};
