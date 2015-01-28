#include <iostream>
#include "Weather.h"

using namespace std;


int main(int argc, char** argv)
{
    cout << "Test 1 2 3" <<endl;

    // Test
    Weather weather;

    cout << "temp : " << weather.getTemperature() << endl;

    // TODO : open a socket
    // TODO : collect info from the sensors
    // ...

    return 0;
}
