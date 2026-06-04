package proyecto_v;

import jakarta.servlet.http.HttpServletRequest;

public final class ReciboFormulario {

    private ReciboFormulario() {
    }

    public static Recibo leer(HttpServletRequest request) {
        return PedidoService.crearRecibo(
                request.getParameter("CodigoProducto"),
                request.getParameter("Producto"),
                request.getParameter("Cantidad"),
                request.getParameter("Documento"),
                request.getParameter("Nombre")
        );
    }
}
