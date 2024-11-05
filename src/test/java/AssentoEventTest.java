import model.AssentoEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssentoEventTest {

    @Test
    void testAssentoEventCreation() {
        Object source = new Object();
        int numeroAssento = 5;
        String status = "Reservado";

        AssentoEvent event = new AssentoEvent(source, numeroAssento, status);

        assertEquals(source, event.getSource(), "A fonte do evento deve estar correta");
        assertEquals(numeroAssento, event.getNumeroAssento(),
                "O número do assento deve estar correto");
        assertEquals(status, event.getStatus(), "O status deve estar correto");
    }
}
