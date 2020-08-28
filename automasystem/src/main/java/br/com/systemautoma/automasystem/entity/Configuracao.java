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
public class Configuracao {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idConfiguracao;
    private String parametro;
    private String valor;


}
