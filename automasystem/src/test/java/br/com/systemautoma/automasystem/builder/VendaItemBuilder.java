package br.com.systemautoma.automasystem.builder;

import br.com.systemautoma.automasystem.entity.VendaItem;
import br.com.systemautoma.automasystem.mother.VendaItemMother;

import java.math.BigDecimal;

public class VendaItemBuilder {

    static VendaItem itemAltevel;

    public VendaItemBuilder iniciaItemAlteravel(){
        itemAltevel =  VendaItemMother.getVendaItem();
        return this;
    }

    public VendaItemBuilder alteraValor(BigDecimal valor){
        itemAltevel.setValor(valor);
        return this;
    }

    public VendaItemBuilder alteraStatusItemCacelado(Boolean status){
        itemAltevel.setCancelado(status);
        return this;
    }

    public VendaItem eRetorna(){
        return this.itemAltevel;
    }

}


