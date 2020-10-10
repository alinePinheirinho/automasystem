package br.com.systemautoma.automasystem.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class PermissaoUser implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermissao;
    private String nomePermissao;

    @ManyToMany(mappedBy ="permissoes")
    private List<Usuario> permissoes;

    public PermissaoUser() { }

    public PermissaoUser(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    public long getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(long idPermissao) {
        this.idPermissao = idPermissao;
    }

    public String getNomePermissao() {
        return nomePermissao;
    }

    public void setNomePermissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    @Override
    public String getAuthority() {
        return this.nomePermissao;
    }
}
