package br.com.systemautoma.automasystem.business;

import br.com.systemautoma.automasystem.domain.TipoDePreco;

import java.math.BigDecimal;

public interface PrecoDeVenda {

    BigDecimal getPrecoDeVenda (TipoDePreco preco, long idProduto);
    void setPrecoDeVenda (TipoDePreco preco, BigDecimal valor, long idProduto);
}
