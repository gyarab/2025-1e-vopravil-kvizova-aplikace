package com.example.kvizovaaplikace2;

public class Otazka {
    String text;
    String[] moznosti;
    int indexSpravne;

    public Otazka(String text, String[] moznosti, int indexSpravne) {
        this.text = text;
        this.moznosti = moznosti;
        this.indexSpravne = indexSpravne;
    }
}