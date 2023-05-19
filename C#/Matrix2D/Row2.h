#pragma once

#include <iostream>

class Row2
{
public:
    Row2();
    Row2(float a, float b);
    Row2(const Row2& other);
    ~Row2();

    float& operator[](int index);
    const float& operator[](int index) const;
    
    Row2& operator=(const Row2& other);
    Row2& operator+=(const Row2& other);
    Row2& operator-=(const Row2& other);
    Row2 operator+(const Row2& other) const;
    Row2 operator-(const Row2& other) const;

    Row2 operator*(float scalar) const;
    
    bool operator==(const Row2& other) const;
    bool operator!=(const Row2& other) const;
    
    friend std::ostream& operator<<(std::ostream& os, const Row2& row);
private:
    float a;
    float b;
};
