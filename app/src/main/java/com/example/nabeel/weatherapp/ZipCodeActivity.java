package com.example.nabeel.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.nabeel.weatherapp.data.remote.RemoteDataSource;
import com.example.nabeel.weatherapp.model.WeatherResult;
import com.example.nabeel.weatherapp.utils.Utility;

public class ZipCodeActivity extends AppCompatActivity {

    EditText etZipCode;
    RemoteDataSource remoteDataSource;
    WeatherResult weatherResult;
    public static final String TAG = "ZipCodeActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code);

        etZipCode = findViewById(R.id.et_zipcode);
        remoteDataSource = new RemoteDataSource();
    }

    public void findZipCode(View view) {
        String zip = etZipCode.getText().toString();

        //If ZipCode isn't 5 characters, inform user and return
        if(zip == null || zip.length()!=5) {
            Toast.makeText(this, "Zipcode must be 5 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        remoteDataSource.getWeather(zip).enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                weatherResult = response.body();
                if(weatherResult!=null)
                    updateUI();
                else
                    Log.d(TAG, "onResponse: null weather result");
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    private void updateUI() {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("WeatherResult",weatherResult);
        startActivity(intent);
    }
}
