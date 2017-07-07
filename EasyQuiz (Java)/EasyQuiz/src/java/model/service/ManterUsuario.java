/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.domain.Usuario;
import model.exception.ExcecaoNegocio;
import model.exception.ExcecaoPersistencia;
import java.util.List;
import model.domain.Usuario;

/**
 *
 * @author Luiz
 */
public interface ManterUsuario {
    public void cadastrarUsuario(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public void alterarUsuario(Usuario usuario) throws ExcecaoPersistencia, ExcecaoNegocio;
    public Usuario deletarUsuario(Long cod_Usuario) throws ExcecaoPersistencia;
    public Usuario getUsuarioById(Long cod_Usuario) throws ExcecaoPersistencia;
    public List<Usuario> getAll() throws ExcecaoPersistencia;
}
