package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.mother.EstoqueMother;
import br.com.systemautoma.automasystem.mother.ProdutoMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstoqueRepositoryTest {

    @Autowired
    EstoqueRepository repository;

    @Test
    public void cadastrarEstoque(){

        Estoque estoque = EstoqueMother.getUmEstoqueParaOProduto(1L);
        repository.save(estoque);
    }
}
