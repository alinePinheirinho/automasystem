package br.com.systemautoma.automasystem.domain.dtos;

import br.com.systemautoma.automasystem.entity.PermissaoUser;
import java.util.List;

public class UsuarioDto {

    private long idUsuario;
    private String nome;
    private String login;
    private String senha;
    private String funcao;
    private List<PermissaoUser> permissoes;
    private Boolean habilitado;

    public UsuarioDto() {
    }

    public UsuarioDto(long idUsuario, String nome, String login, String senha, String funcao, List<PermissaoUser> permissoes, Boolean habilitado) {
        this.idUsuario = idUsuario;
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
}
