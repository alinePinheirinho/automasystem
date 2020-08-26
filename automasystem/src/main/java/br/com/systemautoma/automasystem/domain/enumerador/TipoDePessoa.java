package br.com.systemautoma.automasystem.domain.enumerador;

public enum TipoDePessoa {

    FISICA(1),
    JURIDICA(2);

    private String tipoPessoa;
    private int codTipoPessoa;

    TipoDePessoa(int codigoTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public int getCodTipoPessoa() {
        return codTipoPessoa;
    }
}
