package com.stfalcon.sampleweather.models;

/**
 * Created by artem on 16.03.16.
 */
public class WeekWeatherResponse {

    private String cod;
    private DayOfWeekWeather []list;

    public DayOfWeekWeather[] getList() {
        return list;
    }

    public void setList(DayOfWeekWeather[] list) {
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
