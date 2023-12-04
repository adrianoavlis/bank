/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public interface DAO {
    public void inserir(Usuario user);
    public ArrayList<Usuario> listar();
    public void selecionar();
    public void alterar();
    public void deletar();
    public Usuario logar();
}
