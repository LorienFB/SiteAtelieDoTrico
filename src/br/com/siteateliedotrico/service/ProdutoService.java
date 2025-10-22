
package br.com.siteateliedotrico.service;

import br.com.siteateliedotrico.model.Produto;
import br.com.siteateliedotrico.dao.ProdutoDAO;
import java.sql.SQLException;

public class ProdutoService {
    
   private ProdutoDAO produtoDAO;

    public ProdutoService() {
        try {
            produtoDAO = new ProdutoDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

    public void cadastrarProduto(Produto p) {
        // Aqui você pode colocar validações
        if (p.getNomeProduto() == null || p.getNomeProduto().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio.");
        }
        produtoDAO.cadastrarProduto(p);
    }
}
