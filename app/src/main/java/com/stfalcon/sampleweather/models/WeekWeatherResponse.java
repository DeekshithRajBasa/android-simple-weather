package com.stfalcon.sampleweather.models;

import java.util.ArrayList;

/**
 * Created by artem on 16.03.16.
 */
public class WeekWeatherResponse {

    private String cod;
    private ArrayList<DayOfWeekWeather> list;

    public ArrayList<DayOfWeekWeather> getList() {
        return list;
    }

    public void setList(ArrayList<DayOfWeekWeather> list) {
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
