using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Statistics
    {
        public static double Range(IEnumerable<double> data)
        {
            return data.Max() - data.Min();
        }

        public static double Mean(IEnumerable<double> data)
        {
            return data.Sum() / data.Count();
        }

        public static double MeanAbsoluteDeviation(IEnumerable<double> data)
        {
            var mean = Mean(data);
            return data.Sum(x => Math.Abs(x - mean)) / data.Count();
        }

        public static double Variance(IEnumerable<double> data)
        {
            var mean = Mean(data);
            return data.Sum(x => Math.Pow(x - mean, 2)) / data.Count();
        }

        public static double StandardDeviation(IEnumerable<double> data)
        {
            return Math.Sqrt(Variance(data));
        }

        // EMPIRICAL RULE
        public static double EmpiricalRule(IEnumerable<double> data, double k)
        {
            var mean = Mean(data);
            var stdDev = StandardDeviation(data);
            var n = data.Count();
            var count = data.Count(x => Math.Abs(x - mean) <= k * stdDev);
            return (double)count / n * 100;
        }

        public static Tuple<long, long> EmpiricalRuleRange(IEnumerable<double> data, double k)
        {
            var mean = Mean(data);
            var stdDev = StandardDeviation(data);
            return new Tuple<long, long>((long)(mean - k * stdDev), (long)(mean + k * stdDev));
        }

        public static double CoefficientOfVariation(IEnumerable<double> data)
        {
            return StandardDeviation(data) / Mean(data);
        }

        public static double Skewness(IEnumerable<double> data)
        {
            var mean = Mean(data);
            var stdDev = StandardDeviation(data);
            return data.Sum(x => Math.Pow(x - mean, 3)) / (data.Count() * Math.Pow(stdDev, 3));
        }

        public static double Kurtosis(IEnumerable<double> data)
        {
            var mean = Mean(data);
            var stdDev = StandardDeviation(data);
            return data.Sum(x => Math.Pow(x - mean, 4)) / (data.Count() * Math.Pow(stdDev, 4));
        }

        public static double Covariance(IEnumerable<double> data1, IEnumerable<double> data2)
        {
            var mean1 = Mean(data1);
            var mean2 = Mean(data2);
            var n = data1.Count();
            return data1.Zip(data2, (x, y) => (x - mean1) * (y - mean2)).Sum() / n;
        }

        public static double Correlation(IEnumerable<double> data1, IEnumerable<double> data2)
        {
            var cov = Covariance(data1, data2);
            var stdDev1 = StandardDeviation(data1);
            var stdDev2 = StandardDeviation(data2);
            return cov / (stdDev1 * stdDev2);
        }

        public static double Median(IEnumerable<double> data)
        {
            var sortedData = data.OrderBy(x => x).ToList();
            var n = sortedData.Count;
            if (n % 2 == 0)
            {
                return (sortedData[n / 2 - 1] + sortedData[n / 2]) / 2;
            }
            else
            {
                return sortedData[n / 2];
            }
        }

        public static double Mode(IEnumerable<double> data)
        {
            var groupedData = data.GroupBy(x => x).OrderByDescending(x => x.Count());
            return groupedData.First().Key;
        }

        public static double Percentile(IEnumerable<double> data, double p)
        {
            var sortedData = data.OrderBy(x => x).ToList();
            var n = sortedData.Count;
            var i = (int)Math.Ceiling(p * n);
            return sortedData[i - 1];
        }

        public static Tuple<double, double, double> Quartiles(IEnumerable<double> data)
        {
            var sortedData = data.OrderBy(x => x).ToList();
            var n = sortedData.Count;

            var q1 = Percentile(sortedData, 0.25);
            var q2 = Percentile(sortedData, 0.5);
            var q3 = Percentile(sortedData, 0.75);
            
            return Tuple.Create(q1, q2, q3);
        }

        public static double InterquartileRange(IEnumerable<double> data)
        {
            var quartiles = Quartiles(data);
            return quartiles.Item3 - quartiles.Item1;
        }

        public static double PercentileRank(IEnumerable<double> data, double x)
        {
            var sortedData = data.OrderBy(y => y).ToList();
            var n = sortedData.Count;
            var i = sortedData.IndexOf(x) + 1;
            return i / (double)n;
        }

        public static double ZScore(double x, IEnumerable<double> data)
        {
            var mean = Mean(data);
            var stdDev = StandardDeviation(data);
            return (x - mean) / stdDev;
        }

        public static double SumOfSquares(IEnumerable<double> data)
        {
            var mean = Mean(data);
            return data.Sum(x => Math.Pow(x - mean, 2));
        }
    }
}
