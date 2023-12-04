package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdmDAO {

    // CRUD CREATE      
    public void inserir(Usuario adm) {
        Conexao con = new Conexao();

        try {
            con.conectar();
            PreparedStatement pst = con.getConnection()
                    .prepareStatement("insert into contas (nome, email, cpf, senha, telefone, adm) values (?,?,?,?,?,?)");
            pst.setString(1, adm.getNome());
            pst.setString(2, adm.getEmail());
            pst.setString(3, adm.getCpf());
            pst.setString(4, adm.getSenha());
            pst.setString(5, adm.getTelefone());
            pst.setBoolean(6, adm.isAdm());
            // executar a query
            pst.executeUpdate();
            // fechar conexão
            con.closeConnection();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // CRUD READ 
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> adms = new ArrayList<Usuario>();
        Conexao con = new Conexao();
        String list = "select * from contas order by id";
        try {
            PreparedStatement pst = con.conectar().prepareStatement(list);
            ResultSet retornos = pst.executeQuery();

            // sera executado enquanto houver contatos
            while (retornos.next()) {
                Usuario adm = new Usuario();
                adm.setId(Integer.parseInt(retornos.getString(1)));
                adm.setNome(retornos.getString(2));
                adm.setEmail(retornos.getString(3));
                adm.setCpf(retornos.getString(4));
                adm.setSenha(retornos.getString(5));
                adm.setTelefone(retornos.getString(6));
                adm.setAdm(retornos.getBoolean(7));

                // populando o Array
                adms.add(adm);
            }
            con.closeConnection();
            return adms;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    // CRUD UPDATE
    // Selecionar o Contato
    public Usuario selecionar(Usuario adm) throws SQLException {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement pst = con.getConnection().prepareStatement("select * from contas where idcon = ?");
            pst.setInt(1, adm.getId());
            ResultSet retorno = pst.executeQuery();
            if (retorno.next()) {

                adm.setId(retorno.getInt(1));
                adm.setNome(retorno.getString(2));
                adm.setEmail(retorno.getString(3));
                adm.setCpf(retorno.getString(4));
                adm.setSenha(retorno.getString(5));
                adm.setTelefone(retorno.getString(6));
                adm.setAdm(retorno.getBoolean(8));

                System.out.println(retorno.getString(1));
            }
            con.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e + " erro na query");

        }
        return adm;
    }

    // Editar um Contato  
    public void alterar(Usuario adm) {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement pst = con.getConnection()
                    .prepareStatement("update contas set nome=?, email=?, cpf=? , senha=?, telefone=?, adm=? where id=?");
            pst.setString(1, adm.getNome());
            pst.setString(2, adm.getEmail());
            pst.setString(3, adm.getCpf());
            pst.setString(4, adm.getSenha());
            pst.setString(5, adm.getTelefone());
            pst.setBoolean(6, adm.isAdm());
            pst.setInt(7, adm.getId());
            pst.executeUpdate();
            con.closeConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * CRUD DELETE *
     */
    public void deletar(Usuario adm) {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement pst = con.getConnection().prepareStatement("delete from contas where idcon=?");
            pst.setInt(1, adm.getId());
            pst.executeUpdate();
            con.closeConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Usuario logar(Usuario conta) throws Exception {
        Conexao con = new Conexao();
        try {
            con.conectar();
            PreparedStatement sql = con.getConnection().prepareStatement("SELECT * FROM contas WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, conta.getCpf());
            sql.setString(2, conta.getSenha());
            ResultSet resultado = sql.executeQuery();
            Usuario usuarioObtido = new Usuario();
            if (resultado != null) {
                while (resultado.next()) {
                    usuarioObtido.setId((resultado.getInt(1)));
                    usuarioObtido.setNome(resultado.getString(2));
                    usuarioObtido.setEmail(resultado.getString(3));
                    usuarioObtido.setCpf(resultado.getString(4));
                    usuarioObtido.setSenha(resultado.getString(5));
                    usuarioObtido.setTelefone(resultado.getString(6));
                    usuarioObtido.setAdm(resultado.getBoolean(8));
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
