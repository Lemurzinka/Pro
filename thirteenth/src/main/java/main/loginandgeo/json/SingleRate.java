package main.loginandgeo.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleRate {

    @JsonProperty("UAH")
    private double uah;

    public SingleRate(double uah) {
        this.uah = uah;
    }

    public SingleRate() {
    }


    public double getUah() {
        return uah;
    }

    public void setUah(double uah) {
        this.uah = uah;
    }
}
