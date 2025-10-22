package br.com.siteateliedotrico.service;

import br.com.siteateliedotrico.dao.UsuarioDAO;
import br.com.siteateliedotrico.model.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario validarLogin(String login, String senha) {
        return usuarioDAO.validarLogin(login, senha);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioDAO.cadastrarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioDAO.buscarUsuarioPorId(id);
    }

}
