package com.example.kvizovaaplikace2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KvizAplikace extends Application {

    String[] poleOtazek = {
            "Jaké je hlavní město Francie?", "Kolik má týden dní?", "Která planeta je nejblíže Slunci?",
            "Jaký je chemický symbol pro vodu?", "Kolik je 9 × 8?", "Kdo napsal knihu „Babička“?",
            "Jaký je největší oceán na Zemi?", "Ve kterém roce začala druhá světová válka?",
            "Jak se jmenuje nejvyšší hora světa?", "Jaký plyn dýchají lidé pro přežití?"
    };

    String[][] poleMoznosti = {
            {"Lyon", "Paříž", "Marseille"},
            {"5", "6", "7"},
            {"Merkur", "Venuše", "Mars"},
            {"CO2", "H2O", "O2"},
            {"64", "81", "72"},
            {"Božena Němcová", "Karel Čapek", "Jan Neruda"},
            {"Tichý oceán", "Atlantský oceán", "Indický oceán"},
            {"1945", "1939", "1914"},
            {"K2", "Sněžka", "Mount Everest"},
            {"Kyslík", "Dusík", "Vodík"}
    };

    int[] spravneIndexy = {1, 2, 0, 1, 2, 0, 0, 1, 2, 0};

    int cisloOtazky = 0;
    int pocetBodu = 0;
    boolean zobrazenVysledek = false;
    String historieKvizu = "";

    Label textOtazky = new Label();
    VBox oblastMoznosti = new VBox(10);
    ToggleGroup skupinaPrepinacu = new ToggleGroup();
    Button tlacitkoAkce = new Button("Potvrdit");


    public void start(Stage okno) {
        VBox panel = new VBox(20);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-padding: 30;");

        textOtazky.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        tlacitkoAkce.setOnAction(e -> tlacitkoKliknuto());

        panel.getChildren().addAll(textOtazky, oblastMoznosti, tlacitkoAkce);
        zobrazDalsi();

        Scene scena = new Scene(panel, 500, 550);
        okno.setTitle("Kvíz");
        okno.setScene(scena);
        okno.show();
    }

    public void zobrazDalsi() {
        if (cisloOtazky < poleOtazek.length) {
            textOtazky.setText((cisloOtazky + 1) + ". " + poleOtazek[cisloOtazky]);
            oblastMoznosti.getChildren().clear();
            skupinaPrepinacu.getToggles().clear();

            for (int i = 0; i < 3; i++) {
                RadioButton rb = new RadioButton(poleMoznosti[cisloOtazky][i]);
                rb.setToggleGroup(skupinaPrepinacu);
                oblastMoznosti.getChildren().add(rb);
            }
        } else {
            konec();
        }
    }

    public void tlacitkoKliknuto() {
        if (zobrazenVysledek) {
            cisloOtazky++;
            zobrazenVysledek = false;
            tlacitkoAkce.setText("Potvrdit");
            zobrazDalsi();
            return;
        }

        RadioButton vybrano = (RadioButton) skupinaPrepinacu.getSelectedToggle();

        if (vybrano != null) {
            int indexVybraneho = oblastMoznosti.getChildren().indexOf(vybrano);
            int indexSpravneho = spravneIndexy[cisloOtazky];

            historieKvizu += (cisloOtazky + 1) + ". " + poleOtazek[cisloOtazky] + "\n";

            if (indexVybraneho == indexSpravneho) {
                vybrano.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                pocetBodu++;
                historieKvizu += "   ✓ Správně: " + vybrano.getText() + "\n\n";
            } else {
                vybrano.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                historieKvizu += "   ✗ Tvoje volba: " + vybrano.getText() + "\n";
                historieKvizu += "   → Správně bylo: " + poleMoznosti[cisloOtazky][indexSpravneho] + "\n\n";
            }

            tlacitkoAkce.setText("Pokračovat");
            zobrazenVysledek = true;
        }
    }

    public void konec() {
        textOtazky.setText("Výsledky");
        oblastMoznosti.getChildren().clear();

        double procenta = ((double) pocetBodu / poleOtazek.length) * 100;

        Label bodyLabel = new Label("Body: " + pocetBodu + " / " + poleOtazek.length);
        Label procentaLabel = new Label("Úspěšnost: " + procenta + "%");

        TextArea vypis = new TextArea(historieKvizu);
        vypis.setEditable(false);
        vypis.setPrefHeight(300);

        oblastMoznosti.getChildren().addAll(bodyLabel, procentaLabel, vypis);
        tlacitkoAkce.setVisible(false);
    }

    public static void main(String[] args) {
        launch();
    }
}