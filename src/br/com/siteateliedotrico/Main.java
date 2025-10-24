package br.com.siteateliedotrico;

import br.com.siteateliedotrico.model.Produto;
import br.com.siteateliedotrico.service.ProdutoService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema do Ateliê do Tricô iniciado!");

        // Cria o objeto Produto
        Produto produto = new Produto();
        produto.setNomeProduto("Blusa de Lã");
        produto.setDataProduto("2025-10-23");
        produto.setDescricaoProduto("Blusa feita à mão com lã natural");
        produto.setQtdProduto(10);
        produto.setPrecoCustoProduto(50.00);
        produto.setPrecoVendaProduto(120.00);

        // Cria o service e chama o método de cadastro
        ProdutoService produtoService = new ProdutoService();
        produtoService.cadastrarProduto(produto);

        System.out.println("Teste de cadastro realizado com sucesso!");
    }
}
