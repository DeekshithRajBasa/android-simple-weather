package com.stfalcon.sampleweather.models;

/**
 * Created by artem on 15.03.16.
 */
public class WeatherResponse {
    private int cod;
    private String base;
    private Weather main;
    private Sky []weather;

    public Sky[] getWeather() {
        return weather;
    }

    public void setWeather(Sky[] weather) {
        this.weather = weather;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Weather getMain() {
        return main;
    }

    public void setMain(Weather main) {
        this.main = main;
    }
}
