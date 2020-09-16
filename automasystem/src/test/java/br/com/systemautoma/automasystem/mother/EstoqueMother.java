package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.entity.Filial;
import br.com.systemautoma.automasystem.entity.Produto;

public class EstoqueMother {

    public static Estoque getUmEstoqueParaOProduto(long idProduto){
        Estoque estoque = new Estoque(1L, 10L,
                1L,100, idProduto);

        estoque.setIdEstoque(1L);
        return estoque;
    }
}
