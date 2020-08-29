package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.domain.TipoDePreco;
import br.com.systemautoma.automasystem.entity.VendaItem;

import java.math.BigDecimal;

public class VendaItemMother {

    public static VendaItem getVendaItem() {

        VendaItem item = new VendaItem();
        item.setCancelado(false);
        item.setIdCliente(1L);
        item.setIdEStoque(1L);
        item.setIdProduto(1L);
        item.setIdVenda(1L);
        item.setQuantidadeVendida(1);
        item.setTipoDePreco(TipoDePreco.VAREJO);
        item.setIdEmpresa(1L);
        item.setValor(new BigDecimal(10));

        return item;
    }
}
