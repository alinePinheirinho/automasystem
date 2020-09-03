package br.com.systemautoma.automasystem.domain.converter;

import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.entity.Produto;
import br.com.systemautoma.automasystem.mother.ProdutoMother;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ConverterTest {

    @Test
    public void converterProdutoParaProdutoDtoTest() {
        Produto produto = ProdutoMother.getProduto();
        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(produto);

        Assert.assertNotNull(produtoDto);
        assertEquals(produto.getNomeProduto(), produtoDto.getNomeProduto());
        assertEquals(produto.getCodPersonalizado(), produtoDto.getCodPersonalizado());
    }

    @Test
    public void converterProdutoDtoParaProdutoTest() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setNomeProduto("mamao");
        produtoDto.setIdProduto(123L);
        Produto produto = Converter.INSTANCE.ProdutoDtoToProduto(produtoDto);

        System.out.println("id produto dto = "+produtoDto.getIdProduto());
        System.out.println("id produto"+ produto.getIdProduto());

        Assert.assertNotNull(produto);
        assertEquals(produtoDto.getNomeProduto(), produto.getNomeProduto());
        assertEquals(produtoDto.getIdProduto(), produto.getIdProduto(),0.0001);
    }
}
