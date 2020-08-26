package br.com.systemautoma.automasystem.domain;

public enum TipoDePreco {

    VAREJO (1),
    ATACADO (2),
    COMPRA (3),
    PROMOCAO (4),
    PARCELADO(5);

    private int codTipoPreco;
    private String nomeTipoPreco;

    TipoDePreco(int codTipoPreco) {
        this.codTipoPreco = codTipoPreco;
    }

    public int getCodTipoPreco() {
        return codTipoPreco;
    }

    TipoDePreco(String nomeTipoPreco) {
        this.nomeTipoPreco = nomeTipoPreco;
    }

    public String getNomeTipoPreco() {
        return nomeTipoPreco;
    }
}
