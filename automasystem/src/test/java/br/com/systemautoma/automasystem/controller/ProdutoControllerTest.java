package br.com.systemautoma.automasystem.controller;

import br.com.systemautoma.automasystem.domain.converter.Converter;
import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.mother.ProdutoMother;
import br.com.systemautoma.automasystem.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProdutoControllerTest {


    @Autowired
    private WebApplicationContext conext;
    private MockMvc mvc;

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp ()
    {
        MockitoAnnotations.initMocks(this);
        //mvc = MockMvcBuilders.webAppContextSetup(conext).build();
        mvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }


    @Test
    public void buscarProdutoCadastradoTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/produtos/produto/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void cadastrarProdutoTest() throws Exception {

        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(ProdutoMother.getProduto());
        ObjectMapper ow = new ObjectMapper();
        String json = ow.writeValueAsString(produtoDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/produtos/produto/")
                .headers(headers)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(print())
        .andReturn();
    }

}
