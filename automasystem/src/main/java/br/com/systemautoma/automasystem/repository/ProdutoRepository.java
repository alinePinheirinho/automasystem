package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
