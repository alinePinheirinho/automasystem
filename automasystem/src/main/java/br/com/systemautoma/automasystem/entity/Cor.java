package br.com.systemautoma.automasystem.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicInsert
@DynamicUpdate
public class Cor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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
