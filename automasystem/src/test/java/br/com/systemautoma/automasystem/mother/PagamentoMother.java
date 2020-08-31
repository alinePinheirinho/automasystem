package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import br.com.systemautoma.automasystem.entity.Pagamento;
import br.com.systemautoma.automasystem.entity.Venda;

import java.math.BigDecimal;

public class PagamentoMother {

    public static Pagamento getUmPagamentoEmDinheiro(Venda venda){

       return new Pagamento(venda.getIdVenda(), TipoPagamento.DINHEIRO,
                new BigDecimal(40),1l, StatusPagamento.PAGO);
    }
}
