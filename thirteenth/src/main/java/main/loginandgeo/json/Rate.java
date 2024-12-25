package main.loginandgeo.json;




public class Rate {
    private String date;
    private SingleRate rates;


    public Rate(String date, SingleRate rates) {
        this.date = date;
        this.rates = rates;
    }

    public Rate() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SingleRate getRates() {
        return rates;
    }

    public void setRates(SingleRate rates) {
        this.rates = rates;
    }
}
