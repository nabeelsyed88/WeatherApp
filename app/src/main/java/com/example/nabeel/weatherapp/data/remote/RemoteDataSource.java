package com.example.nabeel.weatherapp.data.remote;

import com.example.nabeel.weatherapp.model.WeatherResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nabeel on 4/21/2018.
 */

public class RemoteDataSource {

    public static final String API_KEY = "54ed352c80745b2d282ebaa1fd75bc88";
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static Call<WeatherResult> getWeather(String zipCode) {
        Retrofit retrofit = create();
        RemoteService service = retrofit.create(RemoteService.class);
        return service.getWeather(zipCode,API_KEY);
    }

    public interface RemoteService {
        @GET("weather")
        Call<WeatherResult> getWeather(
                @Query("zip") String zipCode,
                @Query("apikey") String apiKey);
    }
}
