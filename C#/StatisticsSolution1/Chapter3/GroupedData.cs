using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Chapter3
{
    internal class GroupedData
    {
        private List<GroupedDataItem> _data;

        public GroupedData()
        {
            _data = new List<GroupedDataItem>();
        }

        public void Add(double start, double end, int fx)
        {
            int cumulativeFx = fx;

            if (_data.Count > 0)
            {
                cumulativeFx += _data.Last().CumulativeFx;
            }

            _data.Add(new GroupedDataItem { Start = start, End = end, Fx = fx, CumulativeFx = cumulativeFx });
        }

        public int GetFx(double x)
        {
            var item = _data.FirstOrDefault(i => i.Start <= x && i.End >= x) ?? throw new ArgumentOutOfRangeException(nameof(x));
            return item.Fx;
        }

        public Tuple<double, double> GetRange(double x)
        {
            var item = _data.FirstOrDefault(i => i.Start <= x && i.End >= x) ?? throw new ArgumentOutOfRangeException(nameof(x));
            return new Tuple<double, double>(item.Start, item.End);
        }

        public double SumFx
        {
            get
            {
                double sum = 0;
                foreach (var item in _data)
                {
                    sum += item.Fx;
                }
                return sum;
            }
        }

        public double SumFxMid
        {
            get
            {
                double sum = 0;
                foreach (var item in _data)
                {
                    sum += item.Fx * (item.End + item.Start) / 2;
                }
                return sum;
            }
        }

        public double Mean
        {
            get
            {
                return SumFxMid / SumFx;
            }
        }

        public double Median
        {
            get
            {
                double median = 0;
                double half = SumFx / 2;
                foreach (var item in _data)
                {
                    if (item.CumulativeFx >= half)
                    {
                        median = item.Start + (item.End - item.Start) * (half - (item.CumulativeFx - item.Fx)) / item.Fx;
                        break;
                    }
                }
                return median;
            }
        }

        public double SumOfSquares
        {
            get
            {
                double sum = 0;
                foreach (var item in _data)
                {
                    sum += item.Fx * Math.Pow((item.End + item.Start) / 2 - Mean, 2);
                }
                return sum;
            }
        }

        public double Variance
        {
            get
            {
                return SumOfSquares / SumFx;
            }
        }

        public double StandardDeviation
        {
            get
            {
                return Math.Sqrt(Variance);
            }
        }

        public double Skewness
        {
            get
            {
                return 3 * (Mean - Median) / StandardDeviation;
            }
        }

        public double Kurtosis
        {
            get
            {
                double sum = 0;
                foreach (var item in _data)
                {
                    sum += item.Fx * Math.Pow((item.End + item.Start) / 2 - Mean, 4);
                }
                return sum / (Math.Pow(StandardDeviation, 4) * SumFx) - 3;
            }
        }
    }

    internal class GroupedDataItem
    {
        public double Start { get; set; }
        public double End { get; set; }
        public int Fx { get; set; }
        public int CumulativeFx { get; set; }
    }
}
