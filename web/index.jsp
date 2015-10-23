<%-- 
    Document   : index
    Created on : 2012-10-23, 20:17:01
    Author     : moumene
--%>
<%
    if (session.getAttribute("connecte")==null)  //non connecté
    {
%>
        <jsp:forward page="login.jsp" />
<%
    }
%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Exemple de MVC</title>
        <style type="text/css">
            .errorMessage {color : red;}
            .resultat {font-weight: bold;}
        </style>
    </head>
    <body>       
<%        
        if (session.getAttribute("connecte")!=null)
        {
            out.println("<p class=\"resultat\">"+session.getAttribute("connecte")+", vous êtes connectés. "+
                        "<a href=\"logout.do?action=logout\">déconnexion</a></p>");

        }        
%>
        <h1>MVC</h1>
        <%
        if (request.getAttribute("message")!=null)
        {
            out.println("<p class=\"errorMessage\">"+request.getAttribute("message")+"</p>");
        }
        String  ns1 = request.getParameter("nb1"),
                ns2 = request.getParameter("nb2"),
                ops = request.getParameter("operation"),
                selected=" selected=\"selected\"";
        if (ns1==null) ns1="";
        if (ns2==null) ns2="";
        %>
        <form action="abc.do" method="post">
            Nombre 1 : <input type="text" name="nb1" value="<%=ns1%>" />
            <select name="operation">
                <option value="">--choisir--</option>
                <option value="+" <%="+".equals(ops)?selected:""%>>+</option>
                <option value="-" <%="-".equals(ops)?selected:""%>>-</option>
                <option value="*" <%="*".equals(ops)?selected:""%>>*</option>
                <option value="/" <%="/".equals(ops)?selected:""%>>/</option>
            </select>
            Nombre 2 : <input type="text" name="nb2" value="<%=ns2%>" />
            <input type="submit" value=" = " />
        <%
        if (request.getAttribute("resultat")!=null)
        {
            //double x = ((Double)request.getAttribute("resultat")).doubleValue();
            out.println("<span class=\"resultat\">"+request.getAttribute("resultat")+"</span>");
        }
        %>
        </form>
    </body>
</html>
