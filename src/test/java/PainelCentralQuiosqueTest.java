import model.AssentoEvent;
import view.PainelCentral;
import view.Quiosque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PainelCentralQuiosqueTest {

    private TestePainelCentral painelCentral;
    private TesteQuiosque quiosque;

    @BeforeEach
    void setUp() {
        painelCentral = new TestePainelCentral();
        quiosque = new TesteQuiosque();
    }

    @Test
    void testPainelCentralRecebeNotificacao() {
        // Simula um evento de atualização de assento
        AssentoEvent event = new AssentoEvent(this, 3, "Reservado");
        painelCentral.assentoAtualizado(event);

        // Verifica se a mensagem correta foi registrada
        String mensagemEsperada = "Painel Central - Assento 3 agora está Reservado";
        assertEquals(mensagemEsperada, painelCentral.ultimaMensagem,
                "O Painel Central deve receber a notificação correta");
    }

    @Test
    void testQuiosqueRecebeNotificacao() {

        AssentoEvent event = new AssentoEvent(this, 7, "Indisponível");
        quiosque.assentoAtualizado(event);


        String mensagemEsperada = "Quiosque - Assento 7 está Indisponível";
        assertEquals(mensagemEsperada, quiosque.ultimaMensagem,
                "O Quiosque deve receber a notificação correta");
    }


    private static class TestePainelCentral extends PainelCentral {
        String ultimaMensagem;

        @Override
        public void assentoAtualizado(AssentoEvent event) {
            ultimaMensagem = "Painel Central - Assento " + event.getNumeroAssento() +
                    " agora está " + event.getStatus();
        }
    }


    private static class TesteQuiosque extends Quiosque {
        String ultimaMensagem;

        @Override
        public void assentoAtualizado(AssentoEvent event) {
            ultimaMensagem = "Quiosque - Assento " + event.getNumeroAssento() +
                    " está " + event.getStatus();
        }
    }
}
