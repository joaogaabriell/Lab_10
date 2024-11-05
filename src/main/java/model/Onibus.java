package model;

import java.util.ArrayList;
import java.util.List;

public class Onibus {
    private List<String> assentos;
    private List<AssentoListener> listeners;

    public Onibus(int numeroDeAssentos) {
        assentos = new ArrayList<>(numeroDeAssentos);
        listeners = new ArrayList<>();
        for (int i = 0; i < numeroDeAssentos; i++) {
            assentos.add("Disponível");
        }
    }

    public void adicionarListener(AssentoListener listener) {
        listeners.add(listener);
    }

    public void removerListener(AssentoListener listener) {
        listeners.remove(listener);
    }

    public void atualizarAssento(int numero, String status) {
        if (numero < 0 || numero >= assentos.size()) {
            throw new IndexOutOfBoundsException("Index: " + numero + ", Size: " + assentos.size());
        }
        assentos.set(numero, status);
        notificarListeners(new AssentoEvent(this, numero, status));
    }

    private void notificarListeners(AssentoEvent event) {
        for (AssentoListener listener : listeners) {
            listener.assentoAtualizado(event);
        }
    }

    public String getStatusAssento(int numero) {
        return assentos.get(numero);
    }

    // Método adicionado
    public int getNumeroDeAssentos() {
        return assentos.size(); // Retorna o número total de assentos
    }
}
