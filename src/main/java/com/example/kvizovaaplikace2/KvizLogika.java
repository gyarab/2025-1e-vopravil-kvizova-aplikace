package com.example.kvizovaaplikace2;

import java.util.ArrayList;

public class KvizLogika {
    private ArrayList<Otazka> seznam = new ArrayList<>();
    private int aktualniIndex = 0;
    private int skore = 0;

    public KvizLogika() {
        seznam.add(new Otazka("Co je Java?", new String[]{"Jídlo", "Auto", "Programovací jazyk"}, 2));
        seznam.add(new Otazka("Hlavní město ČR?", new String[]{"Praha", "Brno", "Plzeň"}, 0));
        seznam.add(new Otazka("Kolik je 10 / 2?", new String[]{"2", "5", "8"}, 1));
    }

    public Otazka getCurrent() {
        return (aktualniIndex < seznam.size()) ? seznam.get(aktualniIndex) : null;
    }

    public void odpovedet(int volba) {
        if (volba == seznam.get(aktualniIndex).indexSpravne) skore++;
        aktualniIndex++;
    }

    public boolean jeKonec() { return aktualniIndex >= seznam.size(); }
    public int getSkore() { return skore; }
    public int getPocet() { return seznam.size(); }
}