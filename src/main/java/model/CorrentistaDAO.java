
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CorrentistaDAO {
    
     // CRUD CREATE    
    public void inserir(Correntista conta) {
        Conexao con = new Conexao();

        try {
            con.conectar();
            PreparedStatement pst = con.getConnection()
                    .prepareStatement("insert into contas (nome, email, cpf, senha, telefone, valor, adm) values (?,?,?,?,?,?,?)");
            pst.setString(1, conta.getUsuario().getNome());
            pst.setString(2, conta.getUsuario().getEmail());
            pst.setString(3, conta.getUsuario().getCpf());
            pst.setString(4, conta.getUsuario().getSenha());
            pst.setString(5, conta.getUsuario().getTelefone());
            pst.setDouble(6, conta.getSaldo());
            pst.setBoolean(7, conta.getUsuario().isAdm());
            // executar a query
            pst.executeUpdate();
            // fechar conexão
            con.closeConnection();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // CRUD READ 
     public ArrayList<Correntista> listar() {
        ArrayList<Correntista> contas = new ArrayList<>();
        Conexao con = new Conexao();
        String list = "select * from contas order by id";
        try {
            PreparedStatement pst = con.conectar().prepareStatement(list);
            ResultSet retornos = pst.executeQuery();

            // sera executado enquanto houver contatos
            while (retornos.next()) {
                Correntista conta = new Correntista();
                conta.getUsuario().setId(Integer.parseInt(retornos.getString(1)));
                conta.getUsuario().setNome(retornos.getString(2));
                conta.getUsuario().setEmail(retornos.getString(3));
                conta.getUsuario().setCpf(retornos.getString(4));
                conta.getUsuario().setSenha(retornos.getString(5));
                conta.getUsuario().setTelefone(retornos.getString(6));
                conta.setSaldo(retornos.getDouble(7));
                conta.getUsuario().setAdm(retornos.getBoolean(8));

                // populando o Array
                contas.add(conta);
            }
            con.closeConnection();
            return contas;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    // CRUD UPDATE
    // Selecionar o Contato
    public Correntista selecionar(Correntista conta) throws SQLException {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement pst = con.getConnection().prepareStatement("select * from contas where idcon = ?");
            pst.setInt(1, conta.getUsuario().getId());
            ResultSet retorno = pst.executeQuery();
            if (retorno.next()) {

                conta.getUsuario().setId(retorno.getInt(1));
                conta.getUsuario().setNome(retorno.getString(2));
                conta.getUsuario().setEmail(retorno.getString(3));
                conta.getUsuario().setCpf(retorno.getString(4));
                conta.getUsuario().setSenha(retorno.getString(5));
                conta.getUsuario().setTelefone(retorno.getString(6));
                conta.setSaldo(retorno.getDouble(7));
                conta.getUsuario().setAdm(retorno.getBoolean(8));

                System.out.println(retorno.getString(1));
            }
            con.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e + " erro na query");

        }
        return conta;

    }

    // Editar um Contato  
    public void alterar(Correntista conta) {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement pst = con.getConnection()
                    .prepareStatement("update contas set nome=?, email=?, cpf=? , senha=?, telefone=?, saldo=?, adm=? where id=?");
            pst.setString(1, conta.getUsuario().getNome());
            pst.setString(2, conta.getUsuario().getEmail());
            pst.setString(3, conta.getUsuario().getCpf());
            pst.setString(4, conta.getUsuario().getSenha());
            pst.setString(5, conta.getUsuario().getTelefone());
            pst.setDouble(6, conta.getSaldo());
            pst.setBoolean(7, conta.getUsuario().isAdm());
            pst.setInt(8, conta.getUsuario().getId());
            pst.executeUpdate();
            con.closeConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * CRUD DELETE *
     */
    public void deletar(Correntista conta) {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement pst = con.getConnection().prepareStatement("delete from contas where idcon=?");
            pst.setInt(1, conta.getUsuario().getId());
            pst.executeUpdate();
            con.closeConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Correntista logar(Correntista conta) throws Exception {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement sql = con.getConnection().prepareStatement("SELECT * FROM contas WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, conta.getUsuario().getCpf());
            sql.setString(2, conta.getUsuario().getSenha());
            ResultSet resultado = sql.executeQuery();
          Correntista usuarioObtido = new Correntista();
            if (resultado != null) {
                while (resultado.next()) {
                    usuarioObtido.getUsuario().setId((resultado.getInt(1)));
                    usuarioObtido.getUsuario().setNome(resultado.getString(2));
                    usuarioObtido.getUsuario().setEmail(resultado.getString(3));
                    usuarioObtido.getUsuario().setCpf(resultado.getString(4));
                    usuarioObtido.getUsuario().setSenha(resultado.getString(5));
                    usuarioObtido.getUsuario().setTelefone(resultado.getString(6));
                    usuarioObtido.setSaldo(resultado.getDouble(7));
                    usuarioObtido.getUsuario().setAdm(resultado.getBoolean(8));
                }
            }
            return usuarioObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            con.closeConnection();
        }
    }

    
    
}
