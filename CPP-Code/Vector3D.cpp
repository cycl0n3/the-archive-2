#include "Vector3D.h"

#define _USE_MATH_DEFINES

#include <iostream>
#include <cmath>
#include <sstream>
#include <math.h>

Vector3D::Vector3D() : x(0), y(0), z(0)
{
}

Vector3D::Vector3D(double x, double y, double z) : x(x), y(y), z(z)
{
}

Vector3D::Vector3D(const Vector3D& other) : x(other.x), y(other.y), z(other.z)
{
}

Vector3D::Vector3D(const Point3D& p) : x(p.getX()), y(p.getY()), z(p.getZ())
{
}

Vector3D::~Vector3D()
{
}

Vector3D& Vector3D::operator=(const Vector3D& other)
{
    if (this != &other)
    {
        x = other.x;
        y = other.y;
        z = other.z;
    }
    return *this;
}

Vector3D Vector3D::operator+(const Vector3D& other) const
{
    return Vector3D(x + other.x, y + other.y, z + other.z);
}

Vector3D Vector3D::operator-(const Vector3D& other) const
{
    return Vector3D(x - other.x, y - other.y, z - other.z);
}

Vector3D Vector3D::operator*(double scalar) const
{
    return Vector3D(x * scalar, y * scalar, z * scalar);
}

Vector3D Vector3D::operator/(double scalar) const
{
    return Vector3D(x / scalar, y / scalar, z / scalar);
}

double Vector3D::getX() const
{
    return x;
}

double Vector3D::getY() const
{
    return y;
}

double Vector3D::getZ() const
{
    return z;
}

double Vector3D::norm() const
{
    return sqrt(x * x + y * y + z * z);
}

Vector3D Vector3D::normalize() const
{
    double n = norm();
    return Vector3D(x / n, y / n, z / n);
}

Vector3D Vector3D::rotate(double angle, const Vector3D& axis) const
{
    double c = cos(angle);
    double s = sin(angle);
    
    double t = 1 - c;
    
    double x1 = x * (t * axis.getX() * axis.getX() + c) + y * (t * axis.getX() * axis.getY() - s * axis.getZ()) + z * (t * axis.getX() * axis.getZ() + s * axis.getY());
    double y1 = x * (t * axis.getX() * axis.getY() + s * axis.getZ()) + y * (t * axis.getY() * axis.getY() + c) + z * (t * axis.getY() * axis.getZ() - s * axis.getX());
    double z1 = x * (t * axis.getX() * axis.getZ() - s * axis.getY()) + y * (t * axis.getY() * axis.getZ() + s * axis.getX()) + z * (t * axis.getZ() * axis.getZ() + c);
    
    return Vector3D(x1, y1, z1);
}

Vector3D Vector3D::project(const Vector3D& other) const
{
    return other * (dot(other) / other.norm());
}

Vector3D Vector3D::perpendicular(const Vector3D& other) const
{
    return *this - project(other);
}

double Vector3D::dot(const Vector3D& other) const
{
    return x * other.x + y * other.y + z * other.z;
}

Vector3D Vector3D::cross(const Vector3D& other) const
{
    return Vector3D(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
}

double Vector3D::distance(const Vector3D& other) const
{
    return (*this - other).norm();
}

double Vector3D::angle(const Vector3D& other) const
{
    return acos(dot(other) / (norm() * other.norm())) * 180 / M_PI;
}

bool Vector3D::operator==(const Vector3D& other) const
{
    return x == other.x && y == other.y && z == other.z;
}

bool Vector3D::operator!=(const Vector3D& other) const
{
    return !(*this == other);
}

std::string Vector3D::toString() const
{
    std::ostringstream oss;
    oss << "(" << x << ", " << y << ", " << z << ")";
    return oss.str();
}

bool Vector3D::isParallel(const Vector3D& other) const
{
    return cross(other) == Vector3D(0, 0, 0);
}

bool Vector3D::isOrthogonal(const Vector3D& other) const
{
    return dot(other) == 0;
}

std::ostream& operator<<(std::ostream& os, const Vector3D& v)
{
    os << v.toString();
    return os;
}

// example function
void Vector3D::example()
{
    Vector3D u(4, -1, 2);
    Vector3D v(3, 4, -1);
    Vector3D w(2, 5, -3);

    std::cout << "u = " << u << std::endl;
    std::cout << "v = " << v << std::endl;
    std::cout << "w = " << w << std::endl;

    std::cout << "u x v = " << u.cross(v) << std::endl;
    std::cout << "u x w = " << u.cross(w) << std::endl;
    std::cout << "u x (v + w) = " << u.cross(v + w) << std::endl;
    std::cout << "u x v + u x w = " << u.cross(v) + u.cross(w) << std::endl;

    // cout asterix character 50 times
    std::cout << std::string(50, '*') << std::endl;

    u = Vector3D(4, -1, 3);
    v = Vector3D(7, 2, -1);
    w = Vector3D(2, 1, -1);

    std::cout << "u = " << u << std::endl;
    std::cout << "v = " << v << std::endl;
    std::cout << "w = " << w << std::endl;

    std::cout << "u x v = " << u.cross(v) << std::endl;
    std::cout << "u x w = " << u.cross(w) << std::endl;
    std::cout << "u x (v + w) = " << u.cross(v + w) << std::endl;
    std::cout << "u x v + u x w = " << u.cross(v) + u.cross(w) << std::endl;

    // cout asterix character 50 times
    std::cout << std::string(50, '*') << std::endl;

    Point3D p1(-1, 4, 3);
    Point3D p2(0, 1, 2);
    Point3D p3(-4, -1, 5);

    std::cout << "p1 = " << p1 << std::endl;
    std::cout << "p2 = " << p2 << std::endl;
    std::cout << "p3 = " << p3 << std::endl;

    Vector3D s1(p2 - p1);
    Vector3D s2(p3 - p1);

    // area of the triangle
    std::cout << "area of the triangle = " << s1.cross(s2).norm() / 2 << std::endl;

    // cout asterix character 50 times
    std::cout << std::string(50, '*') << std::endl;

    // u
    std::cout << "u = " << u << std::endl;

    // v x w
    std::cout << "v x w = " << v.cross(w) << std::endl;

    // triple product of u, v, w
    std::cout << "u.(v x w) = " << u.dot(v.cross(w)) << std::endl;

    // vector triple product of u, v, w
    std::cout << "u x (v x w) = " << u.cross(v.cross(w)) << std::endl;

    // vector triple product of u, v, w (using the identity u x (v x w) = (u.w)v - (u.v)w)
    std::cout << "u x (v x w) = " << v * (u.dot(w)) - w * (u.dot(v)) << std::endl;
}
