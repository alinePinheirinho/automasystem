package br.com.systemautoma.automasystem.business;


import br.com.systemautoma.automasystem.builder.VendaItemBuilder;
import br.com.systemautoma.automasystem.business.venda.VendaProcessamento;
import br.com.systemautoma.automasystem.entity.Venda;
import br.com.systemautoma.automasystem.entity.VendaItem;
import br.com.systemautoma.automasystem.mother.VendaItemMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;

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
    }

    @Test
    public void valorTotalVendaTest(){

        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        vendaProcessamento.setItens(Arrays.asList(item,item2));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(40),total);
    }

    @Test
    public void valorTotalVendaComItemCanceladoTest(){

        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        vendaProcessamento.setItens(Arrays.asList(item,item2, item3));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(40),total);
    }

    @Test
    public void verificaTotaldaVendaAposRateioDeDescontoTotal(){

        BigDecimal tot = new BigDecimal(0);
        VendaItem item = VendaItemMother.getVendaItem();
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        vendaProcessamento.setItens(Arrays.asList(item,item2,item3));
        vendaProcessamento.aplicaRateioDeDescontoTotal(new BigDecimal(10));
        BigDecimal total = vendaProcessamento.valorTotalVenda(venda);
        assertEquals(new BigDecimal(30),total);
    }

    @Test
    public void cancelarVenda(){
        VendaItem item2 = builderItem.iniciaItemAlteravel().alteraValor(new BigDecimal(30)).eRetorna();
        VendaItem item3 = builderItem.iniciaItemAlteravel().alteraStatusItemCacelado(true).eRetorna();
        venda.setItens(Arrays.asList(item2,item3));
        Boolean vendaFoiCancelada = vendaProcessamento.cancelarVenda(venda);
        assertTrue(vendaFoiCancelada);
    }
}
