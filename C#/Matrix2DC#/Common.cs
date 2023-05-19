using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleAppMatrix2D
{
    internal class Common
    {
        private static float EPSILON = 0.0000001f;

        public static bool IsEqual(float a, float b)
        {
            return Math.Abs(a - b) < EPSILON;
        }
    }
}
