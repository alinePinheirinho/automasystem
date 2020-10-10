package br.com.systemautoma.automasystem.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idUsuario;
    private String nome;
    private String login;
    private String senha;
    private String funcao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissoes",
    joinColumns = @JoinColumn(name = "usuario_id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "permissoes_id_permissao"),
    foreignKey = @ForeignKey(name = "fk_usuario_permissao"),
    inverseForeignKey = @ForeignKey(name = "fk_permissao_usuario"))
    private List<PermissaoUser> permissoes = new ArrayList<>();
    private Boolean habilitado;

    public Usuario(){}

    public Usuario(String nome, String login, String senha, String funcao, List permissoes, Boolean habilitado) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.funcao = funcao;
        this.permissoes = permissoes;
        this.habilitado = habilitado;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<PermissaoUser> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoUser> permissoes) {
        this.permissoes = permissoes;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(login, usuario.login) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(funcao, usuario.funcao) &&
                Objects.equals(permissoes, usuario.permissoes) &&
                Objects.equals(habilitado, usuario.habilitado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nome, login, senha, funcao, permissoes, habilitado);
    }
}
