using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Example2
    {
        public static void Run()
        {
            // list of 14, 12, 19, 23, 5, 13, 28, 17
            List<double> data = new() { 14, 12, 19, 23, 5, 13, 28, 17 };
            Console.WriteLine("Data: {0}", data);

            // 30th percentile
            Console.WriteLine("30th percentile: {0}", Statistics.Percentile(data, 0.3));

            data = new() { 106, 109, 110, 112, 114, 115, 116, 121, 122, 125, 129 };
            Console.WriteLine("Data: " + data);

            // quartiles
            Console.WriteLine("25th percentile: {0}", Statistics.Percentile(data, 0.25));
            Console.WriteLine("50th percentile: {0}", Statistics.Percentile(data, 0.5));
            Console.WriteLine("75th percentile: {0}", Statistics.Percentile(data, 0.75));
        }
    }
}
