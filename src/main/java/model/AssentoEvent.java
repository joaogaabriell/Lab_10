package model;

import java.util.EventObject;

public class AssentoEvent extends EventObject {
    private int numeroAssento;
    private String status;

    public AssentoEvent(Object source, int numeroAssento, String status) {
        super(source);
        this.numeroAssento = numeroAssento;
        this.status = status;
    }

    public int getNumeroAssento() {
        return numeroAssento;
    }

    public String getStatus() {
        return status;
    }
}
