<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page import="model.Correntista"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <title>Área Usuário</title>
        <link rel="icon" href="imagens/9110984_call_phone_icon.png">
        <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
        <h1>Área Usuário</h1>
        <%
        Correntista usuarioLogado = (Correntista) session.getAttribute("usuario");
        out.println("<h3>Usuário logado com sucesso</h3>");
        out.println("<h2>Nome: " + usuarioLogado.getUsuario().getNome() + "</h2>");
        out.println("<h2>CPF: " + usuarioLogado.getUsuario().getCpf()+ "</h2>");
        out.println("<h2>Telefone " + usuarioLogado.getUsuario().getTelefone() + "</h2>");


        %>

        <script src="scripts/confirmador.js"></script>
    </body>
</html>