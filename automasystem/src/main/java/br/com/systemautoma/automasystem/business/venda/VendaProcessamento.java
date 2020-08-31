package br.com.systemautoma.automasystem.business.venda;

import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import br.com.systemautoma.automasystem.entity.Cliente;
import br.com.systemautoma.automasystem.entity.Pagamento;
import br.com.systemautoma.automasystem.entity.Venda;
import br.com.systemautoma.automasystem.entity.VendaItem;
import br.com.systemautoma.automasystem.exceptions.BusinessVendaExpection;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendaProcessamento {

    public Venda abreVenda(){

        Venda venda = new Venda();
        venda.setCliente(new Cliente());
        venda.getCliente().setNome("CONSUMIDOR");
        venda.getCliente().setIdCliente(1L);
        return venda;
    }

    public void lancaItem(Venda venda, VendaItem item){
        venda.getItens().add(item);
    }

    public void removeItem(Venda venda, VendaItem item){
        if (venda.getItens().contains(item)) {
            venda.getItens().remove(item);
        }
    }

    public void removeItemLogicamente(Venda venda, VendaItem item){
        if (venda.getItens().contains(item)) {
            venda.getItens().get(abreVenda().getItens().indexOf(item)).setCancelado(true);
        }
    }

    public List aplicaRateioDeDescontoTotal(Venda venda, BigDecimal descontoTotal){
        //TODO: melhorar esse metodo
        List<VendaItem> itensAtivos = venda.getItens().stream().filter(item -> !item.isCancelado()).collect(Collectors.toList());
        BigDecimal rateio = descontoTotal.divide(new BigDecimal(itensAtivos.size()));

        venda.getItens().stream().filter(item -> !item.isCancelado()).forEach(item -> {
            item.setValor(item.getValor().subtract(rateio));
        });
        return venda.getItens();
    }

    public List aplicaRateioDeAcrescimoTotal(Venda venda, BigDecimal acrescimoTotal){
        //TODO: melhorar esse metodo
        List<VendaItem> itensAtivos = venda.getItens().stream().filter(item -> !item.isCancelado()).collect(Collectors.toList());
        BigDecimal rateio = acrescimoTotal.divide(new BigDecimal(itensAtivos.size()));

        venda.getItens().stream().filter(item -> !item.isCancelado()).forEach(item -> {
            item.setValor(item.getValor().add(rateio));
        });
        return venda.getItens();
    }

    public BigDecimal valorTotalVenda(Venda venda) throws BusinessVendaExpection {
        if (venda.getItens() == null || venda.getItens().size() < 1){
            throw new BusinessVendaExpection("Não há Itens Lançados para essa Venda");
        }
        BigDecimal valorTotal = venda.getItens().stream().filter(item -> !item.isCancelado())
                .map(item -> item.getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        venda.setValorTotal(valorTotal);

        return venda.getValorTotal();
    }

    public Boolean cancelarVenda(Venda venda){
        if (venda.getItens() != null && venda.getItens().size() >1) {
            cancelaTodosItensDaVenda(venda);
        }
        if (venda.getPagamentos() != null && venda.getPagamentos().size() >1){
            cancelaTodosOsPagamentosDaVenda(venda);
        }
        venda.setVendaCancelada(true);
        return true;
    }

    private void cancelaTodosOsPagamentosDaVenda(Venda venda) {
        venda.getPagamentos().forEach( pagamento ->
                pagamento.setStatusPagamento(StatusPagamento.CANCELADO));
    }

    private void cancelaTodosItensDaVenda(Venda venda) {
        if (venda.getItens()!= null) {
            venda.getItens().forEach(item -> item.setCancelado(true));
        }
    }

    public BigDecimal lancaPagamento(Venda venda, BigDecimal valor, TipoPagamento tipoPagamento) throws BusinessVendaExpection {

        if (venda.getValorRestante() == null){
            if (venda.getValorTotal() == null ) {
                venda.setValorTotal(this.valorTotalVenda(venda));
            }
            venda.setValorRestante(venda.getValorTotal());
        }
        if (venda.getValorRestante().compareTo(new BigDecimal(0)) == 1){
            Pagamento pagamento = atribuiPagamento(venda, valor, tipoPagamento);

            if (venda.getValorRestante().compareTo(new BigDecimal(0)) == 1){
                return retornaValorRestanteAposPagamento(venda);

            } else {
                venda.setTroco(new BigDecimal(venda.getValorRestante().toString()).abs());
                venda.setStatusPagamento(StatusPagamento.PAGO);
                return venda.getValorRestante();
            }

        } else {
            throw new BusinessVendaExpection("Venda sem saldo para efetuar Pagamento");
        }
    }

    private BigDecimal retornaValorRestanteAposPagamento(Venda venda) {
        venda.setStatusPagamento(StatusPagamento.PENDENTE);
        return venda.getValorRestante();
    }

    private Pagamento atribuiPagamento(Venda venda, BigDecimal valor, TipoPagamento tipoPagamento) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(valor);
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setIdVenda(venda.getIdVenda());
        pagamento.setStatusPagamento(StatusPagamento.PAGO);
        if (venda.getPagamentos() == null) {
            venda.setPagamentos(new ArrayList<Pagamento>());
        }
        venda.getPagamentos().add(pagamento);
        venda.setValorRestante(venda.getValorRestante().subtract(valor));
        return pagamento;
    }

}
