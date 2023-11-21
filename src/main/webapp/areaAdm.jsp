<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ page import="model.DAO"%>
<%@ page import="model.Correntista"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <title>Área Administrador</title>     
        <link rel="stylesheet" href="style/style.css">
        <link rel="stylesheet" href="style/bootstrap.css">
   
    </head>
    <body>
         <nav class="navbar navbar-expand-lg navbar-ligth bg-ligth">
        <div class="container">
            <a class="navbar-brand text-center ms-2" href="index.html">
                <img src="https://media.discordapp.net/attachments/963533169708134463/1166479547840090203/OIG.51t5Dx_P0SKgz-removebg-preview.png" width="50px">
            </a>
            
        </div>
        
    </nav>
        
        <h1>Área Administrador</h1>
        <a href="cadastro.html" >Novo Conta</a>

        <table id="tabela" class="table-light">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>CPF</th>
                    <th>Telefone</th>
                    <th>Opcões</th>
                </tr>
            </thead>
            <tbody class="form-group mt-3">
                <%
                    DAO dao = new DAO();
                    ArrayList<Correntista> lista_contas = dao.listarContas();
                    if (lista_contas != null) {
                        for (int i = 0; i < lista_contas.size(); i++) {
                %>
                <tr>
                    <td><%=lista_contas.get(i).getUsuario().getId()%></td>
                    <td><%=lista_contas.get(i).getUsuario().getNome()%></td>
                    <td><%=lista_contas.get(i).getUsuario().getEmail()%></td>
                    <td><%=lista_contas.get(i).getUsuario().getCpf()%></td>
                    <td><%=lista_contas.get(i).getUsuario().getTelefone()%></td>
                    <td>
                        <a href="select?idcon=<%=lista_contas.get(i).getUsuario().getId()%>">Editar</a> 
                        <a href="javascript: confirmar(<%=lista_contas.get(i).getUsuario().getId()%>)">Excluir</a></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        <script src="scripts/script.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>