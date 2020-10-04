package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DynamicInsert
@DynamicUpdate
public class VendaParcelada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVendaParcelada;
    private long idVenda;
    private long idFilial;
    @ManyToOne
    private Cliente cliente;
    private BigDecimal valorVenda;
    private BigDecimal valorParcelado;
    private BigDecimal valorParcela;
    private int quantidadeParcelas;
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusParcela;
    private LocalDate dataVencimento;
    private LocalDate dataVenda;

    public VendaParcelada() { }

    public VendaParcelada(long idVenda, long idFilial, Cliente cliente, BigDecimal valorVenda, BigDecimal valorParcelado,
                          BigDecimal valorParcela, int quantidadeParcelas, TipoPagamento tipoPagamento, StatusPagamento statusParcela,
                           LocalDate dataVencimento, LocalDate dataVenda ) {
        this.idVenda = idVenda;
        this.idFilial = idFilial;
        this.cliente = cliente;
        this.valorVenda = valorVenda;
        this.valorParcelado = valorParcelado;
        this.valorParcela = valorParcela;
        this.quantidadeParcelas = quantidadeParcelas;
        this.tipoPagamento = tipoPagamento;
        this.statusParcela = statusParcela;
        this.dataVencimento =dataVencimento;
        this.dataVenda = dataVenda;
    }

    public long getIdVendaParcelada() {
        return idVendaParcelada;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(long idFilial) {
        this.idFilial = idFilial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getValorParcelado() {
        return valorParcelado;
    }

    public void setValorParcelado(BigDecimal valorParcelado) {
        this.valorParcelado = valorParcelado;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusParcela() {
        return statusParcela;
    }

    public void setStatusParcela(StatusPagamento statusParcela) {
        this.statusParcela = statusParcela;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
