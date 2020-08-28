package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.domain.enumerador.TipoDeProduto;
import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.entity.Preco;
import br.com.systemautoma.automasystem.entity.Produto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoMother {

    public static Produto getProduto(){

        Produto produto = new Produto();
        produto.setNomeProduto("banana");
        produto.setCodPersonalizado("123");
        List<Estoque> estoques = Arrays.asList(EstoqueMother.getUmEstoqueParaOProduto(1L));
        produto.setEstoques(estoques);
        Set<Preco> precos = new HashSet<>();
        precos.add(PrecoMother.getUmPrecoParaOProduto(produto));
        produto.setPrecos(precos);
        produto.setTipoDeProduto(TipoDeProduto.FINAL);

        return produto;
    }
}
