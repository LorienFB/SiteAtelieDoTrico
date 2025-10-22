package br.com.siteateliedotrico.service;

import br.com.siteateliedotrico.dao.VendaProdutoDAO;
import br.com.siteateliedotrico.model.VendaProduto;
import java.sql.SQLException;
import java.util.List;

public class VendaProdutoService {

    private VendaProdutoDAO vendaProdutoDAO;

    public VendaProdutoService() {
        try {
            this.vendaProdutoDAO = new VendaProdutoDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar VendaProdutoDAO", e);
        }
    }

    public boolean cadastrarVendaProduto(int vendaId, List<VendaProduto> produtosVenda) {
        try {
            vendaProdutoDAO.registrarProdutosVenda(vendaId, produtosVenda);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produtos da venda: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarEstoque(List<VendaProduto> produtosVenda) {
        try {
            vendaProdutoDAO.atualizarEstoque(produtosVenda);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque: " + e.getMessage());
            return false;
        }
    }

    public List<VendaProduto> listarPorVenda(int vendaId) {
        try {
            return vendaProdutoDAO.listarPorVenda(vendaId);
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos da venda: " + e.getMessage());
            return null;
        }
    }

}
