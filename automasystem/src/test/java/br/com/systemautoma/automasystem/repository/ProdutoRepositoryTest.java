package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.Produto;
import br.com.systemautoma.automasystem.mother.ProdutoMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository repositoryProduto;

    @Autowired
    FilialRepository repositoryFilial;

    @Autowired
    EmpresaRepository repositoryEmpresa;

    @Autowired
    EnderecoRepository repositoryEndereco;

    @Autowired
    EstoqueRepository repositoryEstoque;

    @Autowired
    PrecoRepository repositoryPreco;

    @Test
    public void cadastroDeProdutoEEstoqueTest(){

        Produto produto = ProdutoMother.getProduto();
        repositoryProduto.save(produto);
    }
}
