#pragma once

#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <functional>
#include <tuple>
#include <iomanip>
#include <fstream>

void runExample2()
{
    auto x = [](int t) -> double { return t; };
    auto y = [](int t) -> double { return t * t; };
    auto z = [](int t) -> double { return t * t * t; };

    auto position = [&](int t) {
        return std::make_tuple(x(t), y(t), z(t));
    };

    // differentiate x, y, z
    auto dx = [](int t) -> double { return 1; };
    auto dy = [](int t) -> double { return 2 * t; };
    auto dz = [](int t) -> double { return 3 * t * t; };

    // double differentiate x, y, z
    auto ddx = [](int t) -> double { return 0; };
    auto ddy = [](int t) -> double { return 2; };
    auto ddz = [](int t) -> double { return 6 * t; };


    // tangent vector
    auto tangent = [&](int t) {
        return std::make_tuple(dx(t), dy(t), dz(t));
    };

    // derivative of tangent vector
    auto dtangent = [&](int t) {
        return std::make_tuple(ddx(t), ddy(t), ddz(t));
    };

    // normal vector as cross product of position and tangent
    auto normal = [&](int t) {
        auto pos = position(t);
        auto tan = tangent(t);

        auto dx = std::get<0>(pos);
        auto dy = std::get<1>(pos);
        auto dz = std::get<2>(pos);
        
        auto dx_ = std::get<0>(tan);
        auto dy_ = std::get<1>(tan);
        auto dz_ = std::get<2>(tan);
        
        auto dx__ = dy * dz_ - dz * dy_;
        auto dy__ = dz * dx_ - dx * dz_;
        auto dz__ = dx * dy_ - dy * dx_;
        
        return std::make_tuple(dx__, dy__, dz__);
    };
    

    // binormal vector as cross product of tangent and normal
    auto binormal = [&](int t) {
        auto tan = tangent(t);
        auto n = normal(t);

        auto dx = std::get<0>(tan);
        auto dy = std::get<1>(tan);
        auto dz = std::get<2>(tan);
        
        auto dx_ = std::get<0>(n);
        auto dy_ = std::get<1>(n);
        auto dz_ = std::get<2>(n);
        
        auto dx__ = dy * dz_ - dz * dy_;
        auto dy__ = dz * dx_ - dx * dz_;
        auto dz__ = dx * dy_ - dy * dx_;
        
        return std::make_tuple(dx__, dy__, dz__);
    };

    // formula of radius of curvature of r(t) = (x(t), y(t), z(t))
    // r'(t) = (x'(t), y'(t), z'(t))
    // r''(t) = (x''(t), y''(t), z''(t))
    // r'''(t) = (x'''(t), y'''(t), z'''(t))
    // r''(t) x r'''(t) = (y''(t)z'''(t) - z''(t)y'''(t), z''(t)x'''(t) - x''(t)z'''(t), x''(t)y'''(t) - y''(t)x'''(t))
    // r'(t) x r''(t) = (y'(t)z''(t) - z'(t)y''(t), z'(t)x''(t) - x'(t)z''(t), x'(t)y''(t) - y'(t)x''(t))
    // |r'(t) x r''(t)| = sqrt((y'(t)z''(t) - z'(t)y''(t))^2 + (z'(t)x''(t) - x'(t)z''(t))^2 + (x'(t)y''(t) - y'(t)x''(t))^2)
    // |r'(t)| = sqrt(x'(t)^2 + y'(t)^2 + z'(t)^2)
    // |r'(t)|^3 = (x'(t)^2 + y'(t)^2 + z'(t)^2)^(3/2)
    // |r'(t) x r''(t)| / |r'(t)|^3 = sqrt((y'(t)z''(t) - z'(t)y''(t))^2 + (z'(t)x''(t) - x'(t)z''(t))^2 + (x'(t)y''(t) - y'(t)x''(t))^2) / (x'(t)^2 + y'(t)^2 + z'(t)^2)^(3/2)



    // radius of curvature at t using tangent and its derivative
    auto radius = [&](int t) {
        auto tan = tangent(t);
        auto dtan = dtangent(t);

        auto dx = std::get<0>(tan);
        auto dy = std::get<1>(tan);
        auto dz = std::get<2>(tan);
        
        auto dx_ = std::get<0>(dtan);
        auto dy_ = std::get<1>(dtan);
        auto dz_ = std::get<2>(dtan);
        
        auto dx__ = dy * dz_ - dz * dy_;
        auto dy__ = dz * dx_ - dx * dz_;
        auto dz__ = dx * dy_ - dy * dx_;

        auto num = dx * dx + dy * dy + dz * dz;
        auto den = dx__ * dx__ + dy__ * dy__ + dz__ * dz__;
        
        return std::pow(std::sqrt(num), 3) / std::pow(den, 0.5);
    };
    
    // position at t = 5
    auto p = position(5);
    std::cout << "p = (" << std::get<0>(p) << ", " << std::get<1>(p) << ", " << std::get<2>(p) << ")" << std::endl;

    // tangent at t = 5
    auto t = tangent(5);
    std::cout << "t = (" << std::get<0>(t) << ", " << std::get<1>(t) << ", " << std::get<2>(t) << ")" << std::endl;

    // derivative of tangent at t = 5
    auto dt = dtangent(5);
    std::cout << "dt = (" << std::get<0>(dt) << ", " << std::get<1>(dt) << ", " << std::get<2>(dt) << ")" << std::endl;

    // normal at t = 5
    auto n = normal(5);
    std::cout << "n = (" << std::get<0>(n) << ", " << std::get<1>(n) << ", " << std::get<2>(n) << ")" << std::endl;

    // binormal at t = 5
    auto b = binormal(5);
    std::cout << "b = (" << std::get<0>(b) << ", " << std::get<1>(b) << ", " << std::get<2>(b) << ")" << std::endl;

    // radius of curvature at t = 5
    auto r = radius(5);
    std::cout << "r = " << r << std::endl;
}