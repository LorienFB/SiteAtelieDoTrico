package br.com.siteateliedotrico.dao;

import br.com.siteateliedotrico.model.Venda;
import br.com.siteateliedotrico.model.VendaProduto;
import br.com.siteateliedotrico.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.sql.*;

public class VendaDAO {

    private Conexao conexao;
    private Connection conn;

    public VendaDAO() throws SQLException {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    PreparedStatement st;
    ResultSet rs;

    public int cadastrarVenda(Venda venda) {
        int status = 0; // Inicializa status
        try {
            String sqlVenda = "INSERT INTO venda (vendedor_id, data_venda, total_venda) VALUES (?, ?, ?)";
            st = conn.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, venda.getVendedorVenda().getIdUsuario());
            st.setString(2, venda.getDataVenda());
            st.setDouble(3, venda.getTotalVenda());

            status = st.executeUpdate();

            if (status > 0) {
                System.out.println("Venda cadastrada com sucesso!");

                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int vendaId = rs.getInt(1);

                    String sqlVendaProduto = "INSERT INTO venda_produto (venda_id, produto_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";
                    PreparedStatement stProduto = conn.prepareStatement(sqlVendaProduto);

                    for (VendaProduto vp : venda.getProdutosVenda()) {
                        stProduto.setInt(1, vendaId);
                        stProduto.setInt(2, vp.getProdutoId().getIdProduto());
                        stProduto.setInt(3, vp.getQuantidade());
                        stProduto.setDouble(4, vp.getSubtotal());
                        stProduto.executeUpdate();
                    }
                }
            } else {
                System.out.println("Falha ao cadastrar venda.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar venda: " + ex.getMessage());
            return -1;
        }
        return status;
    }

    public void registrarVenda(Venda venda) throws SQLException {
        String sqlVenda = "INSERT INTO venda (vendedor_id, total_venda, data_venda) VALUES (?, ?, ?)";
        String sqlVendaProduto = "INSERT INTO venda_produto (venda_id, produto_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";
        String sqlAtualizarEstoque = "UPDATE produto SET quantidade_estoque = quantidade_estoque - ? WHERE id_produto = ?";

        try {
            conn.setAutoCommit(false);

            int vendaId;
            try ( PreparedStatement stVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
                stVenda.setInt(1, venda.getVendedorVenda().getIdUsuario());
                stVenda.setDouble(2, venda.getTotalVenda());
                stVenda.setString(3, venda.getDataVenda());
                stVenda.executeUpdate();
                ResultSet rs = stVenda.getGeneratedKeys();
                rs.next();
                vendaId = rs.getInt(1);
            }

            try ( PreparedStatement stVendaProduto = conn.prepareStatement(sqlVendaProduto);  PreparedStatement stAtualizarEstoque = conn.prepareStatement(sqlAtualizarEstoque)) {

                for (VendaProduto item : venda.getProdutosVenda()) {

                    int produtoId = item.getProdutoId().getIdProduto();

                    // Insere na tabela venda_produto
                    stVendaProduto.setInt(1, vendaId);
                    stVendaProduto.setInt(2, produtoId);
                    stVendaProduto.setInt(3, item.getQuantidade());
                    stVendaProduto.setDouble(4, item.getSubtotal());
                    stVendaProduto.executeUpdate();

                    // Atualiza o estoque
                    stAtualizarEstoque.setInt(1, item.getQuantidade());
                    stAtualizarEstoque.setInt(2, produtoId);
                    stAtualizarEstoque.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

}
