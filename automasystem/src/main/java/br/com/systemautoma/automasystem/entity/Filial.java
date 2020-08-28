package br.com.systemautoma.automasystem.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
public class Filial {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idFilial;
    private String nomeFilal;
    @ManyToOne (targetEntity=Empresa.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa", referencedColumnName = "idEmpresa")
    private Empresa empresa;
    @OneToOne
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
