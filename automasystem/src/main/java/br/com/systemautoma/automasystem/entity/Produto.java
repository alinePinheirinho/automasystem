package br.com.systemautoma.automasystem.entity;

public class Produto extends Estoque {

    private String nomeProduto;
    private String descricaoProduto;
    private double precoVendaVarejo;
    private Preco preco;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getPrecoVendaVarejo() {
        return precoVendaVarejo;
    }

    public void setPrecoVendaVarejo(double precoVendaVarejo) {
        this.precoVendaVarejo = precoVendaVarejo;
    }

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }
}
