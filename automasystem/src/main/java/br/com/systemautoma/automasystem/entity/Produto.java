package br.com.systemautoma.automasystem.entity;

import java.util.Set;

public class Produto {

    private long idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double precoVendaVarejo;
    private Set<Preco> precos;
    private Set<Estoque> estoques;
    private boolean ativo = true;
    private long codBarras;
    private String codPersonalizado;
    private Set<Grade> grades;

    public Produto() { }

    public Produto(String nomeProduto, String descricaoProduto, double precoVendaVarejo,
                   Set<Preco> precos, Set<Estoque> estoques, boolean ativo, long codBarras, String codPersonalizado, Set<Grade> grades) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoVendaVarejo = precoVendaVarejo;
        this.precos = precos;
        this.estoques = estoques;
        this.ativo = ativo;
        this.codBarras = codBarras;
        this.codPersonalizado = codPersonalizado;
        this.grades = grades;
    }

    public long getIdProduto() {
        return idProduto;
    }

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

    public Set<Preco> getPrecos() {
        return precos;
    }

    public void setPrecos(Set<Preco> precos) {
        this.precos = precos;
    }

    public Set<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(Set<Estoque> estoques) {
        this.estoques = estoques;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
