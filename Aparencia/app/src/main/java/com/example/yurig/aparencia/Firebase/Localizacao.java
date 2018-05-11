package com.example.yurig.aparencia.Firebase;

public class Localizacao{
    public double latitude;
    public double longitude;

    public Localizacao(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Localizacao(){
        this.latitude = 0.00;
        this.longitude = 0.00;
    }
}
