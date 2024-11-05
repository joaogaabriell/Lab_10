package view;

import model.AssentoEvent;
import model.AssentoListener;

public class PainelCentral implements AssentoListener {

    @Override
    public void assentoAtualizado(AssentoEvent event) {
        System.out.println("Painel Central - Assento " + event.getNumeroAssento() +
                " agora está " + event.getStatus());
    }
}
