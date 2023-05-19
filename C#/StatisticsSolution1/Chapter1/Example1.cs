using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Example1
    {
        class Record
        {
            public string? Company { get; set; }
            public int Cars { get; set; }
        }

        public static void Run()
        {
            var src = "C:\\Users\\Millind\\source\\repos\\StatisticsSolution1\\Chapter1\\Data\\example1.json";
            var json = System.IO.File.ReadAllText(src);
            var data = Newtonsoft.Json.JsonConvert.DeserializeObject<Record[]>(json);
            if (data == null)
            {
                Console.WriteLine("Data is null");
                return;
            }

            // use Stats.cs to calculate the mean
            var mean = Statistics.Mean(data.Select(x => (double)x.Cars));
            Console.WriteLine($"Mean: {mean}");

            // 80th percentile
            var percentile = Statistics.Percentile(data.Select(x => (double)x.Cars), 0.8);
            Console.WriteLine($"80th percentile: {percentile}");
        }
    }
}
