package br.com.siteateliedotrico.dao;

import br.com.siteateliedotrico.model.Usuario;
import br.com.siteateliedotrico.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection conn;
    private PreparedStatement st;

    public UsuarioDAO() {
        try {
            this.conn = new Conexao().getConexao();
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        int status;
        try {

            st = conn.prepareStatement("INSERT INTO usuario (nome_usuario, login_usuario, senha_usuario, tipo_usuario) VALUES (?, ?, ?, ?)");
            st.setString(1, usuario.getNomeUsuario());
            st.setString(2, usuario.getLoginUsuario());
            st.setString(3, usuario.getSenhaUsuario());
            st.setString(4, usuario.getTipoUsuario());

            status = st.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar usuário: " + ex.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = null;
        try {

            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setLoginUsuario(rs.getString("login_usuario"));
                usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário por ID: " + ex.getMessage());
        }
        return usuario;
    }

    public Usuario validarLogin(String login, String senha) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuario WHERE login_usuario = ? AND senha_usuario = ?";

        try ( PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, login);
            st.setString(2, senha);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setLoginUsuario(rs.getString("login_usuario"));
                usuario.setSenhaUsuario(rs.getString("senha_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
        }
        return usuario;
    }
}
