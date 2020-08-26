package br.com.systemautoma.automasystem.domain.enumerador;

import br.com.systemautoma.automasystem.business.PrecoDeVenda;

public enum TipoPagamento {

    DINHEIRO (1),
    CARTAO_DEBITO (2),
    CARTAO_CREDITO (3),
    CHEQUE (4),
    TICKET (5),
    VALE_ALIMENTACAO (6),
    VALE_REFEICAO (7),
    PICPAY (8),
    BOLETO (9),
    DEPOSITO (10),
    PARCELADO_LOJA (11),
    IFOOD (12);

    private int codigoTipoPagamento;
    private String nomeTipoPagamento;

    TipoPagamento(int codigoTipoPagamento) {
        this.codigoTipoPagamento = codigoTipoPagamento;
    }

    public int getCodigoTipoPagamento() {
        return codigoTipoPagamento;
    }

    public String getNomeTipoPagamento() {
        return nomeTipoPagamento;
    }
}
