using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    internal class Vector
    {
        public double X { get; set; }
        public double Y { get; set; }
        public double Z { get; set; }

        public Vector(double x, double y, double z)
        {
            this.X = x;
            this.Y = y;
            this.Z = z;
        }

        // add two vectors
        public static Vector operator +(Vector v1, Vector v2)
        {
            return new Vector(v1.X + v2.X, v1.Y + v2.Y, v1.Z + v2.Z);
        }

        // subtract two vectors
        public static Vector operator -(Vector v1, Vector v2)
        {
            return new Vector(v1.X - v2.X, v1.Y - v2.Y, v1.Z - v2.Z);
        }

        // multiply a vector by a scalar
        public static Vector operator *(double scalar, Vector v)
        {
            return new Vector(scalar * v.X, scalar * v.Y, scalar * v.Z);
        }

        // divide a vector by a scalar
        public static Vector operator /(Vector v, double scalar)
        {
            return new Vector(v.X / scalar, v.Y / scalar, v.Z / scalar);
        }

        // negate a vector
        public static Vector operator -(Vector v)
        {
            return new Vector(-v.X, -v.Y, -v.Z);
        }

        // dot product of two vectors
        public static double operator *(Vector v1, Vector v2)
        {
            return v1.X * v2.X + v1.Y * v2.Y + v1.Z * v2.Z;
        }

        // cross product of two vectors
        public static Vector operator %(Vector v1, Vector v2)
        {
            return new Vector(
                v1.Y * v2.Z - v1.Z * v2.Y,
                v1.Z * v2.X - v1.X * v2.Z,
                v1.X * v2.Y - v1.Y * v2.X
            );
        }

        // vector magnitude
        public double Magnitude()
        {
            return Math.Sqrt(this * this);
        }

        // vector normalization
        public Vector Normalize()
        {
            return this / this.Magnitude();
        }

        // vector string representation
        public override string ToString()
        {
            return $"({this.X}, {this.Y}, {this.Z})";
        }

        // vector equality
        public override bool Equals(object? obj)
        {
            if (obj == null || GetType() != obj.GetType())
                return false;
            Vector v = (Vector)obj;
            return this.X == v.X && this.Y == v.Y && this.Z == v.Z;
        }

        // vector hash code
        public override int GetHashCode()
        {
            return this.X.GetHashCode() ^ this.Y.GetHashCode() ^ this.Z.GetHashCode();
        }

        // vector comparison
        public static bool operator ==(Vector v1, Vector v2)
        {
            return v1.Equals(v2);
        }

        // vector comparison
        public static bool operator !=(Vector v1, Vector v2)
        {
            return !v1.Equals(v2);
        }

        // vector comparison
        public static bool operator <(Vector v1, Vector v2)
        {
            return v1.Magnitude() < v2.Magnitude();
        }

        // vector comparison
        public static bool operator >(Vector v1, Vector v2)
        {
            return v1.Magnitude() > v2.Magnitude();
        }

        // vector comparison
        public static bool operator <=(Vector v1, Vector v2)
        {
            return v1.Magnitude() <= v2.Magnitude();
        }

        // vector comparison
        public static bool operator >=(Vector v1, Vector v2)
        {
            return v1.Magnitude() >= v2.Magnitude();
        }
    }
}
