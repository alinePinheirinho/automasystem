package br.com.systemautoma.automasystem.domain.enumerador;

public enum TipoDeEndereco {

    PRINCIPAL(1),
    ENTREGA(2),
    COMERCIAL(3);

    private String tipoDeEndereco;
    private int codTipodeEndereco;

    TipoDeEndereco(int codTipodeEndereco) {
        this.codTipodeEndereco = codTipodeEndereco;
    }

    public String getTipoDeEndereco() {
        return tipoDeEndereco;
    }

    public int getCodTipodeEndereco() {
        return codTipodeEndereco;
    }
}
