package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.PermissaoUser;
import br.com.systemautoma.automasystem.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoUserRepository permissaoUserRepository;

    @Test
    public void cadastraUsuario(){

        Usuario user = new Usuario();
        user.setNome("Bruno");
        user.setLogin("bruninho");
        user.setFuncao("adm");
        user.setHabilitado(true);
        user.setSenha("$2a$10$j2mcgTrGeY4TE5iWTzHIUu.95eqTjV4JiFD9kcC9F4jgCH8vNvXcC");

        PermissaoUser permissao = new PermissaoUser("user");

        PermissaoUser permissaoUser = permissaoUserRepository.findByNomePermissaoIgnoreCase(permissao.getNomePermissao())
                .orElse(permissaoUserRepository.saveAndFlush(permissao));

        user.getPermissoes().add(permissaoUser);
        usuarioRepository.saveAndFlush(user);
    }


}
