package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.TipoDeEndereco;

public class Endereco {

    private long idEndereco;
    private long idCliente;
    private TipoDeEndereco tipoEndereco;
    private String rua;
    private String numero;
    private String Bairro;
    private String cidade;
    private String estado;
    private String siglaEstado;
    private String cep;

    public Endereco(){}

    public Endereco(TipoDeEndereco tipoEndereco, String rua, String numero,
                    String bairro, String cidade, String estado, String siglaEstado, String cep) {
        this.tipoEndereco = tipoEndereco;
        this.rua = rua;
        this.numero = numero;
        Bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.siglaEstado = siglaEstado;
        this.cep = cep;
    }

    public long getIdEndereco() {
        return idEndereco;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public TipoDeEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoDeEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
