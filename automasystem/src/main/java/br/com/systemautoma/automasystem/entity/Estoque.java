package br.com.systemautoma.automasystem.entity;

public class Estoque {

    private long idEstoque;
    private long idEmpresa;
    private double quantidade;
    private double quantidadeMinima;
    private double quantidadeMaxima;

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    public double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(double quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public double getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(double quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public long getIdEstoque() { return idEstoque; }

    public void setIdEstoque(long idEstoque) { this.idEstoque = idEstoque; }

    public long getIdEmpresa() { return idEmpresa; }

    public void setIdEmpresa(long idEmpresa) { this.idEmpresa = idEmpresa; }
}
