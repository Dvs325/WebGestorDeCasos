<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Iniciar sesion</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
<div class="container">
    <div class="row justify-content-center" style="margin-right: -20%; margin-left: 25%; margin-top: 5%;">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-header bg-primary text-white">
                    <h3 class="text-center"><span class="glyphicon glyphicon-log-in"></span> Iniciar sesión</h3>
                </div>
                <div class="card-body">
                    <c:if test="${not empty listaErrores}">
                        <div class="alert alert-danger" role="alert">
                            <ul class="mb-0">
                                <c:forEach var="error" items="${listaErrores}">
                                    <li><span class="glyphicon glyphicon-exclamation-sign"></span> ${error}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/login/verificarUsuario" method="post">
                        <div class="form-group">
                            <label for="codigoUsuario"><span class="glyphicon glyphicon-user"></span> Usuario:</label>
                            <input type="text" class="form-control" id="codigoUsuario" name="codigoUsuario" placeholder="Ingrese su código" required >
                        </div>
                        <div class="form-group">
                            <label for="contraseniaUsuario"><span class="glyphicon glyphicon-lock"></span> Contraseña:</label>
                            <input type="password" class="form-control" id="contraseniaUsuario" name="contraseniaUsuario" placeholder="Ingrese su contraseña" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block" value="Verificar" ><span class="glyphicon glyphicon-log-in"></span> Iniciar sesión</button>
                    </form>
                    <c:if test="${not empty resultado}">
                        <p>Tipo de Usuario: ${resultado}</p>
                    </c:if>

                    <c:if test="${not empty error}">
                        <p style="color: red;">${error}</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    <c:if test="${not empty resultado} " >
    alertify.success('Login correcto');
    <c:set var="exito" value="" scope="session" />
    </c:if>
    <c:if test="${not empty error}">
    alertify.error('Login incorrecto');
    <c:set var="exito" value="" scope="session" />
    </c:if>
</script>


</body>
</html>