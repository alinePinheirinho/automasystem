package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.PermissaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoUserRepository extends JpaRepository <PermissaoUser, Long> {

    PermissaoUser findByNomePermissao(String nomePermissao);
    Optional <PermissaoUser>  findByNomePermissaoIgnoreCase(String nomePermissao);
}
