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
    <title>Nuevo usuario</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
<jsp:include page="../menu.jsp"/>

<div class="container">
    <div class="row">
        <h3 class="mb-4">Nuevo usuario <i class="glyphicon glyphicon-user"></i></h3>
    </div>

    <div class="col-md-7">
        <form role="form" action="${pageContext.request.contextPath}/administrador/usuarios/crear" method="POST">
            <div class="form-group">
                <label for="idUsuario">Código del usuario <i class="glyphicon glyphicon-barcode"></i>:</label>
                <input type="text" class="form-control" name="idUsuario"  id="idUsuario" placeholder="Ingresa el código del usuario">
            </div>

            <div class="form-group">
                <label for="nombreUsuario">Nombre del usuario <i class="glyphicon glyphicon-user"></i>:</label>
                <input type="text" class="form-control" name="nombre"  id="nombreUsuario" placeholder="Ingresa el nombre del usuario">
            </div>

            <div class="form-group">
                <label for="apellidoUsuario">Apellido del usuario <i class="glyphicon glyphicon-user"></i>:</label>
                <input type="text" class="form-control" name="apellido"  id="apellidoUsuario" placeholder="Ingresa el apellido del usuario">
            </div>

            <div class="form-group">
                <label for="telefonoUsuario">Teléfono del usuario <i class="glyphicon glyphicon-phone"></i>:</label>
                <input type="text" class="form-control" name="telefono"  id="telefonoUsuario" placeholder="Ingresa el teléfono del usuario">
            </div>

            <div class="form-group">
                <label for="correoUsuario">Correo del usuario <i class="glyphicon glyphicon-envelope"></i>:</label>
                <input type="email" class="form-control" name="correo"  id="correoUsuario" placeholder="Ingresa el correo del usuario">
            </div>

            <div class="form-group">
                <label for="duiUsuario">DUI <i class="glyphicon glyphicon-credit-card"></i>:</label>
                <input type="text" class="form-control" name="dui"  id="duiUsuario" placeholder="Ingresa el DUI del usuario">
            </div>

            <div class="form-group">
                <label for="contraseniaUsuario">Contraseña del usuario <i class="glyphicon glyphicon-lock"></i>:</label>
                <input type="password" class="form-control" name="contrasenia"  id="contraseniaUsuario" placeholder="Ingresa la contraseña del usuario">
            </div>

            <div class="form-group">
                <label for="areaFuncional">Área funcional <i class="glyphicon glyphicon-briefcase"></i>:</label>
                <select name="idAreaFuncional" id="areaFuncional" class="form-control" >
                    <option type="text" value="" selected>Seleccione un Area</option>
                    <c:forEach items="${areaFuncional}" var="area">
                        <option type="text" value="${area.idArea}">${area.area}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="tipoUsuario">Tipo de usuario <i class="glyphicon glyphicon-user"></i>:</label>
                <select name="idTipoUsuario" id="tipoUsuario" class="form-control" >
                    <option type="text" value="" selected>Seleccione el Tipo de Usuario</option>
                    <c:forEach items="${tipoUsuario}" var="tipousuario">
                        <option value="${tipousuario.idTipoUsuario}" >${tipousuario.nombreTipoUsuario}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="jefeUsuario">Jefe <i class="glyphicon glyphicon-king"></i>:</label>
                <select name="idJefeUsuario" id="jefeUsuario" class="form-control">
                <option type="text" value="" selected>Seleccione un Jefe</option>
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
        $(document).ready(function() {
        // Evento cuando se cambia el valor del select de área funcional
        $('#areaFuncional').on('change', function() {
            var AreaFuncional = $(this).val();  // Obtiene el valor seleccionado
            var textAreaFuncional = $("#areaFuncional option:selected").text();  // Obtiene el texto seleccionado

            // Verifica si el área funcional tiene un valor válido (que no sea la opción "Seleccione un Área")
            if (AreaFuncional !== "") {
                // Si se ha seleccionado un área válida, realiza la petición AJAX para obtener los jefes de esa área
                $.ajax({
                    url: '${pageContext.request.contextPath}/api/jefes/' + textAreaFuncional,  // URL del controlador para obtener los jefes
                    type: 'GET',
                    dataType: "JSON",
                    success: function(response) {
                        // Limpiar el select de jefes antes de agregar las nuevas opciones
                        $('#jefeUsuario').empty();
                        //$('#jefeUsuario').append('<option value="">Seleccione un jefe</option>');

                        // Rellenar el select de jefes con los datos obtenidos del servidor
                        $.each(response, function(index, jefe) {
                            $('#jefeUsuario').append('<option value="' + jefe.idUsuario + '">' + jefe.nombre + ' ' + jefe.apellido +' '+ jefe.idUsuario +'</option>');
                        });
                    },
                    error: function() {
                        alert("Error al obtener los jefes de área.");
                    }
                });
            } else {
                // Si el área funcional seleccionada no es válida, resetea el select de jefes
                $('#jefeUsuario').empty();
                $('#jefeUsuario').append('<option value="">Seleccione un jefe</option>');
            }
        });
    });
</script>



</html>
