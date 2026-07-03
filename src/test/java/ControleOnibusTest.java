import controller.ControleOnibus;
import model.AssentoEvent;
import model.Onibus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.PainelCentral;
import view.Quiosque;

import static org.junit.jupiter.api.Assertions.*;

class ControleOnibusTest {

    private Onibus onibus;
    private ControleOnibus controleOnibus;
    private PainelCentral painelCentral;
    private Quiosque quiosque;

    @BeforeEach
    void setUp() {
        // Inicializa um Onibus com 10 assentos e adiciona os listeners
        onibus = new Onibus(10);
        controleOnibus = new ControleOnibus(onibus);
        painelCentral = new PainelCentral();
        quiosque = new Quiosque();

        // Adiciona listeners para o PainelCentral e o Quiosque
        onibus.adicionarListener(painelCentral);
        onibus.adicionarListener(quiosque);
    }

    @Test
    void testReservarAssento() {
        int numeroAssento = 5;
        controleOnibus.reservarAssento(numeroAssento);

        // Verifica se o status do assento foi atualizado para "Reservado"
        assertEquals("Reservado", onibus.getStatusAssento(numeroAssento),
                "O status do assento deveria ser 'Reservado'");
    }

    @Test
    void testComprarAssento() {
        int numeroAssento = 3;
        controleOnibus.comprarAssento(numeroAssento);

        // Verifica se o status do assento foi atualizado para "Indisponível"
        assertEquals("Indisponível", onibus.getStatusAssento(numeroAssento),
                "O status do assento deveria ser 'Indisponível'");
    }

    @Test
    void testNotificarListeners() {
        ContadorListener contador = new ContadorListener();
        onibus.adicionarListener(contador);

        controleOnibus.reservarAssento(2);
        controleOnibus.comprarAssento(2);

        assertEquals(2, contador.notificacoes,
                "O listener deveria ter sido notificado uma vez por atualização");
        assertEquals("Indisponível", contador.ultimoStatus,
                "O último evento deveria refletir a compra do assento");
    }

    private static class ContadorListener implements model.AssentoListener {
        int notificacoes;
        String ultimoStatus;

        @Override
        public void assentoAtualizado(AssentoEvent event) {
            notificacoes++;
            ultimoStatus = event.getStatus();
        }
    }

    @Test
    void testAtualizarAssentoForaDosLimites() {
        int numeroAssento = 15; // Fora dos limites para um ônibus com 10 assentos
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            controleOnibus.reservarAssento(numeroAssento);
        });

        // Use o método getNumeroDeAssentos() para obter o tamanho atual
        String mensagemEsperada = "Index: " + numeroAssento + ", Size: " + onibus.getNumeroDeAssentos();
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada),
                "A exceção deveria indicar que o número do assento está fora dos limites");
    }




    @Test
    void testRemoverListener() {
        // Remove o PainelCentral e verifica se ele não recebe mais notificações
        onibus.removerListener(painelCentral);

        int numeroAssento = 1;
        controleOnibus.comprarAssento(numeroAssento);

        // Não temos um sistema de verificação explícito, mas em um cenário real,
        // poderíamos usar mocks para validar que apenas o Quiosque recebe o evento
        assertEquals("Indisponível", onibus.getStatusAssento(numeroAssento),
                "O status do assento deveria ser atualizado mesmo sem o listener PainelCentral");
    }
}
