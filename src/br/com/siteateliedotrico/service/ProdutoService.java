
package br.com.siteateliedotrico.service;

import br.com.siteateliedotrico.model.Produto;
import br.com.siteateliedotrico.dao.ProdutoDAO;
import java.sql.SQLException;

public class ProdutoService {
    
   private ProdutoDAO produtoDAO;

    public ProdutoService() {
        try {
            produtoDAO = new ProdutoDAO(); // precisa tratar SQLException
        } catch (SQLException ex) {
            ex.printStackTrace();
            // opcional: lançar RuntimeException se não puder continuar
            throw new RuntimeException("Erro ao criar ProdutoDAO", ex);
        }
    }

    public int salvarProduto(Produto produto) {
        // validações, regras de negócio podem ser adicionadas aqui
        return produtoDAO.cadastrarProduto(produto); // chama o método correto do DAO
    }
}
