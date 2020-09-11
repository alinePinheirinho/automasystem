package br.com.systemautoma.automasystem.business;

import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;

import java.math.BigDecimal;

public interface PrecoDeVenda {

    BigDecimal getPrecoDeVenda (ProdutoDto produto, long filial) throws ProdutoException;
    void setPrecoDeVenda (BigDecimal valor, ProdutoDto produto, long filial);
}
