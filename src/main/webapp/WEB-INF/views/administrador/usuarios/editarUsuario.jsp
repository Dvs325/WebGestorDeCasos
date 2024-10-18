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
    <title>Editar usuario</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<div class="container">

    <div class="row">
        <h3 class="mb-4">Editar usuario <i class="glyphicon glyphicon-user"></i></h3>
    </div>

    <div class="col-md-7">
        <form role="form" action="${pageContext.request.contextPath}/administrador/usuarios/actualizar" method="POST">
            <div class="form-group">
                <label for="codigoUsuario">Código del usuario <i class="glyphicon glyphicon-barcode"></i>:</label>
                <input type="text" class="form-control" name="idUsuario"  id="codigoUsuario" value="${usuariomod.idUsuario}" readonly>
            </div>

            <div class="form-group">
                <label for="nombreUsuario">Nombre del usuario <i class="glyphicon glyphicon-user"></i>:</label>
                <input type="text" class="form-control" name="nombre"  id="nombreUsuario" value="${usuariomod.nombre}" placeholder="Ingresa el nombre del usuario">
            </div>

            <div class="form-group">
                <label for="apellidoUsuario">Apellido del usuario <i class="glyphicon glyphicon-user"></i>:</label>
                <input type="text" class="form-control" name="apellido"  id="apellidoUsuario" value="${usuariomod.apellido}" placeholder="Ingresa el apellido del usuario">
            </div>

            <div class="form-group">
                <label for="telefonoUsuario">Teléfono del usuario <i class="glyphicon glyphicon-phone"></i>:</label>
                <input type="text" class="form-control" name="telefono"  id="telefonoUsuario" value="${usuariomod.telefono}" placeholder="Ingresa el teléfono del usuario">
            </div>

            <div class="form-group">
                <label for="correoUsuario">Correo del usuario <i class="glyphicon glyphicon-envelope"></i>:</label>
                <input type="email" class="form-control" name="correo"  id="correoUsuario" value="${usuariomod.correo}" placeholder="Ingresa el correo del usuario">
            </div>

            <div class="form-group">
                <label for="duiUsuario">DUI <i class="glyphicon glyphicon-credit-card"></i>:</label>
                <input type="text" class="form-control" name="dui"  id="duiUsuario" value="${usuariomod.dui}" placeholder="Ingresa el DUI del usuario">
            </div>

            <div class="form-group">
                <label for="contraseniaUsuario">Contraseña del usuario <i class="glyphicon glyphicon-lock"></i>:</label>
                <input type="password" class="form-control" name="contrasenia"  id="contraseniaUsuario" value="${usuariomod.contrasenia}" placeholder="Ingresa la contraseña del usuario">
            </div>

            <div class="form-group">
                <label for="areaFuncional">Área funcional <i class="glyphicon glyphicon-briefcase"></i>:</label>
                <select name="idAreaFuncional" id="areaFuncional" class="form-control" >
                    <c:forEach items="${areaFuncional}" var="area">
                        <option type="text" value="${area.idArea}" <c:if test="${area.idArea == usuariomod.idAreaFuncional}">selected</c:if> >${area.area}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="tipoUsuario">Tipo de usuario <i class="glyphicon glyphicon-user"></i>:</label>
                <select name="idTipoUsuario" id="tipoUsuario" class="form-control" >
                    <c:forEach items="${tipoUsuario}" var="tipousuario">
                        <option value="${tipousuario.idTipoUsuario}" <c:if test="${tipousuario.idTipoUsuario == usuariomod.idTipoUsuario}">selected</c:if> >${tipousuario.nombreTipoUsuario}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="jefeUsuario">Jefe <i class="glyphicon glyphicon-king"></i>:</label>
                <select name="idJefeUsuario" id="jefeUsuario" class="form-control">
                    <option type="text" value="${usuariomod.idJefeUsuario}" selected>${usuariomod.nombreJefeUsuario} ${usuariomod.idJefeUsuario}</option>
                </select>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-info mr-2">Guardar <i class="glyphicon glyphicon-floppy-disk"></i></button>
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/administrador/usuarios">Cancelar <i class="glyphicon glyphicon-remove"></i></a>
            </div>
        </form>
    </div>
</div>

</body>

<script>

    // Evento para cuando se cambia el área funcional
    $('#areaFuncional').on('change', function() {
        actualizarJefes();
    });

    // Evento para cuando se cambia el jefe de usuario
    $('#jefeUsuario').on('click', function() {
        actualizarJefes();
    });

    // Función que se ejecuta en ambos casos
    function actualizarJefes() {
        var AreaFuncional = $('#areaFuncional').val();
        var textAreaFuncional = $("#areaFuncional option:selected").text();
        var codigoUser = $('#codigoUsuario').val();

        // Verifica si el área funcional tiene un valor válido
        if (AreaFuncional !== "") {
            $.ajax({
                url: '${pageContext.request.contextPath}/api/jefes/' + textAreaFuncional, // URL según el controlador
                type: 'GET',
                dataType: "JSON",
                success: function(response) {
                    // Limpiar el select de jefes antes de agregar las nuevas opciones
                    $('#jefeUsuario').empty();
                    //$('#jefeUsuario').append('<option value="">Seleccione un jefe</option>');

                    $.each(response, function(index, jefe) {
                        $('#jefeUsuario').append('<option value="' + jefe.idUsuario + '">' + jefe.nombre + ' ' + jefe.apellido +' '+ jefe.idUsuario +'</option>');
                    });
                },
                error: function() {
                    alert("Error al obtener los jefes de área.");
                }
            });
        } else {
            $('#jefeUsuario').empty();
            $('#jefeUsuario').append('<option value="">Seleccione un jefe</option>');
        }
    }

</script>




</html>
