package br.com.systemautoma.automasystem.entity;

import br.com.systemautoma.automasystem.domain.enumerador.TipoDePessoa;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idCliente;
    private String nome;
    private String nomeFantasia;
    private long documento;
    private String cpf;
    private String cnpj;
    @Enumerated(EnumType.STRING)
    private TipoDePessoa tipoPessoa;
    @ManyToMany
    List<Endereco> enderecos;
    private String Responsavel;
    private String observacao;
    private String telefone;
    private String celular;
    private String telefoneComercial;

    public Cliente(String nome, String nomeFantasia, long documento, String cpf, String cnpj, TipoDePessoa tipoPessoa,
                   List<Endereco> enderecos, String responsavel, String observacao, String telefone, String celular,
                   String telefoneComercial) {
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.documento = documento;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipoPessoa = tipoPessoa;
        this.enderecos = enderecos;
        Responsavel = responsavel;
        this.observacao = observacao;
        this.telefone = telefone;
        this.celular = celular;
        this.telefoneComercial = telefoneComercial;
    }

    public Cliente(){}

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }
}
