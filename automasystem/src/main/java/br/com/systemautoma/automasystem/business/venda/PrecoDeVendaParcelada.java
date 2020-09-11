package br.com.systemautoma.automasystem.business.venda;

import br.com.systemautoma.automasystem.business.PrecoDeVenda;
import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;

import java.math.BigDecimal;

public class PrecoDeVendaParcelada implements PrecoDeVenda {

    @Override
    public BigDecimal getPrecoDeVenda(ProdutoDto produto, long filial) throws ProdutoException {
        return produto.getPrecos().stream().filter(preco1 -> preco1.getIdFilial() == filial)
                .map(preco1 -> preco1.getPrecoParcelado()).findFirst()
                .orElseThrow(() -> new ProdutoException("Produto nao localizado para a Filial " +
                        "selecionada ou nao possui preço cadastrado para Venda Parcelado"));
    }

    @Override
    public void setPrecoDeVenda(BigDecimal valor, ProdutoDto produto, long filial) {
        produto.getPrecos().stream().filter(preco1 -> preco1.getIdFilial() == filial)
                .forEach(preco1 -> preco1.setPrecoParcelado(valor));
    }
}
