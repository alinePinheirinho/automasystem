package br.com.systemautoma.automasystem.business;


import br.com.systemautoma.automasystem.builder.VendaItemBuilder;
import br.com.systemautoma.automasystem.business.venda.VendaProcessamento;
import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import br.com.systemautoma.automasystem.entity.Venda;
import br.com.systemautoma.automasystem.entity.VendaItem;
import br.com.systemautoma.automasystem.exceptions.BusinessVendaExpection;
import br.com.systemautoma.automasystem.mother.PagamentoMother;
import br.com.systemautoma.automasystem.mother.VendaItemMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendaProcessamentoTest {

    private VendaItemBuilder builderItem;
    private VendaProcessamento vendaProcessamento;
    private Venda venda;

    @BeforeEach
    public void setUp() {
        builderItem = new VendaItemBuilder();
        vendaProcessamento = new VendaProcessamento();
        venda = vendaProcessamento.abreVenda();
        venda.setIdVenda(1L);
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


}
