package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.entity.Preco;
import br.com.systemautoma.automasystem.entity.Produto;

import java.math.BigDecimal;

public class PrecoMother {

    public static Preco getUmPrecoParaOProduto(Produto produto){

        return new Preco(1L,new BigDecimal(200), new BigDecimal(100), new BigDecimal(40),
                new BigDecimal(50),new BigDecimal(350), 1L);
    }
}
