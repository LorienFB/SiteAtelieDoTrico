
package br.com.siteateliedotrico.model;

import br.com.siteateliedotrico.model.Produto;

public class VendaProduto {

    private int vendaId;
    private Produto produtoId;
    private int quantidade;
    private double subtotal;

    public VendaProduto() {
    }

    public VendaProduto(int vendaId, Produto produtoId, int quantidade, double subtotal) {
        this.vendaId = vendaId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    public Produto getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produto produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

   

    
   
}
