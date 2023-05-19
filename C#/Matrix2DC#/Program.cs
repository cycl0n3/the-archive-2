namespace ConsoleAppMatrix2D
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Row2D r = new Row2D(1, 2);
            Row2D s = new Row2D(3, 4);

            Console.WriteLine("r = {0}", r);
            Console.WriteLine("s = {0}", s);

            Row2D t = r + s;
            Console.WriteLine("r + s = {0}", t);

            t = r - s;
            Console.WriteLine("r - s = {0}", t);

            t = -r;
            Console.WriteLine("-r = {0}", t);

            t = r * 2;
            Console.WriteLine("r * 2 = {0}", t);

            t = 2 * r;
            Console.WriteLine("2 * r = {0}", t);

            Matrix2D m = new Matrix2D(new Row2D(1, 2), new Row2D(3, 4));
            Matrix2D n = new Matrix2D(new Row2D(5, 6), new Row2D(7, 8));

            Console.WriteLine("m = {0}", m);
            Console.WriteLine("n = {0}", n);

            Matrix2D o = m + n;
            Console.WriteLine("m + n = {0}", o);

            o = m - n;
            Console.WriteLine("m - n = {0}", o);

            o = -m;
            Console.WriteLine("-m = {0}", o);

            o = m * n;
            Console.WriteLine("m * n = {0}", o);

            o = n * m;
            Console.WriteLine("n * m = {0}", o);

            o = m * 2;
            Console.WriteLine("m * 2 = {0}", o);

            o = 2 * m;
            Console.WriteLine("2 * m = {0}", o);
            
            o = ~m;
            Console.WriteLine("~m = {0}", o);

            o = ~n;
            Console.WriteLine("~n = {0}", o);
        }
    }
}
