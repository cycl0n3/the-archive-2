using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleAppMatrix2D
{
    internal class Row2D
    {
        private float[] _values = new float[2];

        public Row2D(float x, float y)
        {
            _values[0] = x;
            _values[1] = y;
        }

        public float X
        {
            get { return _values[0]; }
            set { _values[0] = value; }
        }

        public float Y
        {
            get { return _values[1]; }
            set { _values[1] = value; }
        }

        public float this[int index]
        {
            get { return _values[index]; }
            set { _values[index] = value; }
        }

        public override string ToString()
        {
            return string.Format("[{0}, {1}]", X, Y);
        }

        public static Row2D operator +(Row2D a, Row2D b)
        {
            return new Row2D(a.X + b.X, a.Y + b.Y);
        }

        public static Row2D operator -(Row2D a, Row2D b)
        {
            return new Row2D(a.X - b.X, a.Y - b.Y);
        }

        // unary minus
        public static Row2D operator -(Row2D a)
        {
            return new Row2D(-a.X, -a.Y);
        }

        public static Row2D operator *(Row2D a, float s)
        {
            return new Row2D(a.X * s, a.Y * s);
        }

        public static Row2D operator *(float s, Row2D a)
        {
            return new Row2D(a.X * s, a.Y * s);
        }

        public static Row2D operator /(Row2D a, float s)
        {
            return new Row2D(a.X / s, a.Y / s);
        }

        public static bool operator ==(Row2D a, Row2D b)
        {
            return Common.IsEqual(a.X, b.X) && Common.IsEqual(a.Y, b.Y);
        }

        public static bool operator !=(Row2D a, Row2D b)
        {
            return !(a == b);
        }

        // equals and hashcode
        public override bool Equals(object obj)
        {
            if (obj is Row2D other)
            {
                return this == other;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return X.GetHashCode() ^ Y.GetHashCode();
        }
    }
}
