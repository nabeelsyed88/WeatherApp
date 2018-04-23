package com.example.nabeel.weatherapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nabeel.weatherapp.model.WeatherResult;
import com.example.nabeel.weatherapp.utils.Utility;

public class DetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WeatherResult weatherResult;
    public static final String TAG = "DetailsActivityTag";

    TextView tv_cloudCondition;
    TextView tv_cloudDescription;
    TextView tv_temp;
    TextView tv_windSpeed;
    TextView tv_cloudiness;
    TextView tv_pressure;
    TextView tv_humidity;
    TextView tv_sunrise;
    TextView tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tv_cloudCondition = findViewById(R.id.tv_condition);
        tv_cloudDescription = findViewById(R.id.tv_condition_detail);
        tv_temp = findViewById(R.id.tv_temp);
        tv_windSpeed = findViewById(R.id.tv_wind_speed);
        tv_cloudiness = findViewById(R.id.tv_cloudiness);
        tv_pressure = findViewById(R.id.tv_pressure);
        tv_humidity = findViewById(R.id.tv_humidity);
        tv_sunrise = findViewById(R.id.tv_sunrise);
        tv_city = findViewById(R.id.tv_city);

        weatherResult = (WeatherResult) getIntent().getSerializableExtra("WeatherResult");
        if(weatherResult!=null)
            updateUI();
    }

    private void updateUI() {
        //Get Data from weatherResult object
        String cloudCondition = weatherResult.getWeather().get(0).getMain();
        String cloudDescription = weatherResult.getWeather().get(0).getDescription();
        long temp = Math.round(Utility.KelvinToFarenheit(weatherResult.getMain().getTemp()));
        double windSpeed = weatherResult.getWind().getSpeed();
        String cloudiness = weatherResult.getWeather().get(0).getMain();
        int pressure = weatherResult.getMain().getPressure();
        int humidity = weatherResult.getMain().getHumidity();
        long dt = weatherResult.getDt();
        long sunrise = weatherResult.getSys().getSunrise();
        long sunset = weatherResult.getSys().getSunset();
        String ifSunrise = (dt >= sunrise && dt < sunset) ? "Sunrise":"Sunset";
        String city = weatherResult.getName();
        String country = weatherResult.getSys().getCountry();

        //Update Text Views
        tv_cloudCondition.setText(cloudCondition);
        tv_cloudDescription.setText(cloudDescription);
        tv_temp.setText(" "+temp+"°F");
        tv_windSpeed.setText(windSpeed+" m/s");
        tv_cloudiness.setText(cloudiness);
        tv_pressure.setText(pressure+" hpa");
        tv_humidity.setText(humidity+"%");
        tv_sunrise.setText(ifSunrise);
        tv_city.setText("\n\nMeasured: "+city+", "+country);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
