#ifndef WEATHER_H
#define WEATHER_H


class Weather
{
    public:
        Weather();
        virtual ~Weather();
        float getTemperature();
    protected:
    private:
     float m_temperature;
};

#endif // WEATHER_H
