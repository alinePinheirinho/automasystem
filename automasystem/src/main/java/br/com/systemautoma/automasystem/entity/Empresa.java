package br.com.systemautoma.automasystem.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
public class Empresa {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEmpresa;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicial;
    private String cnae;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco", referencedColumnName = "idEndereco")
    private Endereco endereco;

    @OneToMany( mappedBy = "empresa", cascade=CascadeType.ALL)
    private List<Filial> filiais;

    public Empresa() { }
    public Empresa(String razaoSocial, String nomeFantasia,
                   String cnpj, String inscricaoEstadual, String inscricaoMunicial, String cnae, Endereco endereco) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicial = inscricaoMunicial;
        this.cnae = cnae;
        this.endereco = endereco;
    }

    public long getIdEmpresa() {
        return idEmpresa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicial() {
        return inscricaoMunicial;
    }

    public void setInscricaoMunicial(String inscricaoMunicial) {
        this.inscricaoMunicial = inscricaoMunicial;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
