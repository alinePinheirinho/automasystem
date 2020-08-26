package br.com.systemautoma.automasystem.entity;

import java.math.BigDecimal;

public class Preco {

    private long idPreco;
    private long idProduto;
    private long idEmpresa;
    private BigDecimal precoVendaVarejo;
    private BigDecimal precoVendaAtacado;
    private BigDecimal precoVendaCompra;
    private BigDecimal precoPromocional;
    private BigDecimal precoParcelado;

    public long getIdPreco() {
        return idPreco;
    }

    public void setIdPreco(long idPreco) {
        this.idPreco = idPreco;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public BigDecimal getPrecoVendaVarejo() {
        return precoVendaVarejo;
    }

    public void setPrecoVendaVarejo(BigDecimal precoVendaVarejo) {
        this.precoVendaVarejo = precoVendaVarejo;
    }

    public BigDecimal getPrecoVendaAtacado() {
        return precoVendaAtacado;
    }

    public void setPrecoVendaAtacado(BigDecimal precoVendaAtacado) {
        this.precoVendaAtacado = precoVendaAtacado;
    }

    public BigDecimal getPrecoVendaCompra() {
        return precoVendaCompra;
    }

    public void setPrecoVendaCompra(BigDecimal precoVendaCompra) {
        this.precoVendaCompra = precoVendaCompra;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public BigDecimal getPrecoParcelado() {
        return precoParcelado;
    }

    public void setPrecoParcelado(BigDecimal precoParcelado) {
        this.precoParcelado = precoParcelado;
    }

    public long getIdEmpresa() { return idEmpresa; }

    public void setIdEmpresa(long idEmpresa) { this.idEmpresa = idEmpresa; }
}
