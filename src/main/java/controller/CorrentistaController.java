
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Correntista;
import model.DAO;
import model.CorrentistaDAO;
import model.Transacao;
import model.Usuario;

@WebServlet(urlPatterns = {"/CorrentistaController", "/mainUser", "/insert", "/select", "/update", "/delete"})
public class CorrentistaController extends HttpServlet {

    CorrentistaDAO dao = new CorrentistaDAO();
    Correntista conta = new Correntista();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        if (action.equals("/mainUser")) {
            try {
                minhaConta(request, response);
            } catch (SQLException ex) {
                RequestDispatcher rd = request.getRequestDispatcher("formLogin.jsp");
                    rd.forward(request, response);
            }
        } else if (action.equals("/insert")) {
            novaConta(request, response);
        } else if (action.equals("/select")) {
            try {
                selecionar(request, response);
            } catch (SQLException ex) {
               RequestDispatcher rd = request.getRequestDispatcher("formLogin.jsp");
                    rd.forward(request, response);
            }
        } else if (action.equals("/update")) {
            editar(request, response);
        } else if (action.equals("/delete")) {
            deletar(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
   
    protected void minhaConta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        Correntista conta = new Correntista();
        conta.getUsuario().setId((int) request.getSession().getAttribute("id"));

        Correntista contaobtida = dao.selecionar(conta);

        // encaminhar lista para area do ADM
        request.setAttribute("usuario", contaobtida);
        RequestDispatcher rd = request.getRequestDispatcher("areaUsuario.jsp");
        rd.forward(request, response);
    }

    // Novo Contatos
    protected void novaConta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // teste de recebimento dos dados do formulario
        System.out.println(request.getParameter("nome"));
        Correntista conta = new Correntista();
        // Setar contatos JavaBeans
        conta.getUsuario().setNome(request.getParameter("nome"));
        conta.getUsuario().setEmail(request.getParameter("email"));
        conta.getUsuario().setCpf(request.getParameter("cpf"));
        conta.getUsuario().setSenha(request.getParameter("senha"));
        conta.getUsuario().setTelefone(request.getParameter("Telefone"));

        // invocar DAO passando o objeto
        dao.inserir(conta);

        // Redirecionar para main.jsp
        response.sendRedirect("index.html");

    }

    // Editar Contato
    protected void selecionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Correntista conta = new Correntista();
        // setar JavaBeans
        conta.getUsuario().setId(Integer.parseInt(request.getParameter("id")));

        // executar método selecionar contato (DAO)
       
            Correntista contaobtida = dao.selecionar(conta);
        

        // Setar o conteúdo do form com o objeto
        request.setAttribute("id", conta.getUsuario().getId());
        request.setAttribute("nome", conta.getUsuario().getNome());
        request.setAttribute("email", conta.getUsuario().getEmail());
        request.setAttribute("cpf", conta.getUsuario().getCpf());
        request.setAttribute("telefone", conta.getUsuario().getTelefone());

        // Encaminhar para editar.jsp
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);

    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setar dados do form para o objeto
        conta.getUsuario().setId(Integer.parseInt(request.getParameter("idcon")));
        conta.getUsuario().setNome(request.getParameter("nome"));
        conta.getUsuario().setEmail(request.getParameter("fone"));
        conta.getUsuario().setCpf(request.getParameter("email"));
        conta.getUsuario().setSenha(request.getParameter("senha"));
        conta.getUsuario().setTelefone(request.getParameter("telefone"));
        dao.alterar(conta);

        // Redirecionar para o agenda.jsp
        response.sendRedirect("areaAdm.jsp");
    }

    // Remover um Contato
    protected void deletar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Setar Variável id no Objeto
        conta.getUsuario().setId(Integer.parseInt(request.getParameter("id")));
        // Executar o método deletarContato(DAO) passando o objeto
        dao.deletar(conta);
        // Redirecionar para o agenda.jsp
        response.sendRedirect("areaAdm.jsp");

    }
}
