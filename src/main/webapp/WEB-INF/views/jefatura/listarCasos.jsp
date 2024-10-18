<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Verificar si el usuario está en la sesión y si el tipo de usuario es diferente de 3 --%>
<c:choose>
    <c:when test="${usuario == null || usuario.idTipoUsuario != 3}">
        <%-- Redirigir al login si no es administrador --%>
        <script type="text/javascript">
            window.location.href = "${pageContext.request.contextPath}/login";
        </script>
    </c:when>
</c:choose>


<html>
<head lang="es">
    <title>Lista de casos</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
<header>
    <jsp:include page="menu.jsp"/>
</header>

<div class="container">
    <div class="row">
        <h3 class="text-center">Lista de casos aperturados.</h3>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form role="form" action="${pageContext.request.contextPath}/jefatura/insertarCaso" method="post">
                <div class="form-group">
                    <label for="descripcion">Registro de un caso:</label>
                    <div class="input-group">
                        <textarea class="form-control" name="descripcion" id="descripcion" placeholder="Ingrese una descripción" required></textarea>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                        <input type="hidden" name="idUsuario"  id="codigoUsuario" value="${usuario.idUsuario}">
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-info">Guardar</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="tabla">
                <thead class="thead-dark">
                    <tr>
                        <th class="text-center">Código del caso</th>
                        <th class="text-center">Fecha de registro</th>
                        <th class="text-center">Detalles</th>
                        <th class="text-center">Estado</th>
                        <th class="text-center">Info del rechazo</th>
                        <th class="text-center">Operaciones</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${listarCasos}" var="casos">
                    <tr>
                        <td class="text-center">${casos.idCasos}</td>
                        <td class="text-center">${casos.fechaRegistro}</td>
                        <td class="text-center">${casos.detalles}</td>
                        <td class="text-center estado-caso" data-id="${casos.idEstadoCaso}">${casos.idEstadoCaso}</td>
                        <td class="text-center">${casos.infoRechazo != null ? casos.infoRechazo: "No hay información"}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${casos.idEstadoCaso == '2'}"><!-- solicitud rechazada-->
                                    <a title="Reenviar" class="btn btn-primary btn-sm" href="javascript:detallesCasos('${casos.idCasos}')">
                                        <i class="glyphicon glyphicon-repeat"></i> Reenviar
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <p>Este caso no puede ser enviado a revisión...</p>
                                </c:otherwise>
                            </c:choose>
                            <a class="btn btn-primary" title="Ver Bitácora" href="${pageContext.request.contextPath}/JefaturaController?op=verbitacoras&id=${casos.idCasos}">
                                <i class="glyphicon glyphicon-list-alt"></i> Ver Registro
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal" id="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title">Re envio de casos</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form role="form" action="${contextPath}/JefaturaController?op=reenviocaso" method="post">
                        <div class="form-group row">
                            <label for="idCaso" class="col-sm-4 col-form-label">Id caso:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="idCaso" id="idCaso" readonly>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="descripcionCaso" class="col-sm-4 col-form-label">Descripción:</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" name="descripcionCaso" id="descripcionCaso" required></textarea>
                            </div>
                        </div>

                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-info">Editar</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>


<script>

    $(document).ready(function(){
        $('#tabla').DataTable();
    });

    function detallesCasos(id){
        $.ajax({
            url: "${pageContext.request.contextPath}/JefaturaController?op=detallescaso&id=" + id,
            type: "GET",
            dataType: "JSON",
            success: function (data){
                $('#idCaso').val(data.idCaso);
                $('#descripcionCaso').val(data.descripcion);
                $('#modal').modal('show');
            }
        });
    }


        function obtenerEstadoCaso(idEstadoCaso) {
        switch (idEstadoCaso) {
        case 1:
        return "En espera de respuesta";
        case 2:
        return "Solicitud rechazada";
        case 3:
        return "En desarrollo";
        case 4:
        return "Esperando aprobación de área solicitante";
        case 5:
        return "Vencido";
        case 6:
        return "Devuelto con observaciones";
        case 7:
        return "Esperando aprobación del área solicitante";
        case 8:
        return "Vencido";
        case 9:
        return "Finalizado";
        default:
        return "Estado desconocido";
    }
    }

        // Esta función actualiza los estados en la tabla después de cargar la página
        function actualizarEstados() {
        var estados = document.querySelectorAll(".estado-caso");
        estados.forEach(function (estado) {
        var idEstado = estado.getAttribute("data-id");
        estado.innerText = obtenerEstadoCaso(parseInt(idEstado));
    });
    }

        // Ejecuta la función cuando la página haya cargado
        window.onload = function() {
        actualizarEstados();
    };



<c:if test="${not empty error}">
        alertify.error('${error}');
        <c:set var="fracaso" value="" scope="session" />
    </c:if>

    <c:if test="${not empty exito}">
        alertify.success('${exito}');
        <c:set var="exito" value="" scope="session" />
    </c:if>
</script>
</body>
</html>