using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Question7
    {
        public static void Run()
        {
            List<double> data = new() { 120, 138, 97, 118, 172, 144, 
                138, 107, 94, 119, 139, 145, 
                162, 127, 112, 150, 143, 80, 
                105, 116, 142, 128, 116, 171 };

            Console.WriteLine("Data: {0}", String.Join(", ", data));
            Console.WriteLine("Sorted Data: {0}", String.Join(", ", data.OrderBy(x => x)));

            // Percentile 20
            Console.WriteLine("Percentile 20: {0}", Statistics.Percentile(data, 0.2));

            // Percentile 47
            Console.WriteLine("Percentile 47: {0}", Statistics.Percentile(data, 0.47));

            // Percentile 83
            Console.WriteLine("Percentile 83: {0}", Statistics.Percentile(data, 0.83));

            // Q 1
            Console.WriteLine("Q1: {0}", Statistics.Percentile(data, 0.25));

            // Q 2
            Console.WriteLine("Q2: {0}", Statistics.Percentile(data, 0.5));

            // Q 3
            Console.WriteLine("Q3: {0}", Statistics.Percentile(data, 0.75));

            double mean = Statistics.Mean(data);
            double stdDev = Statistics.StandardDeviation(data);

            Console.WriteLine("Mean: {0}", mean);
            Console.WriteLine("Standard Deviation: {0}", stdDev);

            double[] ks = { 0.5, 1.5, 2.1 };

            foreach (double k in ks)
            {
                Tuple<long, long> range = Statistics.EmpiricalRuleRange(data, k);
                Console.WriteLine("mean - {2}*stdDev: {0}, mean + {2}*stdDev: {1}", range.Item1, range.Item2, k);
                Console.WriteLine("Percentage of data within {0} standard deviations of the mean: {1}", k, Statistics.EmpiricalRule(data, k));
            }
        }
    }
}
