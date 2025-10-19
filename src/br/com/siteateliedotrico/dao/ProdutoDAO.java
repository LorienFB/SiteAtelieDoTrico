
package br.com.siteateliedotrico.dao;

import br.com.siteateliedotrico.model.Produto;
import br.com.siteateliedotrico.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() throws SQLException {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    PreparedStatement st;
    ResultSet rs;

   public int cadastrarProduto(Produto produto) {
    int status = 0;
    try {
        st = conn.prepareStatement("INSERT INTO produto (nome_produto, data_produto, descricao_produto, quantidade_produto, preco_custo_produto, preco_venda_produto) VALUES (?, ?, ?, ?, ?, ?)");
        st.setString(1, produto.getNomeProduto());
        st.setString(2, produto.getDataProduto());
        st.setString(3, produto.getDescricaoProduto());
        st.setInt(4, produto.getQtdProduto());
        st.setDouble(5, produto.getPrecoCustoProduto());
        st.setDouble(6, produto.getPrecoVendaProduto());

        status = st.executeUpdate();

        if (status > 0) {
            System.out.println("Produto cadastrado com sucesso! Linhas afetadas: " + status);
        } else {
            System.out.println("Falha ao cadastrar produto, nenhuma linha foi afetada.");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
    } finally {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar Statement: " + e.getMessage());
        }
    }
    return status;
}


    public Produto buscarProdutoPorId(int id) {
        Produto produto = null;
        try {
            String sql = "SELECT * FROM produto WHERE id_produto = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setDataProduto(rs.getString("data_produto"));
                produto.setDescricaoProduto(rs.getString("descricao_produto"));
                produto.setQtdProduto(rs.getInt("quantidade_produto"));
                produto.setPrecoCustoProduto(rs.getDouble("preco_custo_produto"));
                produto.setPrecoVendaProduto(rs.getDouble("preco_venda_produto"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produto por ID: " + ex.getMessage());
        }
        return produto;
    }

    public Produto getProdutoPorID(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id_produto = ?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {  // Se encontrar o produto
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setDataProduto(rs.getString("data_produto"));
                produto.setDescricaoProduto(rs.getString("descricao_produto"));
                produto.setQtdProduto(rs.getInt("quantidade_produto"));
                produto.setPrecoCustoProduto(rs.getDouble("preco_custo_produto"));
                produto.setPrecoVendaProduto(rs.getDouble("preco_venda_produto"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produto por ID: " + ex.getMessage());
        }

        return produto;
    }

    public List<Produto> getProdutoLista(String nomePesquisa) {
        List<Produto> listaProduto = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE nome_produto LIKE ?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%" + nomePesquisa + "%");  // Busca por qualquer parte do nome
            ResultSet rs = st.executeQuery();

            while (rs.next()) {  // Percorre os resultados
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setDataProduto(rs.getString("data_produto"));
                produto.setDescricaoProduto(rs.getString("descricao_produto"));
                produto.setQtdProduto(rs.getInt("quantidade_produto"));
                produto.setPrecoCustoProduto(rs.getDouble("preco_custo_produto"));
                produto.setPrecoVendaProduto(rs.getDouble("preco_venda_produto"));

                listaProduto.add(produto);  // Adiciona à lista
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produtos: " + ex.getMessage());
        }

        return listaProduto;
    }
    
    public void atualizarEstoque(int idProduto, int quantidadeVendida) throws SQLException {
    String sql = "UPDATE produto SET quantidade_produto = quantidade_produto - ? WHERE id_produto = ?";

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, quantidadeVendida);
        st.setInt(2, idProduto);
        st.executeUpdate();
    }
}


}
