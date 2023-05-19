#include <iostream>

#include <boost/filesystem.hpp>

namespace fs = boost::filesystem;

int main()
{
    fs::path p1("C:\\");

    // is directory
    if (fs::is_directory(p1))
    {
        std::cout << p1 << " is a directory containing:\n";
        // iterate over directory
        for (fs::directory_entry& x : fs::directory_iterator(p1))
        {
            std::cout << "    " << x.path() << '\n';
        }
    }

    return 0;
}
