package com.example.basurainfoclima.model;

public class InfoClima {
    private String ciudad;
    private double temperatura;
    private double humedad;

    public InfoClima() {
    }

    public InfoClima(String ciudad, double temperatura, double humedad) {
        this.ciudad = ciudad;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }
}
