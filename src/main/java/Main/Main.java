package Main;

import controller.ControleOnibus;
import model.Onibus;
import view.PainelCentral;
import view.Quiosque;

public class Main {
    public static void main(String[] args) {

        Onibus onibus = new Onibus(5);
        ControleOnibus controle = new ControleOnibus(onibus);

        PainelCentral painelCentral = new PainelCentral();
        Quiosque quiosque = new Quiosque();

        onibus.adicionarListener(painelCentral);
        onibus.adicionarListener(quiosque);

        controle.reservarAssento(2);
        controle.comprarAssento(2);
        controle.reservarAssento(3);
    }
}
