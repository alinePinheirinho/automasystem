package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.TipoDeProduto;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "idPreco")
    private Set<Preco> precos;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "idEstoque")
    private Collection<Estoque> estoques;
    private boolean ativo = true;
    private long codBarras;
    private String codPersonalizado;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "idGrade")
    private Set<GradeDoProduto> gradeDoProdutos;
    @Enumerated(EnumType.STRING)
    private TipoDeProduto tipoDeProduto;


    public Produto() { }

    public Produto(String nomeProduto, String descricaoProduto, Set<Preco> precos, Set<Estoque> estoques,
                   boolean ativo, long codBarras, String codPersonalizado, Set<GradeDoProduto> gradeDoProdutos) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precos = precos;
        this.estoques = estoques;
        this.ativo = ativo;
        this.codBarras = codBarras;
        this.codPersonalizado = codPersonalizado;
        this.gradeDoProdutos = gradeDoProdutos;
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

    public Set<Preco> getPrecos() {
        return precos;
    }

    public void setPrecos(Set<Preco> precos) {
        this.precos = precos;
    }

    public Collection<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(Collection<Estoque> estoques) {
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

    public Set<GradeDoProduto> getGradeDoProdutos() {
        return gradeDoProdutos;
    }

    public void setGradeDoProdutos(Set<GradeDoProduto> gradeDoProdutos) {
        this.gradeDoProdutos = gradeDoProdutos;
    }

    public TipoDeProduto getTipoDeProduto() { return tipoDeProduto; }

    public void setTipoDeProduto(TipoDeProduto tipoDeProduto) { this.tipoDeProduto = tipoDeProduto; }
}
