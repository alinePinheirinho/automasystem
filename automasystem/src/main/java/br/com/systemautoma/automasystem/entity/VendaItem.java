package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.TipoDePreco;

import java.math.BigDecimal;

public class VendaItem {

    private long idVendaItem;
    private long idVenda;
    private long idCliente;
    private long idProduto;
    private long idEStoque;
    private TipoDePreco tipoDePreco;
    private BigDecimal valor;
    private double quantidadeVendida;
    private boolean cancelado = false;
    private boolean vendaCancelada = false;

    public VendaItem() { }

    public VendaItem(long idVenda, long idCliente, long idProduto, long idEStoque, TipoDePreco tipoDePreco,
                     BigDecimal valor, double quantidadeVendida, boolean cancelado, boolean vendaCancelada) {
        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.idEStoque = idEStoque;
        this.tipoDePreco = tipoDePreco;
        this.valor = valor;
        this.quantidadeVendida = quantidadeVendida;
        this.cancelado = cancelado;
        this.vendaCancelada = vendaCancelada;
    }

    public long getIdVendaItem() {
        return idVendaItem;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getIdEStoque() {
        return idEStoque;
    }

    public void setIdEStoque(long idEStoque) {
        this.idEStoque = idEStoque;
    }

    public TipoDePreco getTipoDePreco() {
        return tipoDePreco;
    }

    public void setTipoDePreco(TipoDePreco tipoDePreco) {
        this.tipoDePreco = tipoDePreco;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public double getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(double quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public boolean isVendaCancelada() {
        return vendaCancelada;
    }

    public void setVendaCancelada(boolean vendaCancelada) {
        this.vendaCancelada = vendaCancelada;
    }
}