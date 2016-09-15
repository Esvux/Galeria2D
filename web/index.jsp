<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Galería Lienzos2D</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="/assets/css/material-icon.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/materialize.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/galeria.css"/>">
    </head>
    <body>
        <div class="container">
            <f:view>
                <h:form>
                    <div class="row" style="margin-top: 1em;">
                        <div class="col m12 l6 hide-on-small-only center">
                            <h3 class="thin">LISTADO DE LIENZOS</h3>
                        </div>
                        <div class="col s12 hide-on-med-and-up center">
                            <h5 class="thin">LISTADO DE LIENZOS</h5>
                        </div>
                        <div class="col s12 m6 l3 input-field center">
                            <h:commandLink action="#{reportes.generar()}" styleClass="btn teal darken-3" value="Ordenar">
                                <i class="material-icons left">sort</i>
                            </h:commandLink>
                        </div>
                        <div class="col s12 m6 l3 input-field">
                            <h:selectOneMenu value="#{reportes.filtro}">
                                <f:selectItem itemValue="Recientes" itemLabel="Más recientes"></f:selectItem>
                                <f:selectItem itemValue="Clasicos" itemLabel="Más antiguos"></f:selectItem>
                                <f:selectItem itemValue="TituloABC" itemLabel="Por título ascendente"></f:selectItem>
                                <f:selectItem itemValue="TituloZYX" itemLabel="Por título descendente"></f:selectItem>
                            </h:selectOneMenu>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <h:dataTable value="#{reportes.lienzos}" var="lnz" rendered="#{not empty reportes.lienzos}">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText styleClass="light" value="TITULO"></h:outputText>
                                    </f:facet>
                                    <h:outputText value="#{lnz.titulo}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText styleClass="light" value="DESCRIPCION"></h:outputText>
                                    </f:facet>
                                    <h:outputText  styleClass="truncate" value="#{lnz.descripcion}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText styleClass="light" value="FECHA DE CREACION"></h:outputText>
                                    </f:facet>
                                    <h:outputText value="#{lnz.fechaConFormato}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText styleClass="light" value="DETALLE"></h:outputText>
                                    </f:facet>
                                    <h:commandLink action="#{reportes.detalle()}" 
                                                   styleClass="btn-floating teal darken-3" 
                                                   style="margin-left:1em;">
                                        <i class="material-icons left">crop_original</i>
                                        <f:param name="seleccionado" value="#{lnz.id}"/>
                                    </h:commandLink>
                                </h:column>
                            </h:dataTable>
                        </div>
                    </div>
                </h:form>
            </f:view>
        </div>
        <script src="<c:url value="/assets/js/jquery-2.1.1.min.js" />"></script>
        <script src="<c:url value="assets/js/materialize.min.js"/>"></script>
        <script>
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>
    </body>
</html>