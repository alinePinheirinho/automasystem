package br.com.systemautoma.automasystem.entity;

public class Tamanho {

    private long idTamanho;
    private String Tamanho;

    public Tamanho(String tamanho) {
        Tamanho = tamanho;
    }

    public long getIdTamanho() {
        return idTamanho;
    }

    public String getTamanho() {
        return Tamanho;
    }

    public void setTamanho(String tamanho) {
        Tamanho = tamanho;
    }
}
