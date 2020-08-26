package br.com.systemautoma.automasystem.entity;

public class Cor {

    private long idCor;
    private String nomeCor;

    public Cor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    public long getIdCor() {
        return idCor;
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }
}
