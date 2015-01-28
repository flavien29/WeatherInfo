#include "Weather.h"

Weather::Weather()
{
    m_temperature = 10;
}

Weather::~Weather()
{
    //dtor
}


float Weather::getTemperature()
{
    return m_temperature;
}
