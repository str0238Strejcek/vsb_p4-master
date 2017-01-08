<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
 <style>
   body {
   	margin: 0;
   	padding: 0;
   	background: #E0DCD1 url(images/img01.jpg) repeat-x left top;
   	font-family: Arial, Helvetica, sans-serif;
   	font-size: 12px;
   	color: #287878;
   }

   h1, h2, h3 {
   	margin: 0;
   	padding: 0;
   	font-family: Arial, Helvetica, sans-serif;
   	font-weight: normal;
   	color: #2F4F4F;
   }

   h1 {
   	font-size: 2em;
   	text-align:center;
   }

   #formular{
   color: #FFF8DC;
   background: #2F4F4F;
   margin-left: auto;
   margin-right: auto;
   text-align: center;}

   table {
   border-style: solid;
   border-width: 2px;
   border-top-width: 5px;
   border-bottom-width: 5px;
   border-top-color: #2F4F4F;
   border-left-color: #2F4F4F;
   border-right-color: #2F4F4F;
   border-bottom-color: #2F4F4F;
}
tr:hover {background-color: #f5f5f5}
</style>
<head>

</head>
<body>
<h1>Výběr dopravních dat podle typu a místa událostí</h1>

<div id="formular">

<form action="/filter" method="POST" ><br>
   Typ dopravních událostí: <select name="updateclass">
                    <option value="">Vyberte požadovaný typ dopravní situace</option>
                    <option value="1">Dopravní situace</option>
                    <option value="3">Nehody</option>
                    <option value="4">Dopravná událostí v řešení</option>
                    <option value="5">Dopravní uzavírky a omezení</option>
                    <option value="9">Omezení provozu a zákazy</option>
                    <option value="11">Práce na silnici</option>
                    <option value="12">Nebezpečné překážky</option>
                    <option value="13">Nebezpečná situace</option>
                    <option value="18">Události ovlivňujicí dopravu</option>
                    <option value="20">Zdržení a čekací doby</option>
                  </select>
    Název obce: <input size=30 type="text" value="Zadejte obec z MS kraje" onclick="if (this.value=='Zadejte obec z MS kraje') this.value=''"; name="townname">
    <input type="submit" value="Filtrovat">
</form> <p>**Zadej obce ve tvaru: Frýdek-Místek,Frenštat pod Radhoštěm</p>
</div>
<form action="/resetFilter" method="POST" >
    <input type="submit" value="Vyresetuj filtr">
</form>




<table align="center">
<tr><th>Výpis z databáze: ID/Město/typ dopravní situace</th><th>Smazat</th></tr>
<c:forEach items="${result}" var="item">
    <tr><td><c:out value="${item.nazev}"/></td><td><a href="/delete/${item.nazev}">smazat</a></td></tr>
</c:forEach>
</table>
</body>
</html>


