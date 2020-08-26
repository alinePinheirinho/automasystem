package br.com.systemautoma.automasystem.business.venda;

import br.com.systemautoma.automasystem.business.PrecoDeVenda;
import br.com.systemautoma.automasystem.domain.TipoDePreco;

import java.math.BigDecimal;

public class PrecoDeVendaPromocional implements PrecoDeVenda {
    @Override
    public BigDecimal getPrecoDeVenda(TipoDePreco preco, long idProduto) {
        return null;
    }

    @Override
    public void setPrecoDeVenda(TipoDePreco preco, BigDecimal valor, long idProduto) {

    }
}
