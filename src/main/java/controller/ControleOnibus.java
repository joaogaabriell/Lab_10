package controller;

import model.Onibus;

public class ControleOnibus {
    private final Onibus onibus;

    public ControleOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public void reservarAssento(int numero) {
        onibus.atualizarAssento(numero, "Reservado");
    }

    public void comprarAssento(int numero) {
        onibus.atualizarAssento(numero, "Indisponível");
    }
}
