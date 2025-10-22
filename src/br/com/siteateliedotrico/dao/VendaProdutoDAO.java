package br.com.siteateliedotrico.dao;

import br.com.siteateliedotrico.model.Produto;
import br.com.siteateliedotrico.model.VendaProduto;
import br.com.siteateliedotrico.conexao.Conexao;
import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VendaProdutoDAO {

    private Conexao conexao;
    private Connection conn;

    public VendaProdutoDAO() throws SQLException {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void registrarProdutosVenda(int vendaId, List<VendaProduto> produtosVenda) throws SQLException {
        String sqlVendaProduto = "INSERT INTO venda_produto (venda_id, produto_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";

        try ( PreparedStatement st = conn.prepareStatement(sqlVendaProduto)) {
            for (VendaProduto item : produtosVenda) {
                st.setInt(1, vendaId);
                st.setInt(2, item.getProdutoId().getIdProduto());
                st.setInt(3, item.getQuantidade());
                st.setDouble(4, item.getSubtotal());

                st.executeUpdate();
            }
        }
    }

    public void atualizarEstoque(List<VendaProduto> produtosVenda) throws SQLException {
        String sqlAtualizarEstoque = "UPDATE produto SET quantidade_estoque = quantidade_estoque - ? WHERE id_produto = ?";

        try ( PreparedStatement st = conn.prepareStatement(sqlAtualizarEstoque)) {
            for (VendaProduto item : produtosVenda) {
                System.out.println("Tentando atualizar estoque do produto ID: " + item.getProdutoId().getIdProduto()
                        + " - Quantidade vendida: " + item.getQuantidade());

                st.setInt(1, item.getQuantidade());
                st.setInt(2, item.getProdutoId().getIdProduto());

                int linhasAfetadas = st.executeUpdate();
                System.out.println("Linhas afetadas: " + linhasAfetadas);
            }
        }
    }

    public boolean verificarEstoque(int produtoId, int quantidade) throws SQLException {
        String sql = "SELECT quantidade_estoque FROM produto WHERE id_produto = ?";

        try ( PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, produtoId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int estoqueAtual = rs.getInt("quantidade_estoque");
                return estoqueAtual >= quantidade;
            }
        }
        return false;
    }

    public void registrarVenda(int vendaId, int produtoId, int quantidadeProduto) throws SQLException {
        try {
            List<VendaProduto> produtosVenda = new ArrayList<>();

            if (!verificarEstoque(produtoId, quantidadeProduto)) {
                JOptionPane.showMessageDialog(null, "Estoque insuficiente para o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto produto = new Produto();
            produto.setIdProduto(produtoId);

            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setProdutoId(produto);
            vendaProduto.setQuantidade(quantidadeProduto);
            vendaProduto.setSubtotal(produto.getPrecoVendaProduto() * quantidadeProduto);

            produtosVenda.add(vendaProduto);

            VendaProdutoDAO produtoVendaDAO = new VendaProdutoDAO();

            produtoVendaDAO.registrarProdutosVenda(vendaId, produtosVenda);
            produtoVendaDAO.atualizarEstoque(produtosVenda);

            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar a venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<VendaProduto> listarPorVenda(int vendaId) throws SQLException {
        List<VendaProduto> lista = new ArrayList<>();

        String sql = "SELECT vp.*, p.nome_produto, p.preco_venda_produto "
                + "FROM venda_produto vp "
                + "JOIN produto p ON vp.produto_id = p.id_produto "
                + "WHERE vp.venda_id = ?";

        try ( PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, vendaId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                VendaProduto vp = new VendaProduto();
                br.com.siteateliedotrico.model.Produto produto = new br.com.siteateliedotrico.model.Produto();

                produto.setIdProduto(rs.getInt("produto_id"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setPrecoVendaProduto(rs.getDouble("preco_venda_produto"));

                vp.setProdutoId(produto);
                vp.setQuantidade(rs.getInt("quantidade"));
                vp.setSubtotal(rs.getDouble("subtotal"));

                lista.add(vp);
            }
        }
        return lista;
    }

}
