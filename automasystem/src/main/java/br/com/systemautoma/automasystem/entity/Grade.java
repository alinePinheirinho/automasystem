package br.com.systemautoma.automasystem.entity;

import java.util.List;

public class Grade {

    private long idGrade;
    private List<Cor> cor;
    private List<Tamanho> tamanho;
    private Estoque estoque;
    private Preco preco;
    private long idProduto;
    private long codBarras;
    private String codPersonalizado;

    public Grade(List<Cor> cor, List<Tamanho> tamanho, Estoque estoque,
                 Preco preco, long idProduto, long codBarras, String codPersonalizado) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.estoque = estoque;
        this.preco = preco;
        this.idProduto = idProduto;
        this.codBarras = codBarras;
        this.codPersonalizado = codPersonalizado;
    }

    public long getIdGrade() {
        return idGrade;
    }

    public List<Cor> getCor() {
        return cor;
    }

    public void setCor(List<Cor> cor) {
        this.cor = cor;
    }

    public List<Tamanho> getTamanho() {
        return tamanho;
    }

    public void setTamanho(List<Tamanho> tamanho) {
        this.tamanho = tamanho;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }

    public String getCodPersonalizado() {
        return codPersonalizado;
    }

    public void setCodPersonalizado(String codPersonalizado) {
        this.codPersonalizado = codPersonalizado;
    }
}
