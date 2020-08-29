package br.com.systemautoma.automasystem.business.venda;

import br.com.systemautoma.automasystem.entity.Cliente;
import br.com.systemautoma.automasystem.entity.Venda;
import br.com.systemautoma.automasystem.entity.VendaItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendaProcessamento {

    private List<VendaItem> itens = new ArrayList<>();

    public Venda abreVenda(){

        Venda venda = new Venda();
        venda.setCliente(new Cliente());
        venda.getCliente().setNome("CONSUMIDOR");
        venda.getCliente().setIdCliente(1L);

        return venda;
    }

    public void lancaItens(VendaItem item){
        itens.add(item);
    }

    public void removeItem(VendaItem item){
        if (itens.contains(item)) {
            itens.remove(item);
        }
    }

    public void removeItemLogicamente(VendaItem item){
        if (itens.contains(item)) {
            itens.get(itens.indexOf(item)).setCancelado(true);
        }
    }

    public List aplicaRateioDeDescontoTotal(BigDecimal descontoTotal){

        List<VendaItem> itensAtivos = itens.stream().filter(item -> !item.isCancelado()).collect(Collectors.toList());
        BigDecimal rateio = descontoTotal.divide(new BigDecimal(itensAtivos.size()));

        itens.stream().filter(item -> !item.isCancelado()).forEach(item -> {
            item.setValor(item.getValor().subtract(rateio));
        });
        return itens;
    }

    public BigDecimal valorTotalVenda(Venda venda){

        return itens.stream().filter(item -> !item.isCancelado())
                .map(item -> item.getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Boolean cancelarVenda(Venda venda){
        try {
            venda.setVendaCancelada(true);
            venda.getItens().forEach(item -> item.setCancelado(true));
            //TODO: chamar metodo para salvar na base
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public List<VendaItem> getItens() {
        return itens;
    }

    public void setItens(List<VendaItem> itens) {
        this.itens = itens;
    }
}
