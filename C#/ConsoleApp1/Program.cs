using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // position = P
            var position = (double t) =>
            {
                return new Tuple<double, double, double>(t, t * t, t * t * t);
            };

            // unit tangent = T = P' / ||P'||
            var tangent = (double t) =>
            {
                var pVec = position(t);
                var pPrimeVec = position(t + 0.01);

                var pPrime = new Tuple<double, double, double>(
                    pPrimeVec.Item1 - pVec.Item1,
                    pPrimeVec.Item2 - pVec.Item2,
                    pPrimeVec.Item3 - pVec.Item3
                );

                var pPrimeMag = Math.Sqrt(
                    pPrime.Item1 * pPrime.Item1
                        + pPrime.Item2 * pPrime.Item2
                        + pPrime.Item3 * pPrime.Item3
                );

                return new Tuple<double, double, double>(
                    pPrime.Item1 / pPrimeMag,
                    pPrime.Item2 / pPrimeMag,
                    pPrime.Item3 / pPrimeMag
                );
            };

            // curvature = ||T'|| / ||P'||
            var curvature = (double t) =>
            {
                var tVec = tangent(t);
                var tPrimeVec = tangent(t + 0.01);

                var tPrime = new Tuple<double, double, double>(
                    tPrimeVec.Item1 - tVec.Item1,
                    tPrimeVec.Item2 - tVec.Item2,
                    tPrimeVec.Item3 - tVec.Item3
                );

                var tPrimeMag = Math.Sqrt(
                    tPrime.Item1 * tPrime.Item1
                        + tPrime.Item2 * tPrime.Item2
                        + tPrime.Item3 * tPrime.Item3
                );

                var pPrimeVec = position(t + 0.01);

                var pPrime = new Tuple<double, double, double>(
                    pPrimeVec.Item1 - tVec.Item1,
                    pPrimeVec.Item2 - tVec.Item2,
                    pPrimeVec.Item3 - tVec.Item3
                );

                var pPrimeMag = Math.Sqrt(
                    pPrime.Item1 * pPrime.Item1
                        + pPrime.Item2 * pPrime.Item2
                        + pPrime.Item3 * pPrime.Item3
                );

                return tPrimeMag / pPrimeMag;
            };

            // normal = N = T' / ||T'||
            var normal = (double t) =>
            {
                var tVec = tangent(t);
                var tPrimeVec = tangent(t + 0.01);

                var tPrime = new Tuple<double, double, double>(
                    tPrimeVec.Item1 - tVec.Item1,
                    tPrimeVec.Item2 - tVec.Item2,
                    tPrimeVec.Item3 - tVec.Item3
                );

                var tPrimeMag = Math.Sqrt(
                    tPrime.Item1 * tPrime.Item1
                        + tPrime.Item2 * tPrime.Item2
                        + tPrime.Item3 * tPrime.Item3
                );

                return new Tuple<double, double, double>(
                    tPrime.Item1 / tPrimeMag,
                    tPrime.Item2 / tPrimeMag,
                    tPrime.Item3 / tPrimeMag
                );
            };

            // binormal = B = T x N
            var binormal = (double t) =>
            {
                var tVec = tangent(t);
                var nVec = normal(t);

                return new Tuple<double, double, double>(
                    tVec.Item2 * nVec.Item3 - tVec.Item3 * nVec.Item2,
                    tVec.Item3 * nVec.Item1 - tVec.Item1 * nVec.Item3,
                    tVec.Item1 * nVec.Item2 - tVec.Item2 * nVec.Item1
                );
            };

            // torsion = -B' . N
            var torsion = (double t) =>
            {
                var bVec = binormal(t);
                var bPrimeVec = binormal(t + 0.01);

                var bPrime = new Tuple<double, double, double>(
                    bPrimeVec.Item1 - bVec.Item1,
                    bPrimeVec.Item2 - bVec.Item2,
                    bPrimeVec.Item3 - bVec.Item3
                );

                var nVec = normal(t);

                return -1
                    * (
                        bPrime.Item1 * nVec.Item1
                        + bPrime.Item2 * nVec.Item2
                        + bPrime.Item3 * nVec.Item3
                    );
            };

            // curvature radius = 1 / curvature
            var curvatureRadius = (double t) =>
            {
                return 1 / curvature(t);
            };

            // arc length = ||P'||
            var arcLength = (double t) =>
            {
                var pVec = position(t);
                var pPrimeVec = position(t + 0.01);

                var pPrime = new Tuple<double, double, double>(
                    pPrimeVec.Item1 - pVec.Item1,
                    pPrimeVec.Item2 - pVec.Item2,
                    pPrimeVec.Item3 - pVec.Item3
                );
                
                return Math.Sqrt(
                    pPrime.Item1 * pPrime.Item1
                        + pPrime.Item2 * pPrime.Item2
                        + pPrime.Item3 * pPrime.Item3
                );
            };

            // arc length parameter = s
            var arcLengthParameter = (double s) =>
            {
                var t = 0.0;
                var s0 = 0.0;
                
                while (s0 < s)
                {
                    s0 += arcLength(t);
                    t += 0.01;
                }

                return t;
            };

            // examples

            var p = position(2.0);
            Console.WriteLine("position(2.0) = ({0}, {1}, {2})", p.Item1, p.Item2, p.Item3);

            var t = tangent(2.0);
            Console.WriteLine("tangent(2.0) = ({0}, {1}, {2})", t.Item1, t.Item2, t.Item3);

            var c = curvature(2.0);
            Console.WriteLine("curvature(2.0) = {0}", c);

            var r = curvatureRadius(2.0);
            Console.WriteLine("curvatureRadius(2.0) = {0}", r);

            var n = normal(2.0);
            Console.WriteLine("normal(2.0) = ({0}, {1}, {2})", n.Item1, n.Item2, n.Item3);

            var b = binormal(2.0);
            Console.WriteLine("binormal(2.0) = ({0}, {1}, {2})", b.Item1, b.Item2, b.Item3);

            var to = torsion(2.0);
            Console.WriteLine("torsion(2.0) = {0}", to);

            var asp = arcLengthParameter(2.0);
            Console.WriteLine("arcLengthParameter(2.0) = {0}", asp);
        }
    }
}
