package br.com.systemautoma.automasystem.controller;

import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;
import br.com.systemautoma.automasystem.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Validated
@RequestMapping("/produtos")
@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "produto", method = RequestMethod.POST)
    public void cadastrarProduto(@Valid ProdutoDto produtoDto){

        produtoService.cadastrarProduto(produtoDto);

    }

    @ResponseBody
    @RequestMapping(value = "produto/{id}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ProdutoDto bucarProduto(@PathVariable(value = "id") long id) {

        try {
            return produtoService.buscarProdutoPorId(id);

        } catch (ProdutoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
