package br.com.systemautoma.automasystem.domain.converter;

import br.com.systemautoma.automasystem.domain.dtos.EstoqueDto;
import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.entity.Produto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface Converter {

    Converter INSTANCE = Mappers.getMapper( Converter.class );

    //@Mapping(source = "type", target = "dataType")
    Produto ProdutoDtoToProduto(ProdutoDto produtoDto);

    @InheritInverseConfiguration
    ProdutoDto ProdutoToProdutoDto(Produto produto);

    Estoque EstoqueDtoToEstoque(EstoqueDto estoqueDto);

    @InheritInverseConfiguration
    EstoqueDto EstoqueToEstoqueDto(Estoque estoque);

}
