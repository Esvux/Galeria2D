<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <div class="col s12 m6">
                            <h3 class="thin">DETALLE LIENZO</h3>
                        </div>
                        <div class="col s12 m6">
                            <h:commandLink action="a_galeria" 
                                           styleClass="btn teal darken-3 right" 
                                           value="Regresar a la galeria"
                                           style="margin-top:1.8em;">
                                <i class="material-icons left">filter</i>
                            </h:commandLink>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12 l4">
                            <span class="light">TITULO:</span><br class="hide-on-small-and-down">
                            <h:outputText styleClass="thin" style="margin-left:1em;" value="#{lienzo.titulo}"/>
                        </div>
                        <div class="col s12 l4">
                            <span class="light">DESCRIPCION:</span><br class="hide-on-small-and-down">
                            <h:outputText styleClass="thin" style="margin-left:1em;" value="#{lienzo.descripcion}"/>
                        </div>
                        <div class="col s12 l4">
                            <span class="light">FECHA:</span><br class="hide-on-small-and-down">
                            <h:outputText styleClass="thin" style="margin-left:1em;" value="#{lienzo.fechaConFormato}"/>
                        </div>
                        <div class="col s12 card green lighten-4" style="margin: 2em 0;">
                            <div class="card-content center">
                                <img src="data:image/png;base64,${lienzo.imagen}" alt="Lienzo">
                            </div>
                        </div>
                    </div>
                </h:form>
            </f:view>
        </div>
        <script src="<c:url value="/assets/js/jquery-2.1.1.min.js" />"></script>
        <script src="<c:url value="assets/js/materialize.min.js"/>"></script>
    </body>
</html>