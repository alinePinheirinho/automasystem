package br.com.systemautoma.automasystem.business;


import br.com.systemautoma.automasystem.builder.VendaItemBuilder;
import br.com.systemautoma.automasystem.business.venda.VendaProcessamento;
import br.com.systemautoma.automasystem.domain.TipoDePreco;
import br.com.systemautoma.automasystem.domain.converter.Converter;
import br.com.systemautoma.automasystem.domain.dtos.EstoqueDto;
import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import br.com.systemautoma.automasystem.entity.Produto;
import br.com.systemautoma.automasystem.entity.Venda;
import br.com.systemautoma.automasystem.entity.VendaItem;
import br.com.systemautoma.automasystem.exceptions.BusinessVendaExpection;
import br.com.systemautoma.automasystem.exceptions.EstoqueException;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;
import br.com.systemautoma.automasystem.mother.EstoqueMother;
import br.com.systemautoma.automasystem.mother.PagamentoMother;
import br.com.systemautoma.automasystem.mother.ProdutoMother;
import br.com.systemautoma.automasystem.mother.VendaItemMother;
import br.com.systemautoma.automasystem.repository.ProdutoRepository;
import br.com.systemautoma.automasystem.service.EstoqueService;
import br.com.systemautoma.automasystem.service.ProdutoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
@SpringBootTest
public class VendaProcessamentoTest {

    private VendaItemBuilder builderItem;
    private Venda venda;

    @Autowired
    ProdutoRepository produtoRepository;


    @InjectMocks
    private VendaProcessamento vendaProcessamento;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private EstoqueService estoqueService;

    @BeforeEach
    public void setUp() {
        produtoService = Mockito.mock(ProdutoService.class);
        vendaProcessamento = new VendaProcessamento(produtoService, estoqueService);
        MockitoAnnotations.initMocks(this);

        builderItem = new VendaItemBuilder();
        venda = vendaProcessamento.abreVenda();
        venda.setIdVenda(1L);
        venda.setIdFilial(1L);


    }

    @Test
    public void lancaItemTest(){

        VendaItem item = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();

        Produto produto = ProdutoMother.getProduto();
        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(produto);
        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        EstoqueDto estoqueDto = Converter.INSTANCE.EstoqueToEstoqueDto(EstoqueMother
                .getUmEstoqueParaOProduto(produto.getIdProduto()));
        Mockito.when(estoqueService.buscaEstoqueporId(item.getIdEStoque()))
                .thenReturn(estoqueDto);

        vendaProcessamento.lancaItem(venda, item);
        assertNotNull(venda.getItens());
        assertEquals(item, venda.getItens().get(0));
    }

    @Test
    public void lancaItemComitensDistintosEItensCanceladosTest(){

        VendaItem item = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3JaLancado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).alteraQauntidade(10).eRetorna();
        VendaItem item3JaLancadoECancelado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).alteraQauntidade(10).alteraStatusItemCacelado(true)
                .eRetorna();
        VendaItem item3ASerLancado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).eRetorna();

        venda.getItens().addAll(Arrays.asList(item, item2,item3JaLancado,item3JaLancadoECancelado));

        Produto produto = ProdutoMother.getProduto();
        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(produto);
        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        EstoqueDto estoqueDto = Converter.INSTANCE.EstoqueToEstoqueDto(EstoqueMother
                .getUmEstoqueParaOProduto(produto.getIdProduto()));
        Mockito.when(estoqueService.buscaEstoqueporId(item2.getIdEStoque()))
                .thenReturn(estoqueDto);

        vendaProcessamento.lancaItem(venda, item3ASerLancado);

        assertNotNull(venda.getItens().get(4));
    }

    @Test
    public void lancaItemComitensDistintosEItensCanceladosEsperaExceptionTest(){

        VendaItem item = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3JaLancado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).alteraQauntidade(10).eRetorna();
        VendaItem item3JaLancadoECancelado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).alteraQauntidade(10).alteraStatusItemCacelado(false)
                .eRetorna();
        VendaItem item3ASerLancado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).eRetorna();

        venda.getItens().addAll(Arrays.asList(item, item2,item3JaLancado,item3JaLancadoECancelado));


        Produto produto = ProdutoMother.getProduto();
        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(produto);
        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        EstoqueDto estoqueDto = Converter.INSTANCE.EstoqueToEstoqueDto(EstoqueMother
                .getUmEstoqueParaOProduto(produto.getIdProduto()));
        Mockito.when(estoqueService.buscaEstoqueporId(item2.getIdEStoque()))
                .thenReturn(estoqueDto);

        Assertions.assertThatThrownBy(() -> vendaProcessamento.lancaItem(venda, item3ASerLancado))
                .isInstanceOf(EstoqueException.class)
                .hasMessage("Os Lancamentos desse Item nessa venda Ultrapassam a Quantidade de estoque existente. " +
                        "Estoque Existente = 10.0 estoque somado para esse item = 20.0");
    }

    @Test
    public void lancaItemNaoPermiteLancarItemSemEstoqueEsperaExceptionTest(){

        VendaItem item = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).alteraQauntidade(10).alteraStatusItemCacelado(false)
                .eRetorna();
        VendaItem itemASerLancado = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdProduto(5).alteraIdDoEstoque(5).eRetorna();

        venda.getItens().add(item);

        Produto produto = ProdutoMother.getProduto();
        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(produto);
        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        EstoqueDto estoqueDto = Converter.INSTANCE.EstoqueToEstoqueDto(EstoqueMother
                .getUmEstoqueParaOProduto(produto.getIdProduto()));
        estoqueDto.setQuantidade(0D);
        Mockito.when(estoqueService.buscaEstoqueporId(Mockito.anyLong()))
                .thenReturn(estoqueDto);

        Assertions.assertThatThrownBy(() -> vendaProcessamento.lancaItem(venda, itemASerLancado))
                .isInstanceOf(EstoqueException.class)
                .hasMessage("Saldo em estoque Insuficiente . Estoque Atual = 0.0");
    }

    @Test
    public void removeItemLogicamenteTest(){

        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        VendaItem item4 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40)).eRetorna();
        venda.setItens(Arrays.asList(item,item2,item3,item4));

        vendaProcessamento.removeItemLogicamente(venda, item2);

        assertTrue(venda.getItens().get(1).isCancelado());
        assertFalse(venda.getItens().get(0).isCancelado());
        assertFalse(venda.getItens().get(3).isCancelado());
    }

    @Test
    public void valorTotalVendaTest() throws BusinessVendaExpection {
        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        venda.setItens(Arrays.asList(item,item2));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(40),total);
    }

    @Test
    public void valorTotalVendaComItemCanceladoTest() throws BusinessVendaExpection {
        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        venda.setItens(Arrays.asList(item,item2, item3));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(40),total);
    }

    @Test
    public void verificaTotaldaVendaAposRateioDeDescontoTotalTest() throws BusinessVendaExpection {
        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        venda.setItens(Arrays.asList(item,item2,item3));
        vendaProcessamento.aplicaRateioDeDescontoTotal(venda, new BigDecimal(10));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(30),total);
    }

    @Test
    public void verificaTotalAposaplicaRateioDeAcrescimoTotalTest() throws BusinessVendaExpection {
        BigDecimal tot = new BigDecimal(0);
        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        venda.setItens(Arrays.asList(item,item2,item3));
        vendaProcessamento.aplicaRateioDeAcrescimoTotal(venda, new BigDecimal(10));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(50),total);

    }

    @Test
    public void verificaTotalDeVendaSemItensDeveRetornarExceptionTest() {
        try{
            vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
            BigDecimal total = vendaProcessamento.valorTotalVenda(venda);

        } catch ( BusinessVendaExpection e){
            assertEquals("Não há Itens Lançados para essa Venda", e.getMessage());
        }
    }

    @Test
    public void cancelarVendaTest(){
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        venda.setPagamentos(Arrays.asList(PagamentoMother.getUmPagamentoEmDinheiro(venda)));
        Boolean vendaFoiCancelada = vendaProcessamento.cancelarVenda(venda);
        assertTrue(vendaFoiCancelada);
        assertTrue(venda.getItens().get(0).isCancelado());
        assertEquals(StatusPagamento.CANCELADO, venda.getPagamentos().get(0).getStatusPagamento());
    }

    @Test
    public void lancaPagamentoTest() throws BusinessVendaExpection {
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        BigDecimal valorRestante = vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
        assertEquals(BigDecimal.ZERO, valorRestante);

    }

    @Test
    public void lancaPagamentoComPagamentoJaExistenteTest() throws BusinessVendaExpection {
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(90)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
        BigDecimal valorRestanteSegPagamento = vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
        assertEquals(new BigDecimal(20), valorRestanteSegPagamento);
    }

    @Test
    public void lancaPagamentoQueDevolvaTrocoTest() throws BusinessVendaExpection {
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
        vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
        assertEquals(new BigDecimal(-30), venda.getValorRestante());
        assertEquals(new BigDecimal(30), venda.getTroco());
    }

    @Test
    public void lancaPagamentoQueDevolveExceptionTest() {
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        venda.setValorRestante(new BigDecimal(0));
        try{
            vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
            vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);

        } catch ( BusinessVendaExpection e){
            assertEquals("Venda sem saldo para efetuar Pagamento", e.getMessage());
        }
    }


    @Test
    public void atualizaPrecoConformeTipoDeVendaVarejoTest() throws ProdutoException, BusinessVendaExpection {

        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        item2.setIdProduto(2L);
        item2.setIdProduto(3L);

        venda.setItens(Arrays.asList(item2,item3));

        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(ProdutoMother.getProduto());

        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        Venda vendaAtualizada = vendaProcessamento.atualizaPrecoConformeTipoDeVenda(this.venda);

        assertEquals(venda.getItens().get(1).getValor(), vendaAtualizada.getItens().get(1).getValor());

        vendaProcessamento.valorTotalVenda(venda);

        assertEquals(new BigDecimal(400), venda.getValorTotal());
    }

    @Test
    public void atualizaPrecoConformeTipoDeVendaAtacadoTest() throws ProdutoException, BusinessVendaExpection {

        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        item2.setIdProduto(2L);
        item2.setIdProduto(3L);

        venda.setItens(Arrays.asList(item2,item3));

        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(ProdutoMother.getProduto());

        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        venda.setTipoDePreco(TipoDePreco.ATACADO);

        Venda vendaAtualizada = vendaProcessamento.atualizaPrecoConformeTipoDeVenda(this.venda);

        assertEquals(venda.getItens().get(1).getValor(), vendaAtualizada.getItens().get(1).getValor());

        vendaProcessamento.valorTotalVenda(venda);

        assertEquals(new BigDecimal(200), venda.getValorTotal());
    }

    @Test
    public void atualizaPrecoConformeTipoDeVendaParceladoTest() throws ProdutoException, BusinessVendaExpection {

        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        item2.setIdProduto(2L);
        item2.setIdProduto(3L);

        venda.setItens(Arrays.asList(item2,item3));

        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(ProdutoMother.getProduto());

        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        venda.setTipoDePreco(TipoDePreco.PARCELADO);

        Venda vendaAtualizada = vendaProcessamento.atualizaPrecoConformeTipoDeVenda(this.venda);

        assertEquals(venda.getItens().get(1).getValor(), vendaAtualizada.getItens().get(1).getValor());

        vendaProcessamento.valorTotalVenda(venda);

        assertEquals(new BigDecimal(700), venda.getValorTotal());
    }

    @Test
    public void atualizaPrecoConformeTipoDeVendaPromocionalTest() throws ProdutoException, BusinessVendaExpection {

        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false).eRetorna();
        item2.setIdProduto(2L);
        item2.setIdProduto(3L);

        venda.setItens(Arrays.asList(item2,item3));

        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(ProdutoMother.getProduto());

        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        venda.setTipoDePreco(TipoDePreco.PROMOCAO);

        Venda vendaAtualizada = vendaProcessamento.atualizaPrecoConformeTipoDeVenda(this.venda);

        assertEquals(venda.getItens().get(1).getValor(), vendaAtualizada.getItens().get(1).getValor());

        vendaProcessamento.valorTotalVenda(venda);

        assertEquals(new BigDecimal(100), venda.getValorTotal());
    }

    @Test
    public void atualizaEstoqueAposPagamentoTest() throws BusinessVendaExpection {

        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(40))
                .alteraIdDoEstoque(999L).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(false)
                .alteraIdDoEstoque(999L).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);
        vendaProcessamento.lancaPagamento(venda, new BigDecimal(40), TipoPagamento.DINHEIRO);

        ProdutoDto produtoDto = Converter.INSTANCE.ProdutoToProdutoDto(ProdutoMother.getProduto());
        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        Mockito.when(produtoService.buscarProdutoPorId(Mockito.anyLong())).thenReturn(produtoDto);

        vendaProcessamento.atualizaEstoqueAposPagamento(venda);

        assertEquals(1L, venda.getItens().get(0).getIdEStoque());
        assertEquals(new BigDecimal(30), venda.getTroco());

    }
}
