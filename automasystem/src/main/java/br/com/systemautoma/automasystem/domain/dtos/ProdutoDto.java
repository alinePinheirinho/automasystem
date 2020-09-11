package br.com.systemautoma.automasystem.domain.dtos;

import br.com.systemautoma.automasystem.domain.enumerador.TipoDeProduto;
import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.entity.GradeDoProduto;
import br.com.systemautoma.automasystem.entity.Preco;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotBlank;

public class ProdutoDto {

    private long idProduto;
    @NotBlank
    private String nomeProduto;
    private String descricaoProduto;
    private Set<Preco> precos;
    private Collection<Estoque> estoques;
    private boolean ativo = true;
    private long codBarras;
    private String codPersonalizado;
    private Set<GradeDoProduto> gradeDoProdutos;
    private TipoDeProduto tipoDeProduto;

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
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

    public TipoDeProduto getTipoDeProduto() {
        return tipoDeProduto;
    }

    public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
        this.tipoDeProduto = tipoDeProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDto that = (ProdutoDto) o;
        return idProduto == that.idProduto &&
                ativo == that.ativo &&
                codBarras == that.codBarras &&
                Objects.equals(nomeProduto, that.nomeProduto) &&
                Objects.equals(descricaoProduto, that.descricaoProduto) &&
                Objects.equals(precos, that.precos) &&
                Objects.equals(estoques, that.estoques) &&
                Objects.equals(codPersonalizado, that.codPersonalizado) &&
                Objects.equals(gradeDoProdutos, that.gradeDoProdutos) &&
                tipoDeProduto == that.tipoDeProduto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, nomeProduto, descricaoProduto, precos, estoques, ativo, codBarras,
                codPersonalizado, gradeDoProdutos, tipoDeProduto);
    }
}
