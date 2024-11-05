package view;

import model.AssentoEvent;
import model.AssentoListener;

public class Quiosque implements AssentoListener {

    @Override
    public void assentoAtualizado(AssentoEvent event) {
        System.out.println("Quiosque - Assento " + event.getNumeroAssento() +
                " está " + event.getStatus());
    }
}
