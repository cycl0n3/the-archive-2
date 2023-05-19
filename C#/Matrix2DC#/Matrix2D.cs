using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleAppMatrix2D
{
    internal class Matrix2D
    {
        private Row2D[] _rows = new Row2D[2];

        public Matrix2D(Row2D row0, Row2D row1)
        {
            _rows[0] = row0;
            _rows[1] = row1;
        }

        public Row2D Row0
        {
            get { return _rows[0]; }
            set { _rows[0] = value; }
        }

        public Row2D Row1
        {
            get { return _rows[1]; }
            set { _rows[1] = value; }
        }

        public Row2D this[int index]
        {
            get { return _rows[index]; }
            set { _rows[index] = value; }
        }

        public override string ToString()
        {
            string s = string.Format("[{0}, {1}]", Row0, Row1);
            return s;
        }

        public static Matrix2D operator +(Matrix2D a, Matrix2D b)
        {
            return new Matrix2D(a.Row0 + b.Row0, a.Row1 + b.Row1);
        }

        public static Matrix2D operator -(Matrix2D a, Matrix2D b)
        {
            return new Matrix2D(a.Row0 - b.Row0, a.Row1 - b.Row1);
        }

        // unary minus
        public static Matrix2D operator -(Matrix2D a)
        {
            return new Matrix2D(-a.Row0, -a.Row1);
        }

        public static Matrix2D operator *(Matrix2D a, Matrix2D b)
        {
            Row2D row0 = new Row2D(
                a.Row0.X * b.Row0.X + a.Row0.Y * b.Row1.X,
                a.Row0.X * b.Row0.Y + a.Row0.Y * b.Row1.Y
            );
            Row2D row1 = new Row2D(
                a.Row1.X * b.Row0.X + a.Row1.Y * b.Row1.X,
                a.Row1.X * b.Row0.Y + a.Row1.Y * b.Row1.Y
            );
            return new Matrix2D(row0, row1);
        }

        public static Matrix2D operator *(Matrix2D a, float s)
        {
            Row2D row0 = new Row2D(a.Row0.X * s, a.Row0.Y * s);
            Row2D row1 = new Row2D(a.Row1.X * s, a.Row1.Y * s);
            return new Matrix2D(row0, row1);
        }

        public static Matrix2D operator *(float s, Matrix2D a)
        {
            return a * s;
        }

        public static Matrix2D operator /(Matrix2D a, float s)
        {
            Row2D row0 = new Row2D(a.Row0.X / s, a.Row0.Y / s);
            Row2D row1 = new Row2D(a.Row1.X / s, a.Row1.Y / s);
            return new Matrix2D(row0, row1);
        }

        public static bool operator ==(Matrix2D a, Matrix2D b)
        {
            return a.Row0 == b.Row0 && a.Row1 == b.Row1;
        }

        public static bool operator !=(Matrix2D a, Matrix2D b)
        {
            return !(a == b);
        }

        public override bool Equals(object obj)
        {
            if (obj is Matrix2D d)
            {
                return this == d;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return Row0.GetHashCode() ^ Row1.GetHashCode();
        }

        public static Matrix2D Identity
        {
            get { return new Matrix2D(new Row2D(1, 0), new Row2D(0, 1)); }
        }

        // inverse
        public static Matrix2D operator ~(Matrix2D a)
        {
            float det = a.Row0.X * a.Row1.Y - a.Row0.Y * a.Row1.X;

            if (Common.IsEqual(det, 0))
            {
                throw new Exception("Matrix is singular and cannot be inverted");
            }

            float invDet = 1.0f / det;

            Row2D row0 = new Row2D(a.Row1.Y * invDet, -a.Row0.Y * invDet);
            Row2D row1 = new Row2D(-a.Row1.X * invDet, a.Row0.X * invDet);

            return new Matrix2D(row0, row1);
        }

        public static Matrix2D Rotate(float angle)
        {
            float cos = (float)Math.Cos(angle);
            float sin = (float)Math.Sin(angle);

            return new Matrix2D(new Row2D(cos, -sin), new Row2D(sin, cos));
        }

        public static Matrix2D Scale(float sx, float sy)
        {
            return new Matrix2D(new Row2D(sx, 0), new Row2D(0, sy));
        }
    }
}
