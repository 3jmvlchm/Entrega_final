/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_v.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyecto_v.Recibo;
import proyecto_v.ReciboDao;
import proyecto_v.ReciboFormulario;

/**
 *
 * @author USER
 */
@WebServlet("/registrar")
public class Registrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        response.setContentType("text/html;charset=UTF-8");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            Recibo recibo = ReciboFormulario.leer(request);
            ReciboDao reciboDao = new ReciboDao();
            reciboDao.crear(recibo);
            response.sendRedirect(request.getContextPath()
                    + "/index.jsp?action=mostrar&tipo=exito&mensaje="
                    + encode("Pedido registrado correctamente.")
                    + "#listado");
        } catch (RuntimeException e) {
            response.sendRedirect(request.getContextPath()
                    + "/index.jsp?action=registro&tipo=error&mensaje="
                    + encode(e.getMessage())
                    + "#recibos");
        }

    }

    private String encode(String value) {
        if (value == null || value.isEmpty()) {
            value = "No se pudo procesar el pedido.";
        }
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

}
