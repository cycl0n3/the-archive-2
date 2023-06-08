#pragma once

#include<string>

class Vector2D
{
public:
    Vector2D();
    Vector2D(double x, double y);
    Vector2D(const Vector2D& other);
    ~Vector2D();

    Vector2D& operator=(const Vector2D& other);
    Vector2D operator+(const Vector2D& other) const;
    Vector2D operator-(const Vector2D& other) const;
    Vector2D operator*(double scalar) const;
    Vector2D operator/(double scalar) const;
    
    double getX() const;
    double getY() const;

    double norm() const;
    
    Vector2D normalize() const;
    Vector2D rotate(double angle) const;
    Vector2D project(const Vector2D& other) const;
    Vector2D reject(const Vector2D& other) const;

    double dot(const Vector2D& other) const;
    double cross(const Vector2D& other) const;
    double distance(const Vector2D& other) const;
    double angle(const Vector2D& other) const;
    
    bool operator==(const Vector2D& other) const;
    bool operator!=(const Vector2D& other) const;
    
    bool isParallel(const Vector2D& other) const;
    bool isOrthogonal(const Vector2D& other) const;
    
    std::string toString() const;

    static void example();
private:
    double x;
    double y;
};
