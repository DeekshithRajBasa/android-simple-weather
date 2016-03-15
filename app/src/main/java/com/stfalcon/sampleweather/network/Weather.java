package com.stfalcon.sampleweather.network;

/**
 * Created by artem on 15.03.16.
 */
public class Weather {

    private long temp;
    private int pressure;
    private long humidity;
    private long temp_min;
    private long temp_max;

    public long getTemp() {
        return temp;
    }

    public void setTemp(long temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    public long getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(long temp_min) {
        this.temp_min = temp_min;
    }

    public long getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(long temp_max) {
        this.temp_max = temp_max;
    }
}
