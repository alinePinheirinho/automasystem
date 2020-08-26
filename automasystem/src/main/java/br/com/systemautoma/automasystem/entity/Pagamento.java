package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;

import java.math.BigDecimal;

public class Pagamento {

    private long idPagamento;
    private long idVenda;
    private TipoPagamento tipoPagamento;
    private BigDecimal valor;

    public Pagamento(){ }

    public Pagamento(long idVenda, TipoPagamento tipoPagamento, BigDecimal valor) {
        this.idVenda = idVenda;
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
    }

    public long getIdPagamento() {
        return idPagamento;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
