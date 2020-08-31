package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
public class Pagamento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idPagamento;
    private long idVenda;
    private long idFilial;
    private TipoPagamento tipoPagamento;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;

    public Pagamento(){ }

    public Pagamento(long idVenda, TipoPagamento tipoPagamento, BigDecimal valor, long idFilial, StatusPagamento statusPagamento) {
        this.idVenda = idVenda;
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
        this.idFilial = idFilial;
        this.statusPagamento = statusPagamento;
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

    public long getIdFilial() { return idFilial; }

    public void setIdFilial(long idFilial) { this.idFilial = idFilial; }

    public StatusPagamento getStatusPagamento() { return statusPagamento; }

    public void setStatusPagamento(StatusPagamento statusPagamento) { this.statusPagamento = statusPagamento; }
}
