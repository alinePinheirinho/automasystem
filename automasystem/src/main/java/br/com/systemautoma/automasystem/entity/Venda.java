package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.TipoDePreco;
import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Venda {

    private long idVenda;
    private long idFilial;
    private List<VendaItem> itens;
    private Cliente cliente;
    private TipoDePreco tipoDePreco;
    @Transient
    private BigDecimal ValorRestante;
    private double volume;
    private BigDecimal valorItens;
    private BigDecimal valorTotalDeDescontos;
    private BigDecimal valorTotalDeAcrescimos;
    private BigDecimal valorTotal;
    private List<Pagamento> pagamentos;
    private BigDecimal troco;
    private boolean vendaCancelada = false;
    private String statusVenda = "aberta";
    private StatusPagamento statusPagamento;
    @Transient
    private List<VendaParcelada> parcelas;

    public Venda() { }

    public Venda(long idEmpresa, List<VendaItem> itens, Cliente cliente, TipoDePreco tipoDePreco, BigDecimal valor, double volume,
                 BigDecimal valorItens, BigDecimal valorTotalDeDescontos, BigDecimal valorTotalDeAcrescimos, BigDecimal valorTotal,
                 List<Pagamento> paramento, BigDecimal troco,
                 boolean vendaCancelada, StatusPagamento statusPagamento) {
        this.itens = itens;
        this.cliente = cliente;
        this.tipoDePreco = tipoDePreco;
        this.ValorRestante = valor;
        this.volume = volume;
        this.valorItens = valorItens;
        this.valorTotalDeDescontos = valorTotalDeDescontos;
        this.valorTotalDeAcrescimos = valorTotalDeAcrescimos;
        this.valorTotal = valorTotal;
        this.pagamentos = paramento;
        this.troco = troco;
        this.vendaCancelada = vendaCancelada;
        this.idVenda = 1001+1+ idEmpresa + new Date().getTime();
        this.idFilial = idEmpresa;
        this.statusPagamento = statusPagamento;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        //TODO: alterar isso, provisorio!
        if (this.idVenda <=0) {
            this.idVenda = 1001+1+ new Date().getTime();
        }
        this.idVenda = idVenda;
    }

    public List<VendaItem> getItens() {
        return itens;
    }

    public void setItens(List<VendaItem> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoDePreco getTipoDePreco() {
        return tipoDePreco;
    }

    public void setTipoDePreco(TipoDePreco tipoDePreco) {
        this.tipoDePreco = tipoDePreco;
    }

    public BigDecimal getValorRestante() {
        return ValorRestante;
    }

    public void setValorRestante(BigDecimal valorRestante) {
        this.ValorRestante = valorRestante;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public BigDecimal getValorItens() {
        return valorItens;
    }

    public void setValorItens(BigDecimal valorItens) {
        this.valorItens = valorItens;
    }

    public BigDecimal getValorTotalDeDescontos() {
        return valorTotalDeDescontos;
    }

    public void setValorTotalDeDescontos(BigDecimal valorTotalDeDescontos) {
        this.valorTotalDeDescontos = valorTotalDeDescontos;
    }

    public BigDecimal getValorTotalDeAcrescimos() {
        return valorTotalDeAcrescimos;
    }

    public void setValorTotalDeAcrescimos(BigDecimal valorTotalDeAcrescimos) {
        this.valorTotalDeAcrescimos = valorTotalDeAcrescimos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> paramento) {
        this.pagamentos = paramento;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public boolean isVendaCancelada() {
        return vendaCancelada;
    }

    public void setVendaCancelada(boolean vendaCancelada) {
        this.vendaCancelada = vendaCancelada;
    }

    public long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(long idFilial) {
        this.idFilial = idFilial;
    }

    public StatusPagamento getStatusPagamento() { return statusPagamento; }

    public void setStatusPagamento(StatusPagamento statusPagamento) { this.statusPagamento = statusPagamento; }

    public String getStatusVenda() { return statusVenda; }

    public void setStatusVenda(String statusVenda) { this.statusVenda = statusVenda; }

    public List<VendaParcelada> getParcelas() {
        if (parcelas == null){
            parcelas = new ArrayList<>();
        }
        return parcelas;
    }

    public void setParcelas(List<VendaParcelada> parcelas) { this.parcelas = parcelas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return idVenda == venda.idVenda &&
                idFilial == venda.idFilial &&
                Double.compare(venda.volume, volume) == 0 &&
                vendaCancelada == venda.vendaCancelada &&
                Objects.equals(itens, venda.itens) &&
                Objects.equals(cliente, venda.cliente) &&
                tipoDePreco == venda.tipoDePreco &&
                Objects.equals(ValorRestante, venda.ValorRestante) &&
                Objects.equals(valorItens, venda.valorItens) &&
                Objects.equals(valorTotalDeDescontos, venda.valorTotalDeDescontos) &&
                Objects.equals(valorTotalDeAcrescimos, venda.valorTotalDeAcrescimos) &&
                Objects.equals(valorTotal, venda.valorTotal) &&
                Objects.equals(pagamentos, venda.pagamentos) &&
                Objects.equals(troco, venda.troco) &&
                Objects.equals(statusVenda, venda.statusVenda) &&
                statusPagamento == venda.statusPagamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenda, idFilial, itens, cliente, tipoDePreco, ValorRestante, volume, valorItens, valorTotalDeDescontos, valorTotalDeAcrescimos, valorTotal, pagamentos, troco, vendaCancelada, statusVenda, statusPagamento);
    }
}
