#include "AdditionModulo.h"

#include <iostream>
#include <sstream>
#include <string>

AdditionModulo::AdditionModulo()
    : value(0), modulo(1)
{
}

AdditionModulo::AdditionModulo(int modulo)
    : value(0), modulo(modulo)
{
}

AdditionModulo::AdditionModulo(int value, int modulo)
    : value(value), modulo(modulo)
{
    if (modulo <= 0)
    {
        std::cout << "Modulo must be positive. Setting modulo to 1." << std::endl;
        this->modulo = 1;
    }
    
    int q = std::abs(this->value) / this->modulo;

    if (this->value < 0)
    {
        this->value += (q + 1) * this->modulo;
    }
    else
    {
        this->value %= this->modulo;
    }
}

AdditionModulo::AdditionModulo(const AdditionModulo& other)
    : value(other.value), modulo(other.modulo)
{
}

AdditionModulo::~AdditionModulo()
{
}

AdditionModulo& AdditionModulo::operator=(const AdditionModulo& other)
{
    if (this != &other)
    {
        this->value = other.value;
        this->modulo = other.modulo;
    }
    return *this;
}

AdditionModulo AdditionModulo::operator+(const AdditionModulo& other) const
{
    if (this->modulo != other.modulo)
    {
        std::cout << "Modulos must be equal. Returning default value." << std::endl;
        return AdditionModulo(this->modulo);
    }
    return AdditionModulo(this->value + other.value, this->modulo);
}

AdditionModulo AdditionModulo::operator-(const AdditionModulo& other) const
{
    if (this->modulo != other.modulo)
    {
        std::cout << "Modulos must be equal. Returning default value." << std::endl;
        return AdditionModulo(this->modulo);
    }
    return AdditionModulo(this->value - other.value, this->modulo);
}

AdditionModulo AdditionModulo::operator+(int scalar) const
{
    return AdditionModulo(this->value + scalar, this->modulo);
}

AdditionModulo AdditionModulo::operator-(int scalar) const
{
    return AdditionModulo(this->value - scalar, this->modulo);
}

int AdditionModulo::getValue() const
{
    return this->value;
}

int AdditionModulo::getModulo() const
{
    return this->modulo;
}

AdditionModulo AdditionModulo::inverse() const
{
    if (this->value == 0)
    {
        std::cout << "Cannot invert zero. Returning default value." << std::endl;
        return AdditionModulo(this->modulo);
    }
    
    return AdditionModulo(-this->value, this->modulo);
}

AdditionModulo AdditionModulo::identity() const
{
    return AdditionModulo(0, this->modulo);
}

AdditionModulo AdditionModulo::operator-() const
{
    return AdditionModulo(-this->value, this->modulo);
}

bool AdditionModulo::operator==(const AdditionModulo& other) const
{
    return this->value == other.value && this->modulo == other.modulo;
}

bool AdditionModulo::operator!=(const AdditionModulo& other) const
{
    return !(*this == other);
}

std::string AdditionModulo::toString() const
{
    std::stringstream ss;
    ss << this->value << " (mod " << this->modulo << ")";
    return ss.str();
}

std::ostream& operator<<(std::ostream& os, const AdditionModulo& additionModulo)
{
    os << additionModulo.toString();
    return os;
}

void AdditionModulo::example()
{
    std::cout << "GroupAdditionModulo::example()" << std::endl;
    
    AdditionModulo a(3, 5);
    AdditionModulo b(4, 5);
    AdditionModulo c(2, 5);
    
    std::cout << "a = " << a << std::endl;
    std::cout << "b = " << b << std::endl;
    std::cout << "c = " << c << std::endl;
    std::cout << "a + b = " << (a + b) << std::endl;
    std::cout << "a - b = " << (a - b) << std::endl;
    std::cout << "a.inverse() = " << a.inverse() << std::endl;
    std::cout << "a.identity() = " << a.identity() << std::endl;
    std::cout << "-a = " << (-a) << std::endl;
    std::cout << "a == b = " << (a == b) << std::endl;
    std::cout << "a != b = " << (a != b) << std::endl;
    std::cout << "a == c = " << (a == c) << std::endl;
    std::cout << "a != c = " << (a != c) << std::endl;

    // asterix 50 times
    std::cout << std::string(50, '*') << std::endl;

    AdditionModulo d(3, 13);
    AdditionModulo e(4, 13);

    std::cout << "d = " << d << std::endl;
    std::cout << "e = " << e << std::endl;

    std::cout << "-d = " << -d << std::endl;
    std::cout << "-e = " << -e << std::endl;

    std::cout << "d.inverse() = " << d.inverse() << std::endl;
    std::cout << "e.inverse() = " << e.inverse() << std::endl;

    std::cout << std::endl;
}