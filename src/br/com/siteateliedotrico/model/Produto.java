
package br.com.siteateliedotrico.model;

public class Produto {

    private int idProduto;
    private String nomeProduto;
    private String dataProduto;
    private String descricaoProduto;
    private int qtdProduto;
    private double precoCustoProduto;
    private double precoVendaProduto;

    public Produto() {
    }

    public Produto(int idProduto, String nomeProduto, String dataProduto, String descricaoProduto, int qtdProduto, double precoCustoProduto, double precoVendaProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.dataProduto = dataProduto;
        this.descricaoProduto = descricaoProduto;
        this.qtdProduto = qtdProduto;
        this.precoCustoProduto = precoCustoProduto;
        this.precoVendaProduto = precoVendaProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDataProduto() {
        return dataProduto;
    }

    public void setDataProduto(String dataProduto) {
        this.dataProduto = dataProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public double getPrecoCustoProduto() {
        return precoCustoProduto;
    }

    public void setPrecoCustoProduto(double precoCustoProduto) {
        this.precoCustoProduto = precoCustoProduto;
    }

    public double getPrecoVendaProduto() {
        return precoVendaProduto;
    }

    public void setPrecoVendaProduto(double precoVendaProduto) {
        this.precoVendaProduto = precoVendaProduto;
    }

    @Override
    public String toString() {
        return nomeProduto; 
    }

}
