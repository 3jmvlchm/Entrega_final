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
import proyecto_v.ReciboDao;

/**
 *
 * @author USER
 */
@WebServlet("/eliminar")
public class Eliminar extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("reciboId"));
            
            ReciboDao reciboDao = new ReciboDao();
            reciboDao.eliminar(id);
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect(request.getContextPath()
                    + "/index.jsp?action=mostrar&tipo=exito&mensaje="
                    + encode("Pedido eliminado correctamente.")
                    + "#listado");

    }

    private String encode(String value) {
        if (value == null || value.isEmpty()) {
            value = "No se pudo procesar el pedido.";
        }
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

}
