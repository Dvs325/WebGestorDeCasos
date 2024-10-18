<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${usuario == null || usuario.idTipoUsuario != 1}">
        <%-- Redirigir al login si no es administrador --%>
        <script type="text/javascript">
            window.location.href = "${pageContext.request.contextPath}/login";
        </script>
    </c:when>
</c:choose>

<html lang="es">
<head>
    <title>Menu administrador</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<div class="container">
    <div class="row">
        <h3>Lista de usuarios</h3>

        <div class="row">
            <div class="col-md-12">
                <a type="button" class="btn btn-primary btn-md" href="${pageContext.request.contextPath}/administrador/usuarios/nuevo" style="margin-bottom:1%"> Nuevo usuario</a>

                <table class="table table-striped table-bordered table-hover" id="tabla">
                    <thead>
                    <tr>
                        <th>Codigo del usuario</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Telefono</th>
                        <th>DUI</th>
                        <th>Correo</th>
                        <th>Area funcional</th>
                        <th>Tipo de usuario</th>
                        <th>Jefe asignado</th>
                        <th>Contraseña</th>
                        <th>Operaciones</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listarUsuarios}" var="usuario">
                            <tr>
                                <td>${usuario.idUsuario}</td>
                                <td>${usuario.nombre}</td>
                                <td>${usuario.apellido}</td>
                                <td>${usuario.telefono}</td>
                                <td>${usuario.dui}</td>
                                <td>${usuario.correo}</td>
                                <td>${usuario.nombreAreaFuncional}</td>
                                <td>${usuario.nombreTipoUsuario}</td>
                                <td class="text-center">${usuario.nombreJefeUsuario != null ? usuario.nombreJefeUsuario : "Este usuario no tiene jefe asignado"}</td>
                                <td>${usuario.contrasenia}</td>
                                <td>
                                    <a href="javascript:void(0)" class="btn btn-primary" onclick="modificar('${usuario.idUsuario}', '${usuario.nombreAreaFuncional}')"> <span class="glyphicon glyphicon-edit"> </span></a>
                                    <a title="eliminar" class="btn btn-danger" onclick="eliminar('${usuario.idUsuario}')"><span class="glyphicon glyphicon-trash"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- Formulario reutilizable -->
<form id="usuarioForm" action="${pageContext.request.contextPath}/administrador/usuarios/editar" method="POST" style="display:none;">
    <input type="hidden" name="codigoUsuario" id="codigoUsuario">
    <input type="hidden" name="idAreaFuncional" id="idAreaFuncional">
</form>

<script>
    //Jquery Datatables para el filtro
    $(document).ready(function(){
        $('#tabla').DataTable();
    });

    function eliminar(id) {
        alertify.confirm("¿Realmente desea eliminar a este usuario?", function (e) {
            if (e) {
                location.href = "${pageContext.request.contextPath}/administrador/usuarios/eliminar/"+id;
            }
        })
    }

    function modificar(codigoUsuario, idAreaFuncional) {
        // Asigna el valor al campo oculto del formulario reutilizable
        document.getElementById('codigoUsuario').value = codigoUsuario;
        document.getElementById('idAreaFuncional').value = idAreaFuncional;
        // Envía el formulario
        document.getElementById('usuarioForm').submit();
    }


</script>
</body>
</html>
