<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${usuario == null || usuario.idTipoUsuario != 2}">
        <%-- Redirigir al login si no es administrador --%>
        <script type="text/javascript">
            window.location.href = "${pageContext.request.contextPath}/login";
        </script>
    </c:when>
</c:choose>

<html>
<head lang="es">
    <title>Menú jefe de desarrollo</title>
    <jsp:include page="/cabecera.jsp"/>
</head>
<body>
    <header>
        <jsp:include page="menu.jsp"/>
    </header>

    <div class="container">
        <div class="row">
            <h2 class="text-center">Menu de jefe de desarollo</h2>
        </div>

        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="glyphicon glyphicon-list-alt fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${totalCasosArea}</div>
                                <div>Registro de casos</div>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/JefeDesarrolloController?op=registrocasos">
                        <div class="panel-footer">
                            <span class="pull-left">Ver casos</span>
                            <span class="pull-right"><i class="glyphicon glyphicon-chevron-circle-right"></i></span>
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
                                <i class="glyphicon glyphicon-tasks fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${casosPendientes}</div>
                                <div>Gestor de casos</div>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/JefeDesarrolloController?op=casospendientes">
                        <div class="panel-footer">
                            <span class="pull-left">Ver casos por aprobación</span>
                            <span class="pull-right"><i class="glyphicon glyphicon-chevron-circle-right"></i></span>
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
                                <i class="glyphicon glyphicon-tasks fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${totalCasosProbadores}</div>
                                <div>Gestor de casos a probadores</div>
                            </div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/JefeDesarrolloController?op=casosasignar">
                        <div class="panel-footer">
                            <span class="pull-left">Ver casos para asignar a un probador</span>
                            <span class="pull-right"><i class="glyphicon glyphicon-chevron-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>

        </div>

    </div>
</body>
</html>
