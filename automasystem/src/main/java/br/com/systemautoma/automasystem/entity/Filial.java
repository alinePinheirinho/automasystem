package br.com.systemautoma.automasystem.entity;

public class Filial {

    private long idFilial;
    private String nomeFilal;
    private Empresa empresa;
    private Configuracao configuracao;

    public long getIdFilial() {
        return idFilial;
    }

    public String getNomeFilal() {
        return nomeFilal;
    }

    public void setNomeFilal(String nomeFilal) {
        this.nomeFilal = nomeFilal;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }
}
