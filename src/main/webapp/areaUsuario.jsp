<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Favela Bank</title>
    <link rel="stylesheet" href="style/bootstrap.css">
    <link href="style/style.css" rel="stylesheet">

    <style>
        html, body {
            height: 100%;
            margin: 0; /* Remover margens padrão do corpo da página */
        }

        body {
            font-family: Arial, sans-serif;
        }

        #menu {
            background-color: #ffffff;
            padding: 15px;
            color: #333;
            float: left;
            width: 150px;
        }

        #menu a {
            text-decoration: none;
            color: #333;
            display: block;
            margin-bottom: 10px;
            background-color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
        }

        #menu a:hover {
            background-color: #ddd;
        }

        #menu a.selected {
            background-color: orange;
            color: white;
        }

        #retangulo1,
        #retangulo2 {
            width: calc(50% - 160px);
            height: 100%;
            background-color: white;
            padding: 20px;
        }

        #retangulo2 {
            background-color: coral;
            color: white;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand text-center ms-2" href="index.html">
                <img src="https://media.discordapp.net/attachments/963533169708134463/1166479547840090203/OIG.51t5Dx_P0SKgz-removebg-preview.png" width="50px">
            </a>
            <div class="cadastro_entrar">
                <a href="/" class="btn btn-primary">Página Inicial</a>
                <a href="AutenticaController?acao=Login" class="btn btn-secondary">Perfil</a>
                <a href="areaAdm.jsp" class="btn btn-secondary">Configurações</a>
                <a href="areaUsuario.jsp" class="btn btn-secondary">Sair</a>
            </div>
        </div>
    </nav>

    <div id="menu">
        <!-- Conteúdo do menu -->
        <a href="#" class="selected">Saque</a>
        <a href="#">Transferências</a>
        <a href="#">Extratos</a>
        <a href="#">Investimentos</a>
    </div>

    <div id="retangulo1">
        <!-- Conteúdo do primeiro retângulo -->
        <h2>Bem vindo, @nomeDoUsuario</h2>
        <h3> Saldo: @saldo</h3> 
    </div>

    <div class="footer fixed-bottom">
        &copy; 2023 Sistema Bancário
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
