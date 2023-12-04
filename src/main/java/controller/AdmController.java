/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import model.AdmDAO;
import model.Correntista;
import model.CorrentistaDAO;
import model.DAO;
import model.Transacao;
import model.Usuario;

@WebServlet(urlPatterns = {"/AdmController", "/mainAdm", "/insertAdm", "/selectAdm", "/updateAdm", "/deleteAdm"})
public class AdmController extends HttpServlet {

   
    AdmDAO dao = new AdmDAO();
    Usuario adm = new Usuario();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/mainAdm":
                try {
                    minhaConta(request, response);
                } catch (SQLException ex) {
                    RequestDispatcher rd = request.getRequestDispatcher("formLogin.jsp");
                    rd.forward(request, response);
                }   break;
            case "/insertAdm":
                novo(request, response);
                break;
            case "/selectAdm":
            {
                try {
                    selecionar(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;

            case "/updateAdm":
                editar(request, response);
                break;
            case "/deleteAdm":
                deletar(request, response);
                break;
            default:
                response.sendRedirect("index.html");
                break;
        }
    }
    
    // Listar Contas
    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Usuario> contas = dao.listar();

        // encaminhar lista para area do ADM
        request.setAttribute("contas", contas);
        RequestDispatcher rd = request.getRequestDispatcher("areaAdm.jsp");
        rd.forward(request, response);
    }

    protected void minhaConta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        Usuario adm = new Usuario();
        adm.setId((int) request.getSession().getAttribute("id"));

        Usuario admobtido = dao.selecionar(adm);

        // encaminhar lista para area do ADM
        request.setAttribute("usuario", admobtido);
        RequestDispatcher rd = request.getRequestDispatcher("areaAdm.jsp");
        rd.forward(request, response);
    }

    // Novo Contatos
    protected void novo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // teste de recebimento dos dados do formulario
        System.out.println(request.getParameter("nome"));
        Usuario adm = new Usuario();
        // Setar contatos JavaBeans
        adm.setNome(request.getParameter("nome"));
        adm.setEmail(request.getParameter("email"));
        adm.setCpf(request.getParameter("cpf"));
        adm.setSenha(request.getParameter("senha"));
        adm.setTelefone(request.getParameter("Telefone"));
        adm.setAdm(true);

        // invocar DAO passando o objeto
        dao.inserir(adm);

        // Redirecionar para main.jsp
        response.sendRedirect("main");

    }

    // Editar Contato
    protected void selecionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Usuario conta = new Usuario();
        // setar JavaBeans
        conta.setId(Integer.parseInt(request.getParameter("id")));

        // executar método selecionar contato (DAO)
       
            dao.selecionar(conta);
        
        // Setar o conteúdo do form com o objeto
        request.setAttribute("id", conta.getId());
        request.setAttribute("nome", conta.getNome());
        request.setAttribute("email", conta.getEmail());
        request.setAttribute("cpf", conta.getCpf());
        request.setAttribute("telefone", conta.getTelefone());

        // Encaminhar para editar.jsp
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);

    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setar dados do form para o objeto
        adm.setId(Integer.parseInt(request.getParameter("idcon")));
        adm.setNome(request.getParameter("nome"));
        adm.setEmail(request.getParameter("fone"));
        adm.setCpf(request.getParameter("email"));
        adm.setSenha(request.getParameter("senha"));
        adm.setTelefone(request.getParameter("telefone"));
        dao.alterar(adm);

        // Redirecionar para o agenda.jsp
        response.sendRedirect("areaAdm.jsp");
    }

    // Remover um Contato
    protected void deletar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Setar Variável id no Objeto
        adm.setId(Integer.parseInt(request.getParameter("id")));
        // Executar o método deletarContato(DAO) passando o objeto
        dao.deletar(adm);
        // Redirecionar para o agenda.jsp
        response.sendRedirect("areaAdm.jsp");

    }

}
