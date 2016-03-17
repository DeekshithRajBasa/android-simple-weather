package com.stfalcon.sampleweather.models;

import java.util.ArrayList;

/**
 * Created by artem on 16.03.16.
 */
public class DayOfWeekWeather {
    private float dp;
    private Temperature temp;
    private float pressure;
    private float humidity;
    private ArrayList<Sky> weather;

    public float getDp() {
        return dp;
    }

    public void setDp(float dp) {
        this.dp = dp;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public ArrayList<Sky> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Sky> weather) {
        this.weather = weather;
    }
}
