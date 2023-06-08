#include "MultiplicationModulo.h"

#include <iostream>
#include <sstream>


MultiplicationModulo::MultiplicationModulo()
    : value(0), modulo(1)
{
}

MultiplicationModulo::MultiplicationModulo(int modulo)
    : value(0), modulo(modulo)
{
}

MultiplicationModulo::MultiplicationModulo(int value, int modulo)
    : value(value), modulo(modulo)
{
    if (modulo <= 0)
    {
        std::cout << "Modulo must be positive!" << std::endl;
        this->modulo = 1;
    }
    
    int q = std::abs(this->value) / this->modulo;

    int a = this->modulo;
    int b = this->value;

    if (b < 0)
    {
        b += (q + 1) * this->modulo;
    }
    else
    {
        b %= this->modulo;
    }

    this->value = b;
}

MultiplicationModulo::MultiplicationModulo(const MultiplicationModulo& other)
    : value(other.value), modulo(other.modulo)
{
}

MultiplicationModulo::~MultiplicationModulo()
{
}

MultiplicationModulo& MultiplicationModulo::operator=(const MultiplicationModulo& other)
{
    if (this != &other)
    {
        this->value = other.value;
        this->modulo = other.modulo;
    }
    return *this;
}

MultiplicationModulo MultiplicationModulo::operator*(const MultiplicationModulo& other) const
{
    if (this->modulo != other.modulo)
    {
        std::cout << "Modulos must be equal!" << std::endl;
        return MultiplicationModulo();
    }
    return MultiplicationModulo(this->value * other.value, this->modulo);
}

MultiplicationModulo MultiplicationModulo::operator/(const MultiplicationModulo& other) const
{
    if (this->modulo != other.modulo)
    {
        std::cout << "Modulos must be equal!" << std::endl;
        return MultiplicationModulo();
    }
    if (other.value == 0)
    {
        std::cout << "Division by zero!" << std::endl;
        return MultiplicationModulo();
    }
    return MultiplicationModulo(this->value * other.inverse().value, this->modulo);
}

MultiplicationModulo MultiplicationModulo::operator*(int scalar) const
{
    return MultiplicationModulo(this->value * scalar, this->modulo);
}

MultiplicationModulo MultiplicationModulo::operator/(int scalar) const
{
    if (scalar == 0)
    {
        std::cout << "Division by zero!" << std::endl;
        return MultiplicationModulo();
    }

    return *this / MultiplicationModulo(scalar, this->modulo);
}

int MultiplicationModulo::getValue() const
{
    return this->value;
}

int MultiplicationModulo::getModulo() const
{
    return this->modulo;
}

MultiplicationModulo MultiplicationModulo::inverse() const
{
    if (this->value == 0)
    {
        std::cout << "Division by zero!" << std::endl;
        return MultiplicationModulo();
    }

    int a = this->modulo;
    int b = this->value;

    for (int i = 1; i < this->modulo; i++)
    {
        if ((b * i) % a == 1)
        {
            return MultiplicationModulo(i, this->modulo);
        }
    }

    return MultiplicationModulo(this->value, this->modulo);
}

MultiplicationModulo MultiplicationModulo::identity() const
{
    return MultiplicationModulo(1, this->modulo);
}

bool MultiplicationModulo::operator==(const MultiplicationModulo& other) const
{
    return this->value == other.value && this->modulo == other.modulo;
}

bool MultiplicationModulo::operator!=(const MultiplicationModulo& other) const
{
    return !(*this == other);
}

std::string MultiplicationModulo::toString() const
{
    std::ostringstream oss;
    oss << this->value << " (mod " << this->modulo << ")";
    return oss.str();
}

std::ostream& operator<<(std::ostream& os, const MultiplicationModulo& other)
{
    os << other.toString();
    return os;
}

void MultiplicationModulo::example()
{
    std::cout << "MultiplicationModulo::example()" << std::endl;
    
    MultiplicationModulo a(8, 17);
    MultiplicationModulo b(9, 17);
    
    std::cout << "a = " << a << std::endl;
    std::cout << "b = " << b << std::endl;
    std::cout << "a * b = " << (a * b) << std::endl;
    std::cout << "a / b = " << (a / b) << std::endl;
    std::cout << "a * 2 = " << (a * 2) << std::endl;
    std::cout << "a / 2 = " << (a / 2) << std::endl;
    std::cout << "a.inverse() = " << a.inverse() << std::endl;
    std::cout << "a.identity() = " << a.identity() << std::endl;
    std::cout << "b.inverse() = " << b.inverse() << std::endl;
    std::cout << "b.identity() = " << b.identity() << std::endl;
    std::cout << std::endl;
}
