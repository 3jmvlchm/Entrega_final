<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="proyecto_v.Recibo" %>
<%@ page import="proyecto_v.ReciboDao" %>

<%!
    private String safe(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
%>

<%
    String action = request.getParameter("action");
    if (action == null || action.isEmpty()) {
        action = "registro";
    }

    String contextPath = request.getContextPath();
    ReciboDao reciboDao = new ReciboDao();
    List<Recibo> recibos = reciboDao.obtenerTodos();
    NumberFormat moneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Agroinsumos G.A.O. - Registro de pedidos</title>
        <link rel="icon" type="image/x-icon" href="<%= contextPath %>/assets/logosinfondo.png" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="<%= contextPath %>/css/styles.css" rel="stylesheet" />
        <style>
            .app-hero { background: linear-gradient(135deg, #12372a 0%, #1f7a4d 55%, #e6b325 100%); }
            .receipt-table th { white-space: nowrap; }
            .nav-pills .nav-link { border: 1px solid #dee2e6; }
            .nav-pills .nav-link.active { border-color: transparent; }
        </style>
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand fw-bold" href="<%= contextPath %>/index.jsp">Agroinsumos G.A.O.</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Abrir navegacion">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item"><a class="nav-link active" href="<%= contextPath %>/index.jsp">Registro de pedidos</a></li>
                            <li class="nav-item"><a class="nav-link" href="<%= contextPath %>/portfolio-overview.html">Portafolio</a></li>
                            <li class="nav-item"><a class="nav-link" href="<%= contextPath %>/about.html">Acerca de nuestra empresa</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

            <header class="app-hero py-5">
                <div class="container px-5">
                    <div class="row gx-5 align-items-center justify-content-between">
                        <div class="col-lg-7">
                            <h1 class="display-5 fw-bolder text-white mb-3">Agroinsumos G.A.O.</h1>
                            <p class="lead fw-normal text-white-50 mb-4">Gestión local de recibos para pedidos de productos industriales y agrícolas.</p>
                        </div>
                        <div class="col-lg-4 d-none d-lg-block">
                            <img class="img-fluid rounded-3 shadow" src="https://www.agroinsumosgao.com/wp-content/uploads/2024/12/WhatsApp-Image-2024-12-27-at-10.20.01-AM.jpeg" alt="Productos de Agroinsumos G.A.O." />
                        </div>
                    </div>
                </div>
            </header>

            <section class="py-5" id="recibos">
                <div class="container px-5">
                    <div class="text-center mb-4">
                        <h2 class="fw-bolder">recibos</h2>
                        <p class="lead fw-normal text-muted mb-0">Registrar, modificar y eliminar pedidos.</p>
                    </div>

                    <ul class="nav nav-pills justify-content-center gap-2 mb-4">
                        <li class="nav-item">
                            <a class="nav-link <%= action.equals("registro") ? "active" : "" %>" href="<%= contextPath %>/index.jsp?action=registro#recibos"><i class="bi bi-plus-circle me-1"></i>Registrar</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link <%= action.equals("actualizar") ? "active" : "" %>" href="<%= contextPath %>/index.jsp?action=actualizar#recibos"><i class="bi bi-pencil-square me-1"></i>Modificar</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link <%= action.equals("eliminar") ? "active" : "" %>" href="<%= contextPath %>/index.jsp?action=eliminar#recibos"><i class="bi bi-trash me-1"></i>Eliminar</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link <%= action.equals("mostrar") ? "active" : "" %>" href="<%= contextPath %>/index.jsp?action=mostrar#listado"><i class="bi bi-table me-1"></i>Mostrar</a>
                        </li>
                    </ul>

                    <% if (action.equals("registro")) { %>
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8">
                            <div class="bg-light rounded-3 py-5 px-4 px-md-5">
                                <form action="<%= contextPath %>/registrar" method="POST">
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                            <label for="regProducto" class="form-label">Producto pedido</label>
                                            <input type="text" class="form-control" id="regProducto" name="Producto" placeholder="Manguera industrial" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="regPrecio" class="form-label">Precio</label>
                                            <input type="number" class="form-control" id="regPrecio" name="Precio" min="0" step="0.01" placeholder="150000" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="regDocumento" class="form-label">Documento</label>
                                            <input type="text" class="form-control" id="regDocumento" name="Documento" placeholder="1020304050" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="regNombre" class="form-label">Nombre solicitante</label>
                                            <input type="text" class="form-control" id="regNombre" name="Nombre" placeholder="Marcos Chica" required>
                                        </div>
                                        <div class="col-12 d-grid d-sm-flex justify-content-sm-end mt-4">
                                            <button type="submit" class="btn btn-primary btn-lg px-4"><i class="bi bi-save me-2"></i>Guardar recibo</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>

                    <% if (action.equals("actualizar")) { %>
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8">
                            <div class="bg-light rounded-3 py-5 px-4 px-md-5">
                                <form action="<%= contextPath %>/actualizar" method="POST">
                                    <div class="row g-3">
                                        <div class="col-12">
                                            <label for="modId" class="form-label">Recibo a modificar</label>
                                            <select class="form-select" id="modId" name="reciboId" required>
                                                <option value="" disabled selected>Selecciona un recibo</option>
                                                <% for (Recibo r : recibos) { %>
                                                <option value="<%= r.getId() %>"
                                                        data-producto="<%= safe(r.getProducto()) %>"
                                                        data-precio="<%= r.getPrecio() %>"
                                                        data-documento="<%= safe(r.getDocumento()) %>"
                                                        data-nombre="<%= safe(r.getNombre()) %>">
                                                    #<%= r.getId() %> - <%= safe(r.getProducto()) %> - <%= safe(r.getNombre()) %>
                                                </option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="modProducto" class="form-label">Producto pedido</label>
                                            <input type="text" class="form-control" id="modProducto" name="Producto" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="modPrecio" class="form-label">Precio</label>
                                            <input type="number" class="form-control" id="modPrecio" name="Precio" min="0" step="0.01" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="modDocumento" class="form-label">Documento</label>
                                            <input type="text" class="form-control" id="modDocumento" name="Documento" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="modNombre" class="form-label">Nombre solicitante</label>
                                            <input type="text" class="form-control" id="modNombre" name="Nombre" required>
                                        </div>
                                        <div class="col-12 d-grid d-sm-flex justify-content-sm-end mt-4">
                                            <button type="submit" class="btn btn-warning btn-lg px-4"><i class="bi bi-pencil-square me-2"></i>Actualizar recibo</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>

                    <% if (action.equals("eliminar")) { %>
                    <div class="row gx-5 justify-content-center">
                        <div class="col-lg-8">
                            <div class="bg-light rounded-3 py-5 px-4 px-md-5">
                                <form action="<%= contextPath %>/eliminar" method="POST" onsubmit="return confirm('¿Eliminar este recibo definitivamente?');">
                                    <div class="row g-3">
                                        <div class="col-12">
                                            <label for="elimId" class="form-label">Recibo a eliminar</label>
                                            <select class="form-select" id="elimId" name="reciboId" required>
                                                <option value="" disabled selected>Selecciona un recibo</option>
                                                <% for (Recibo r : recibos) { %>
                                                <option value="<%= r.getId() %>">#<%= r.getId() %> - <%= safe(r.getProducto()) %> - <%= safe(r.getNombre()) %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="col-12 d-grid d-sm-flex justify-content-sm-end mt-4">
                                            <button type="submit" class="btn btn-danger btn-lg px-4"><i class="bi bi-trash me-2"></i>Eliminar recibo</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
            </section>

            <section class="py-5 bg-light" id="listado">
                <div class="container px-5">
                    <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-end gap-3 mb-4">
                        <div>
                            <h2 class="fw-bolder mb-1">Recibos registrados</h2>
                            <p class="text-muted mb-0">Total: <%= recibos.size() %></p>
                        </div>
                        <a class="btn btn-primary" href="<%= contextPath %>/index.jsp?action=registro#recibos"><i class="bi bi-plus-circle me-2"></i>Nuevo recibo</a>
                    </div>
                    <div class="table-responsive bg-white border rounded-3">
                        <table class="table table-hover align-middle mb-0 receipt-table">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Producto</th>
                                    <th>Precio</th>
                                    <th>Documento</th>
                                    <th>Nombre</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (recibos.isEmpty()) { %>
                                <tr>
                                    <td colspan="5" class="text-center text-muted py-4">No hay recibos registrados.</td>
                                </tr>
                                <% } else { %>
                                    <% for (Recibo r : recibos) { %>
                                    <tr>
                                        <td><span class="badge bg-dark">#<%= r.getId() %></span></td>
                                        <td class="fw-semibold"><%= safe(r.getProducto()) %></td>
                                        <td><%= moneda.format(r.getPrecio()) %></td>
                                        <td><%= safe(r.getDocumento()) %></td>
                                        <td><%= safe(r.getNombre()) %></td>
                                    </tr>
                                    <% } %>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </main>

        <footer class="bg-dark py-4 mt-auto">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    <div class="col-auto"><div class="small m-0 text-white">Copyright &copy; Agroinsumos Gao 2026. By Marcos Chica</div></div>
                </div>
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="<%= contextPath %>/js/scripts.js"></script>
        <script>
            const select = document.getElementById("modId");
            const producto = document.getElementById("modProducto");
            const precio = document.getElementById("modPrecio");
            const documento = document.getElementById("modDocumento");
            const nombre = document.getElementById("modNombre");

            if (select && producto && precio && documento && nombre) {
                select.addEventListener("change", () => {
                    const op = select.options[select.selectedIndex];
                    producto.value = op.dataset.producto || "";
                    precio.value = op.dataset.precio || "";
                    documento.value = op.dataset.documento || "";
                    nombre.value = op.dataset.nombre || "";
                });
            }
        </script>
    </body>
</html>
