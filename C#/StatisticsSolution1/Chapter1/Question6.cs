using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter1
{
    internal class Question6
    {
        // list of 16 28 29 13 17 20 11 34 32 27 25 30 19 18 33
        public static void Run()
        {
            List<double> data = new() { 16, 28, 29, 13, 17, 20, 11, 34, 32, 27, 25, 30, 19, 18, 33 };
            Console.WriteLine("Data: {0}", String.Join(", ", data));
            Console.WriteLine("Sorted Data: {0}", String.Join(", ", data.OrderBy(x => x)));

            // 35th percentile
            Console.WriteLine("35th percentile: {0}", Statistics.Percentile(data, 0.35));

            // 55th percentile
            Console.WriteLine("55th percentile: {0}", Statistics.Percentile(data, 0.55));

            // all quartiles
            Console.WriteLine("Quartiles: {0}", String.Join(", ", Statistics.Quartiles(data)));
        }
    }
}
