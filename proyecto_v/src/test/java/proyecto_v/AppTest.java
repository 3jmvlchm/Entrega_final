package proyecto_v;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class AppTest {

    @Test
    public void calculaTotalConPrecioFijoYCantidad() {
        Recibo recibo = PedidoService.crearRecibo(
                "manguera-industrial",
                "",
                "3",
                "1020304050",
                "Marcos Chica"
        );

        assertEquals("Manguera industrial", recibo.getProducto());
        assertEquals(3, recibo.getCantidad());
        assertEquals(new BigDecimal("150000.00"), recibo.getPrecioUnitario());
        assertEquals(new BigDecimal("450000.00"), recibo.getPrecio());
    }

    @Test
    public void rechazaCantidadMenorAUno() {
        try {
            PedidoService.crearRecibo("manguera-industrial", "", "0", "1020304050", "Marcos Chica");
        } catch (IllegalArgumentException e) {
            assertEquals("La cantidad debe ser mayor o igual a 1.", e.getMessage());
            return;
        }
        throw new AssertionError("La cantidad invalida no fue rechazada.");
    }
}
