import model.AssentoEvent;
import view.PainelCentral;
import view.Quiosque;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PainelCentralQuiosqueTest {

    @Test
    void testPainelCentralRecebeNotificacao() {
        PainelCentral painelCentral = mock(PainelCentral.class);


        AssentoEvent event = new AssentoEvent(this, 3, "Reservado");
        painelCentral.assentoAtualizado(event);


        verify(painelCentral).assentoAtualizado(event);
    }

    @Test
    void testQuiosqueRecebeNotificacao() {
        Quiosque quiosque = mock(Quiosque.class);


        AssentoEvent event = new AssentoEvent(this, 7, "Indisponível");
        quiosque.assentoAtualizado(event);


        verify(quiosque).assentoAtualizado(event);
    }
}
