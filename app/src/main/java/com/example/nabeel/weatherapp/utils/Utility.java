package com.example.nabeel.weatherapp.utils;

/**
 * Created by Nabeel on 4/21/2018.
 */

public class Utility {

    public static double KelvinToFarenheit(double kelvin) {
        if(kelvin<0)throw new IllegalArgumentException("Cannot have negative Kelvins");
        double farenheit = 1.8*(kelvin-273)+32;
        return farenheit;
    }

}
