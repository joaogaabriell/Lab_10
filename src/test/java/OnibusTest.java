import model.AssentoEvent;
import model.Onibus;
import model.AssentoListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OnibusTest {

    private Onibus onibus;

    @BeforeEach
    void setUp() {
        onibus = new Onibus(10); // Inicializa um ônibus com 10 assentos
    }

    @Test
    void testInicializacaoAssentos() {
        for (int i = 0; i < 10; i++) {
            assertEquals("Disponível", onibus.getStatusAssento(i),
                    "Todos os assentos devem ser iniciados como 'Disponível'");
        }
    }

    @Test
    void testAdicionarRemoverListener() {
        AssentoListener listenerMock = mock(AssentoListener.class);


        onibus.adicionarListener(listenerMock);
        onibus.atualizarAssento(0, "Reservado");


        verify(listenerMock, times(1)).assentoAtualizado(any(AssentoEvent.class));


        onibus.removerListener(listenerMock);
        onibus.atualizarAssento(1, "Indisponível");

        // Verifica que o listener não foi notificado após a remoção
        verify(listenerMock, times(1)).assentoAtualizado(any(AssentoEvent.class));
    }

    @Test
    void testAtualizarAssentoComNotificacao() {
        AssentoListener listenerMock = mock(AssentoListener.class);
        onibus.adicionarListener(listenerMock);

        int numeroAssento = 2;
        String status = "Reservado";
        onibus.atualizarAssento(numeroAssento, status);


        verify(listenerMock).assentoAtualizado(argThat(event ->
                event.getNumeroAssento() == numeroAssento && event.getStatus().equals(status)
        ));
    }
}
