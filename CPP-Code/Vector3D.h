#pragma once

#include<string>
#include<iostream>

#include "Point3D.h"

class Vector3D
{
public:
    Vector3D();
    Vector3D(double x, double y, double z);
    Vector3D(const Point3D& p);
    Vector3D(const Vector3D& other);
    ~Vector3D();

    Vector3D& operator=(const Vector3D& other);
    Vector3D operator+(const Vector3D& other) const;
    Vector3D operator-(const Vector3D& other) const;
    Vector3D operator*(double scalar) const;
    Vector3D operator/(double scalar) const;
    
    double getX() const;
    double getY() const;
    double getZ() const;

    double norm() const;
    
    Vector3D normalize() const;
    Vector3D rotate(double angle, const Vector3D& axis) const;
    Vector3D project(const Vector3D& other) const;
    Vector3D perpendicular(const Vector3D& other) const;

    double dot(const Vector3D& other) const;
    Vector3D cross(const Vector3D& other) const;
    double distance(const Vector3D& other) const;
    double angle(const Vector3D& other) const;
    
    bool operator==(const Vector3D& other) const;
    bool operator!=(const Vector3D& other) const;
    
    bool isParallel(const Vector3D& other) const;
    bool isOrthogonal(const Vector3D& other) const;
    
    std::string toString() const;

    static void example();

    friend std::ostream& operator<<(std::ostream& os, const Vector3D& v);
private:
    double x;
    double y;
    double z;
};
