
package br.com.siteateliedotrico.model;

import br.com.siteateliedotrico.model.Usuario;
import java.util.List;

public class Venda {

    private int idVenda;
    private Usuario vendedorVenda;
    private List<VendaProduto> produtosVenda;
    private double totalVenda;
    private String dataVenda;

    public Venda() {
    }

    public Venda(int idVenda, Usuario vendedorVenda, List<VendaProduto> produtosVenda, double totalVenda, String dataVenda) {
        this.idVenda = idVenda;
        this.vendedorVenda = vendedorVenda;
        this.produtosVenda = produtosVenda;
        this.totalVenda = totalVenda;
        this.dataVenda = dataVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Usuario getVendedorVenda() {
        return vendedorVenda;
    }

    public void setVendedorVenda(Usuario vendedorVenda) {
        this.vendedorVenda = vendedorVenda;
    }

    public List<VendaProduto> getProdutosVenda() {
        return produtosVenda;
    }

    public void setProdutosVenda(List<VendaProduto> produtosVenda) {
        this.produtosVenda = produtosVenda;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    

}
