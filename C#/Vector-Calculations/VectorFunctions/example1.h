#pragma once

#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <functional>
#include <tuple>
#include <iomanip>
#include <fstream>

// Example 1: Write a function that takes a vector of doubles and returns a vector of doubles
void runExample1()
{
    // F(t) = (cos t, sin t, 2t)
    std::vector<double> t(25);
    std::iota(t.begin(), t.end(), 0);
    std::transform(t.begin(), t.end(), t.begin(), [](double x) { return x / 10; });
    std::vector<double> x(t.size()), y(t.size()), z(t.size());
    std::transform(t.begin(), t.end(), x.begin(), [](double t) { return cos(t); });
    std::transform(t.begin(), t.end(), y.begin(), [](double t) { return sin(t); });
    std::transform(t.begin(), t.end(), z.begin(), [](double t) { return 2 * t; });

    // print F(t) in a table and setw 10
    std::cout << std::setw(10) << "t" << std::setw(10) << "x" << std::setw(10) << "y" << std::setw(10) << "z" << std::endl;

    for (int i = 0; i < t.size(); ++i)
    {
        std::cout << std::setw(10) << t[i] << std::setw(10) << x[i] << std::setw(10) << y[i] << std::setw(10) << z[i] << std::endl;
    }

    // write results in a csv file
    std::ofstream fout("F(t).csv");
    fout << "t,x,y,z" << std::endl;
    for (int i = 0; i < t.size(); ++i)
    {
        fout << t[i] << "," << x[i] << "," << y[i] << "," << z[i] << std::endl;
    }
    fout.close();

    // write results in a json file
    std::ofstream fout2("F(t).json");
    fout2 << "{" << std::endl;
    fout2 << "\"t\": [";
    for (int i = 0; i < t.size(); ++i)
    {
        fout2 << t[i];
        if (i < t.size() - 1)
        {
            fout2 << ",";
        }
    }
    fout2 << "]," << std::endl;
    fout2 << "\"x\": [";
    for (int i = 0; i < x.size(); ++i)
    {
        fout2 << x[i];
        if (i < x.size() - 1)
        {
            fout2 << ",";
        }
    }
    fout2 << "]," << std::endl;
    fout2 << "\"y\": [";
    for (int i = 0; i < y.size(); ++i)
    {
        fout2 << y[i];
        if (i < y.size() - 1)
        {
            fout2 << ",";
        }
    }
    fout2 << "]," << std::endl;
    fout2 << "\"z\": [";
    for (int i = 0; i < z.size(); ++i)
    {
        fout2 << z[i];
        if (i < z.size() - 1)
        {
            fout2 << ",";
        }
    }
    fout2 << "]" << std::endl;
    fout2 << "}" << std::endl;
    fout2.close();

    // write results in a xml file
    std::ofstream fout3("F(t).xml");
    fout3 << "<Ft>" << std::endl;
    fout3 << "<t>";
    for (int i = 0; i < t.size(); ++i)
    {
        fout3 << t[i];
        if (i < t.size() - 1)
        {
            fout3 << ",";
        }
    }
    fout3 << "</t>" << std::endl;
    fout3 << "<x>";
    for (int i = 0; i < x.size(); ++i)
    {
        fout3 << x[i];
        if (i < x.size() - 1)
        {
            fout3 << ",";
        }
    }
    fout3 << "</x>" << std::endl;
    fout3 << "<y>";
    for (int i = 0; i < y.size(); ++i)
    {
        fout3 << y[i];
        if (i < y.size() - 1)
        {
            fout3 << ",";
        }
    }
    fout3 << "</y>" << std::endl;
    fout3 << "<z>";
    for (int i = 0; i < z.size(); ++i)
    {
        fout3 << z[i];
        if (i < z.size() - 1)
        {
            fout3 << ",";
        }
    }
    fout3 << "</z>" << std::endl;
    fout3 << "</Ft>" << std::endl;
    fout3.close();

    // convert c++ code to python code
    std::ofstream fout4("F(t).py");
    fout4 << "import numpy as np" << std::endl;
    fout4 << "import matplotlib.pyplot as plt" << std::endl;
    fout4 << "t = np.arange(0, 2.5, 0.1)" << std::endl;
    fout4 << "x = np.cos(t)" << std::endl;
    fout4 << "y = np.sin(t)" << std::endl;
    fout4 << "z = 2 * t" << std::endl;
    fout4 << "plt.plot(t, x, label='x')" << std::endl;
    fout4 << "plt.plot(t, y, label='y')" << std::endl;
    fout4 << "plt.plot(t, z, label='z')" << std::endl;
    fout4 << "plt.legend()" << std::endl;
    fout4 << "plt.show()" << std::endl;
    fout4.close();

    // convert c++ code to javascript code
    std::ofstream fout5("F(t).js");
    fout5 << "var t = [];" << std::endl;
    fout5 << "var x = [];" << std::endl;
    fout5 << "var y = [];" << std::endl;
    fout5 << "var z = [];" << std::endl;
    fout5 << "for (var i = 0; i < 25; ++i) {" << std::endl;
    fout5 << "    t.push(i / 10);" << std::endl;
    fout5 << "    x.push(Math.cos(t[i]));" << std::endl;
    fout5 << "    y.push(Math.sin(t[i]));" << std::endl;
    fout5 << "    z.push(2 * t[i]);" << std::endl;
    fout5 << "}" << std::endl;
    fout5 << "var trace1 = {" << std::endl;
    fout5 << "    x: t," << std::endl;
    fout5 << "    y: x," << std::endl;
    fout5 << "    name: 'x'," << std::endl;
    fout5 << "    type: 'scatter'" << std::endl;
    fout5 << "};" << std::endl;
    fout5 << "var trace2 = {" << std::endl;
    fout5 << "    x: t," << std::endl;
    fout5 << "    y: y," << std::endl;
    fout5 << "    name: 'y'," << std::endl;
    fout5 << "    type: 'scatter'" << std::endl;
    fout5 << "};" << std::endl;
    fout5 << "var trace3 = {" << std::endl;
    fout5 << "    x: t," << std::endl;
    fout5 << "    y: z," << std::endl;
    fout5 << "    name: 'z'," << std::endl;
    fout5 << "    type: 'scatter'" << std::endl;
    fout5 << "};" << std::endl;
    fout5 << "var data = [trace1, trace2, trace3];" << std::endl;
    fout5 << "var layout = {" << std::endl;
    fout5 << "    title: 'F(t) = (cos(t), sin(t), 2t)'" << std::endl;
    fout5 << "};" << std::endl;
    fout5 << "Plotly.newPlot('myDiv', data, layout);" << std::endl;
    fout5.close();
}