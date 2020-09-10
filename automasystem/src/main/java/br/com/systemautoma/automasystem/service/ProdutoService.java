package br.com.systemautoma.automasystem.service;

import br.com.systemautoma.automasystem.domain.converter.Converter;
import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.entity.Produto;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;
import br.com.systemautoma.automasystem.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrarProduto(ProdutoDto produtoDto) {

        Produto produto = Converter.INSTANCE.ProdutoDtoToProduto(produtoDto);
        produtoRepository.save(produto);
    }

    public ProdutoDto buscarProdutoPorId(long id) throws ProdutoException {

       Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Id do Produto nao Localizado"));

        return Converter.INSTANCE.ProdutoToProdutoDto(produto);
    }
}
