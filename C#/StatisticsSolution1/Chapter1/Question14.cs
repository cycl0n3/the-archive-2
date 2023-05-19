using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Question14
    {
        public static void Run()
        {
            var data = new List<double> { 57, 88, 68, 43, 93, 
                63, 51, 37, 77, 83, 
                66, 60, 38, 52, 28, 
                34, 52, 60, 57, 29, 
                92, 37, 38, 17, 67 };

            var mean = Statistics.Mean(data);
            var stdDev = Statistics.StandardDeviation(data);

            // print data with 5 items per line
            Console.WriteLine("Data: ");
            var count = 0;
            foreach (var item in data)
            {
                Console.Write("{0,3} ", item);
                count++;
                if (count % 5 == 0)
                    Console.WriteLine();
            }

            // print sorted data with 5 items per line and highlight central item with green color
            Console.WriteLine("\nSorted Data: ");
            count = 0;
            foreach (var item in data.OrderBy(x => x))
            {
                if (count == data.Count / 2)
                    Console.ForegroundColor = ConsoleColor.Green;
                Console.Write("{0,3} ", item);
                Console.ResetColor();
                count++;
                if (count % 5 == 0)
                    Console.WriteLine();
            }

            Console.WriteLine("Mean: {0}", mean);
            Console.WriteLine("Standard Deviation: {0}", stdDev);
        }
    }
}
