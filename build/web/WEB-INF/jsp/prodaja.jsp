<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Prodaja stranica</title>
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        />
        <style>
            body{
                text-align: center;
                font: 15px/1.5 Arial, Helvetica, sans-serif;
                padding:0;
                margin:0;
            }
            #main{
                width: 60%;
                margin-left: 20%;
            }
            
        </style>
    </head>
    <body>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <nav class="navbar navbar-default">
            <div class="container">
            <div class="navbar-header">
              <button
                type="button"
                class="navbar-toggle collapsed"
                data-toggle="collapse"
                data-target="#navbar"
                aria-expanded="false"
                aria-controls="navbar"
              >
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span> <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-left">
                <li><a href="#">Web Shop</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="kupac.htm">Kupci</a></li>
                <li><a href="proizvod.htm">Proizvodi</a></li>
              </ul>
            </div>
          </div>
        </nav>
        
        <div id="main">
            <div id="div1">
                <form:form class="form-group" action="Prodaj.htm" method="post" modelAttribute="shop">
                    <form:label path="idKupac">Id kupca koji zeli da kupi proizvod:</form:label><br>
                    <form:input class="form-control" id="id" type="text" path="idKupac" placeholder="..."></form:input><br>
                    <form:label path="idProizvod">Id proizvoda kojeg je kupac izabrao:</form:label><br>
                    <form:input class="form-control" id="id" type="text" path="idProizvod" placeholder="..."></form:input><br>
                    <input class="btn btn-success" type="submit" value="Prodaja"/>
                </form:form>
            </div>
        
            <div id="div2" class="list-group">
                <h2 class="text-info">Sve prodaje:</h2>
                <c:forEach items="${prodaje}" var="item">
                    <p class="list-group-item">${item}</p>
                </c:forEach>
            </div>
        </div>
    </body>
</html>