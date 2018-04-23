package com.example.nabeel.weatherapp;

import com.example.nabeel.weatherapp.utils.Utility;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nabeel on 4/23/2018.
 */

public class UnitTest {

    @Test
    public void TempConversionIsCorrect() throws Exception {
        //273 Kelvins should be 32 Farenheit
        assertEquals(Utility.KelvinToFarenheit(273.0),32.0,0.1);
    }
}
