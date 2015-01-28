cmake_minimum_required(VERSION 2.8)
project("WeatherInfoRPI")
include_directories(include)
add_executable(WeatherInfoRPI src/Main.cpp src/Weather.cpp include/Weather.h)
