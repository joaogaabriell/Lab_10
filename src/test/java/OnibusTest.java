import model.AssentoEvent;
import model.Onibus;
import model.AssentoListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnibusTest {

    private Onibus onibus;
    private TesteAssentoListener listener;

    @BeforeEach
    void setUp() {
        onibus = new Onibus(10); // Inicializa um ônibus com 10 assentos
        listener = new TesteAssentoListener();
        onibus.adicionarListener(listener);
    }

    @Test
    void testInicializacaoAssentos() {
        for (int i = 0; i < 10; i++) {
            assertEquals("Disponível", onibus.getStatusAssento(i),
                    "Todos os assentos devem ser iniciados como 'Disponível'");
        }
    }

    @Test
    void testAtualizarAssentoComNotificacao() {
        int numeroAssento = 2;
        String status = "Reservado";

        // Atualiza o status de um assento
        onibus.atualizarAssento(numeroAssento, status);

        // Verifica se o listener foi notificado com o número de assento e o status corretos
        assertEquals(numeroAssento, listener.ultimoEvento.getNumeroAssento());
        assertEquals(status, listener.ultimoEvento.getStatus());
    }

    @Test
    void testAdicionarRemoverListener() {
        // Atualiza um assento e verifica se o listener foi notificado
        onibus.atualizarAssento(0, "Reservado");
        assertNotNull(listener.ultimoEvento, "Listener deveria ter sido notificado");

        // Remove o listener e atualiza outro assento
        onibus.removerListener(listener);
        listener.ultimoEvento = null;
        onibus.atualizarAssento(1, "Indisponível");

        // Verifica que o listener não foi notificado após a remoção
        assertNull(listener.ultimoEvento, "Listener não deveria ter sido notificado após a remoção");
    }

    @Test
    void testAtualizarAssentoForaDosLimites() {
        int numeroAssento = 15; // Fora dos limites para um ônibus com 10 assentos

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            onibus.atualizarAssento(numeroAssento, "Reservado");
        });

        String mensagemEsperada = "Index: " + numeroAssento + ", Size: 10";
        String mensagemAtual = exception.getMessage();

        assertTrue(mensagemAtual.contains(mensagemEsperada),
                "A exceção deveria indicar que o número do assento está fora dos limites");
    }

    @Test
    void testAtualizarAssentoStatus() {
        int numeroAssento = 3;
        String status = "Indisponível";

        // Atualiza o status do assento e verifica
        onibus.atualizarAssento(numeroAssento, status);
        assertEquals(status, onibus.getStatusAssento(numeroAssento),
                "O status do assento deve ser atualizado corretamente");
    }

    // Classe auxiliar que implementa AssentoListener para registrar o último evento recebido
    private static class TesteAssentoListener implements AssentoListener {
        AssentoEvent ultimoEvento;

        @Override
        public void assentoAtualizado(AssentoEvent event) {
            this.ultimoEvento = event;
        }
    }
}
