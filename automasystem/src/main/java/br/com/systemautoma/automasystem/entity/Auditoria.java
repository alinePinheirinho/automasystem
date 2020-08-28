package br.com.systemautoma.automasystem.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditoria {

    @CreatedDate
    protected LocalDateTime dataCriacao;

    @LastModifiedDate
    protected LocalDateTime dataAtualizacao;

    @CreatedBy
    protected Usuario usuarioCriacao;

    @LastModifiedBy
    protected Usuario usuarioModificacao;



    public Auditoria(LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, Usuario usuario) {
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.usuarioCriacao = usuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Usuario getUsuario() {
        return usuarioCriacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioCriacao = usuario;
    }

    public Usuario getUsuarioModificacao() { return usuarioModificacao; }

    public void setUsuarioModificacao(Usuario usuarioModificacao) { this.usuarioModificacao = usuarioModificacao; }
}
