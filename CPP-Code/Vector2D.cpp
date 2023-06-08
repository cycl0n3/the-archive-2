#include "Vector2D.h"

#define _USE_MATH_DEFINES

#include <iostream>
#include <cmath>
#include <sstream>
#include <string>
#include <math.h>

Vector2D::Vector2D()
    : x(0.0), y(0.0)
{
}

Vector2D::Vector2D(double x, double y)
    : x(x), y(y)
{
}

Vector2D::Vector2D(const Vector2D& other)
    : x(other.x), y(other.y)
{
}

Vector2D::~Vector2D()
{
}

Vector2D& Vector2D::operator=(const Vector2D& other)
{
    if (this != &other)
    {
        x = other.x;
        y = other.y;
    }
    return *this;
}

Vector2D Vector2D::operator+(const Vector2D& other) const
{
    return Vector2D(x + other.x, y + other.y);
}

Vector2D Vector2D::operator-(const Vector2D& other) const
{
    return Vector2D(x - other.x, y - other.y);
}

Vector2D Vector2D::operator*(double scalar) const
{
    return Vector2D(x * scalar, y * scalar);
}

Vector2D Vector2D::operator/(double scalar) const
{
    return Vector2D(x / scalar, y / scalar);
}

double Vector2D::getX() const
{
    return x;
}

double Vector2D::getY() const
{
    return y;
}

std::string Vector2D::toString() const
{
    std::ostringstream oss;
    oss << "(" << x << ", " << y << ")";
    return oss.str();
}

double Vector2D::norm() const
{
    return std::sqrt(x * x + y * y);
}

Vector2D Vector2D::normalize() const
{
    double n = norm();
    return Vector2D(x / n, y / n);
}

Vector2D Vector2D::rotate(double angle) const
{
    double rad = angle * M_PI / 180.0;
    double c = std::cos(rad);
    double s = std::sin(rad);
    return Vector2D(x * c - y * s, x * s + y * c);
}

Vector2D Vector2D::project(const Vector2D& other) const
{
    return other * (dot(other) / other.dot(other));
}

Vector2D Vector2D::reject(const Vector2D& other) const
{
    return *this - project(other);
}

double Vector2D::dot(const Vector2D& other) const
{
    return x * other.x + y * other.y;
}

double Vector2D::cross(const Vector2D& other) const
{
    return x * other.y - y * other.x;
}

double Vector2D::distance(const Vector2D& other) const
{
    return (*this - other).norm();
}

double Vector2D::angle(const Vector2D& other) const
{
    return std::acos(dot(other) / (norm() * other.norm())) * 180.0 / M_PI;
}

bool Vector2D::operator==(const Vector2D& other) const
{
    return x == other.x && y == other.y;
}

bool Vector2D::operator!=(const Vector2D& other) const
{
    return !(*this == other);
}

bool Vector2D::isParallel(const Vector2D& other) const
{
    return cross(other) == 0;
}

bool Vector2D::isOrthogonal(const Vector2D& other) const
{
    return dot(other) == 0;
}

void Vector2D::example()
{
    Vector2D v1(1.0, 2.0);
    Vector2D v2(3.0, 4.0);
    Vector2D v3 = v1 + v2;

    std::cout << "v1 = " << v1.toString() << std::endl;
    std::cout << "v2 = " << v2.toString() << std::endl;
    std::cout << "v3 = v1 + v2 = " << v3.toString() << std::endl;

    // some more examples
    Vector2D v4 = v1 - v2;
    Vector2D v5 = v1 * 2.0;
    Vector2D v6 = v1 / 2.0;
    Vector2D v7 = v1.normalize();
    Vector2D v8 = v1.rotate(90.0);
    Vector2D v9 = v1.project(v2);
    Vector2D v10 = v1.reject(v2);
    double d1 = v1.dot(v2);
    double d2 = v1.cross(v2);
    double d3 = v1.distance(v2);
    double d4 = v1.angle(v2);
    bool b1 = v1 == v2;
    bool b2 = v1 != v2;
    bool b3 = v1.isParallel(v2);
    bool b4 = v1.isOrthogonal(v2);

    std::cout << "v4 = v1 - v2 = " << v4.toString() << std::endl;
    std::cout << "v5 = v1 * 2.0 = " << v5.toString() << std::endl;
    std::cout << "v6 = v1 / 2.0 = " << v6.toString() << std::endl;
    std::cout << "v7 = v1.normalize() = " << v7.toString() << std::endl;
    std::cout << "v8 = v1.rotate(90.0) = " << v8.toString() << std::endl;
    std::cout << "v9 = v1.project(v2) = " << v9.toString() << std::endl;
    std::cout << "v10 = v1.perpendicular(v2) = " << v10.toString() << std::endl;
    std::cout << "d1 = v1.dot(v2) = " << d1 << std::endl;
    std::cout << "d2 = v1.cross(v2) = " << d2 << std::endl;
    std::cout << "d3 = v1.distance(v2) = " << d3 << std::endl;
    std::cout << "d4 = v1.angle(v2) = " << d4 << std::endl;
    std::cout << "b1 = v1 == v2 = " << b1 << std::endl;
    std::cout << "b2 = v1 != v2 = " << b2 << std::endl;
    std::cout << "b3 = v1.isParallel(v2) = " << b3 << std::endl;
    std::cout << "b4 = v1.isOrthogonal(v2) = " << b4 << std::endl;
}