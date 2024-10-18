<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Verificar si el usuario está en la sesión y si el tipo de usuario es diferente de 1 --%>
<c:choose>
    <c:when test="${usuario == null || usuario.idTipoUsuario != 1}">
        <%-- Redirigir al login si no es administrador --%>
        <script type="text/javascript">
            window.location.href = "${pageContext.request.contextPath}/login";
        </script>
    </c:when>
</c:choose>

<html>
<head lang="es">
    <title>Menu administrador</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
    <header>
        <jsp:include page="menu.jsp"/>
    </header>

    <div class="container">
        <div class="row">
            <h2 class="text-center">Menu Administrativo</h2>
        </div>

        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="glyphicon glyphicon-user glyphicon-size"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge" >${totalusuarios}</div>
                                <div>Gestor de usuarios</div>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/administrador/usuarios">
                        <div class="panel-footer">
                            <span class="pull-left">Ver usuarios</span>
                            <span class="pull-right"><span class="glyphicon glyphicon-chevron-right"></span></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="glyphicon glyphicon-folder-open glyphicon-size"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge" >${totalareas}</div>
                                <div>Gestor de departamentos</div>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/administrador/areas">
                        <div class="panel-footer">
                            <span class="pull-left">Ver departamentos</span>
                            <span class="pull-right"><span class="glyphicon glyphicon-chevron-right"></span></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
