using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Example4
    {
        public double Marks { get; set; }
        public int Frequency { get; set; }
        public int CumulativeFrequency { get; set; }

        public static void Run()
        {
            var f = File.Open("C:\\Users\\Millind\\source\\repos\\StatisticsSolution1\\Chapter1\\Data\\example4.json", FileMode.Open);
            var s = new StreamReader(f);
            var json = s.ReadToEnd();
            
            var data = Newtonsoft.Json.JsonConvert.DeserializeObject<Example4[]>(json);
            if(data == null)
            {
                Console.WriteLine("Data is null");
                return;
            }

            // Calculate cumulative frequency
            var cumulativeFrequency = 0;
            foreach(var d in data)
            {
                cumulativeFrequency += d.Frequency;
                d.CumulativeFrequency = cumulativeFrequency;
            }

            var range = data.Max(x => x.Marks) - data.Min(x => x.Marks);
            Console.WriteLine($"Range: {range}");

            // mean by frequency
            var mean = data.Sum(x => x.Marks * x.Frequency) / data.Sum(x => x.Frequency);
            Console.WriteLine($"Mean: {mean}");

            // sample variance
            var variance = data.Sum(x => Math.Pow(x.Marks - mean, 2) * x.Frequency) / (data.Sum(x => x.Frequency) - 1);
            Console.WriteLine($"Variance: {variance}");

            var stdDev = Math.Sqrt(variance);
            Console.WriteLine($"Standard Deviation: {stdDev}");

            // coefficient of variation
            var cv = stdDev / mean;
            Console.WriteLine($"Coefficient of Variation: {cv}");

            // skewness
            var skewness = data.Sum(x => Math.Pow(x.Marks - mean, 3) * x.Frequency) / (data.Sum(x => x.Frequency) * Math.Pow(stdDev, 3));
            Console.WriteLine($"Skewness: {skewness}");

            var total = data.Sum(x => x.Frequency);
            Console.WriteLine($"Total: {total}");

            // calculate interquartile range
            var q1 = total / 4;
            var q3 = 3 * total / 4;

            Console.WriteLine($"Q1: {q1}");
            Console.WriteLine($"Q3: {q3}");

            var q1Data = data.First(x => x.CumulativeFrequency >= q1);
            var q3Data = data.First(x => x.CumulativeFrequency >= q3);

            Console.WriteLine($"Q1 Data: {q1Data.Marks}");
            Console.WriteLine($"Q3 Data: {q3Data.Marks}");
        }
    }
}
