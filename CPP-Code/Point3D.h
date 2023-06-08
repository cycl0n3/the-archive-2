#pragma once

#include<string>
#include<iostream>

class Point3D
{
public:
    Point3D();
    Point3D(double x, double y, double z);
    Point3D(const Point3D& other);
    ~Point3D();

    Point3D& operator=(const Point3D& other);

    Point3D operator+(const Point3D& other) const;
    Point3D operator-(const Point3D& other) const;
    Point3D operator*(double scalar) const;
    Point3D operator/(double scalar) const;
    
    double getX() const;
    double getY() const;
    double getZ() const;

    double distance(const Point3D& other) const;
    double distance(const Point3D& p1, const Point3D& p2) const;

    bool operator==(const Point3D& other) const;
    bool operator!=(const Point3D& other) const;

    std::string toString() const;

    // overload <<
    friend std::ostream& operator<<(std::ostream& os, const Point3D& p);
private:
    double x;
    double y;
    double z;
};
