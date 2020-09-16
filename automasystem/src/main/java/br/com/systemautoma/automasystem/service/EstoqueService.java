package br.com.systemautoma.automasystem.service;

import br.com.systemautoma.automasystem.domain.converter.Converter;
import br.com.systemautoma.automasystem.domain.dtos.EstoqueDto;
import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.exceptions.EstoqueException;
import br.com.systemautoma.automasystem.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    public Estoque baixaEstoqueAposVenda(long idEStoque, double quantidadeVendida) {

        Estoque estoque = estoqueRepository.findById(idEStoque).orElseThrow( () ->
                new EstoqueException("Id do Estoque nao localizado na base de Dados"));

        double quantidade = estoque.getQuantidade();
        double totalAtualDeEstoque = quantidade - quantidadeVendida;
        estoque.setQuantidade(totalAtualDeEstoque);

        return estoqueRepository.save(estoque);
    }

    public EstoqueDto buscaEstoqueporId(long idEStoque) {

        Estoque estoque = estoqueRepository.findById(idEStoque).orElseThrow( () ->
                new EstoqueException("Id do Estoque nao localizado na base de Dados"));

        EstoqueDto estoqueDto = Converter.INSTANCE.EstoqueToEstoqueDto(estoque);
        return estoqueDto;

    }
}
