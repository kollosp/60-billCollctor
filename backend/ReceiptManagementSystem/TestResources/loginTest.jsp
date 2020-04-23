<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
      pageEncoding="ISO-8859-1"%>
 
 <HTML>
 <HEAD>
   <TITLE>ReceiptManagementSystem</TITLE>
 </HEAD>
 

<body>
  	<!-- Użycie tagu jsp:useBean. Tag ten szuka obiektu klasy User z pakiecie
  	loginSetup w sesji użytkownika. Jeśli znajdzie, to go 
  	wykorzysta, jeśli nie stworzy nowy obiekt klasy user o nazwie "user"-->
  	<jsp:useBean id="user" class="loginSetup.User"
  		scope="session"></jsp:useBean>
  
  	<!-- Użycie tagu jsp:setProperty. Tag ten umieszcza wszystkie parametry
  	przesyłane z innych plików jsp pasujące do obiektu o nazwie user. Jeśli
  	parametry te nie będą się zgadzać, tomcat wyrzuci wyjątkiem -->
  	<jsp:setProperty property="*" name="user" />
  
  	<!-- Ponowne użycie tagu jsp:useBean ładujący źródło danych. -->
  	<jsp:useBean id="dataSource"
  		class="loginSetup.DataSource" scope="session"></jsp:useBean>
  
  	<!-- Wyświetlenie nazwy użytkownika. -->
  	Nazwa: <%= user.getName() %><br />
  
  	<!-- Logika sprawdzająca poprawność parametrów logowania. -->
  	<% String result = "Dane niepoprawne";
  
  	if(dataSource.userExists(user)) {
  		result = "Poprawny uzytkownik oraz haslo";
  		}
  	%>
  
  	<!-- Zwrócenie wynikowego stringa "result" -->
  	<%= result %>
  </body>
 </HTML>