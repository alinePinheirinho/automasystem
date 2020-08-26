package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.TipoDePessoa;

import java.util.List;

public class Cliente {

    long idCliente;
    private String nome;
    private String nomeFantasia;
    private long documento;
    private String cpf;
    private String cnpj;
    private TipoDePessoa tipoPessoa;
    List<Endereco> enderecos;
    private String Responsavel;
    private String observacao;

    public Cliente(){}

    public Cliente(String nome, String nomeFantasia, long documento, String cpf, String cnpj,
            TipoDePessoa tipoPessoa, List<Endereco> enderecos, String responsavel, String observacao) {
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.documento = documento;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipoPessoa = tipoPessoa;
        this.enderecos = enderecos;
        Responsavel = responsavel;
        this.observacao = observacao;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TipoDePessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoDePessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String responsavel) {
        Responsavel = responsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
