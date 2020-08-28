package br.com.systemautoma.automasystem.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DynamicInsert
@DynamicUpdate
public class Preco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPreco;
    /*
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "idFilial", referencedColumnName = "idFilial")
    private Filial filial;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "idProduto", referencedColumnName = "idProduto")
    private Produto produto;

    TODO: validar e remover
     */
    private long idFilial;
    private BigDecimal precoVendaVarejo;
    private BigDecimal precoVendaAtacado;
    private BigDecimal precoVendaCompra;
    private BigDecimal precoPromocional;
    private BigDecimal precoParcelado;
    private long idProduto;


    public Preco(long idFilial, BigDecimal precoVendaVarejo, BigDecimal precoVendaAtacado,
                 BigDecimal precoVendaCompra, BigDecimal precoPromocional, BigDecimal precoParcelado, long idProduto) {
        this.idFilial = idFilial;
        this.precoVendaVarejo = precoVendaVarejo;
        this.precoVendaAtacado = precoVendaAtacado;
        this.precoVendaCompra = precoVendaCompra;
        this.precoPromocional = precoPromocional;
        this.precoParcelado = precoParcelado;
        this.idProduto = idProduto;
    }

    public long getIdPreco() {
        return idPreco;
    }

    public long getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(long idFilial) {
        this.idFilial = idFilial;
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

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long produto) {
        this.idProduto = produto;
    }
}
