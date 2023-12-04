package controller;

import model.Correntista;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdmDAO;
import model.CorrentistaDAO;
import model.Usuario;

@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        RequestDispatcher rd;
        switch (acao) {
            case "Login":  // chama form de login

                rd = request.getRequestDispatcher("formLogin.jsp");
                rd.forward(request, response);

                break;
            case "Logout":
                HttpSession session = request.getSession();
                session.invalidate();
                rd = request.getRequestDispatcher("formLogin.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String user_adm = request.getParameter("adm");
        if (cpf_user == null || cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("formLogin.jsp");
            rd.forward(request, response);

        } else {
            if (user_adm.equalsIgnoreCase("adm")) {

                Usuario usuarioObtido = new Usuario();
                Usuario usuarioInput = new Usuario();
                usuarioInput.setCpf(cpf_user);
                usuarioInput.setSenha(senha_user);
                AdmDAO dao = new AdmDAO();
                try {
                    usuarioObtido = dao.logar(usuarioInput);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query para Logar");
                }

                if (usuarioObtido.getId() != 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuarioObtido);
                    session.setAttribute("id", usuarioObtido.getId());
                    System.out.println(usuarioObtido.getId());
                    
                        rd = request.getRequestDispatcher("/mainAdm");
                        rd.forward(request, response);

                } else {
                    request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                    rd = request.getRequestDispatcher("formLogin.jsp");
                    rd.forward(request, response);
                }
            }

            Correntista usuarioObtido = new Correntista();
            Correntista usuarioInput = new Correntista();
            usuarioInput.getUsuario().setCpf(cpf_user);
            usuarioInput.getUsuario().setSenha(senha_user);
            CorrentistaDAO dao = new CorrentistaDAO();
            try {
                usuarioObtido = dao.logar(usuarioInput);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (usuarioObtido.getUsuario().getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarioObtido);
                System.out.println(usuarioObtido.getUsuario().getId());
               
                rd = request.getRequestDispatcher("/mainUser");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("formLogin.jsp");
                rd.forward(request, response);
            }
        }
    }

}
