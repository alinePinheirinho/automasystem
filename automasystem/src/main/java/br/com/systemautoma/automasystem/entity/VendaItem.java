package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.TipoDePreco;

import java.math.BigDecimal;
import java.util.Objects;

public class VendaItem {

    private long idVendaItem;
    private long idVenda;
    private long idCliente;
    private long idProduto;
    private long idEStoque;
    private long idEmpresa;
    private TipoDePreco tipoDePreco;
    private BigDecimal valor;
    private double quantidadeVendida;
    private boolean cancelado = false;
    private boolean vendaCancelada = false;

    public VendaItem() { }

    public VendaItem(long idEmpresa, long idVenda, long idCliente, long idProduto, long idEStoque, TipoDePreco tipoDePreco,
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
        this.idEmpresa = idEmpresa;
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

    public long getIdEmpresa() {  return idEmpresa; }

    public void setIdEmpresa(long idEmpresa) { this.idEmpresa = idEmpresa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaItem vendaItem = (VendaItem) o;
        return idVendaItem == vendaItem.idVendaItem &&
                idVenda == vendaItem.idVenda &&
                idCliente == vendaItem.idCliente &&
                idProduto == vendaItem.idProduto &&
                idEStoque == vendaItem.idEStoque &&
                idEmpresa == vendaItem.idEmpresa &&
                Double.compare(vendaItem.quantidadeVendida, quantidadeVendida) == 0 &&
                cancelado == vendaItem.cancelado &&
                vendaCancelada == vendaItem.vendaCancelada &&
                tipoDePreco == vendaItem.tipoDePreco &&
                Objects.equals(valor, vendaItem.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVendaItem, idVenda, idCliente, idProduto, idEStoque, idEmpresa, tipoDePreco, valor, quantidadeVendida, cancelado, vendaCancelada);
    }
}