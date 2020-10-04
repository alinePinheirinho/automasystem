package br.com.systemautoma.automasystem.business.venda;

import br.com.systemautoma.automasystem.Util.DataUtil;
import br.com.systemautoma.automasystem.business.PrecoDeVenda;
import br.com.systemautoma.automasystem.domain.TipoDePreco;
import br.com.systemautoma.automasystem.domain.dtos.EstoqueDto;
import br.com.systemautoma.automasystem.domain.dtos.ProdutoDto;
import br.com.systemautoma.automasystem.domain.enumerador.StatusPagamento;
import br.com.systemautoma.automasystem.domain.enumerador.TipoPagamento;
import br.com.systemautoma.automasystem.entity.*;
import br.com.systemautoma.automasystem.exceptions.BusinessVendaExpection;
import br.com.systemautoma.automasystem.exceptions.EstoqueException;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;
import br.com.systemautoma.automasystem.service.EstoqueService;
import br.com.systemautoma.automasystem.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaProcessamento {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    EstoqueService estoqueService;

    @Autowired
    public VendaProcessamento(ProdutoService produtoService) { }

    public VendaProcessamento(ProdutoService produtoService, EstoqueService estoqueService) { }

    public Venda abreVenda() {

        Venda venda = new Venda();
        venda.setCliente(new Cliente());
        venda.getCliente().setNome("CONSUMIDOR");
        venda.getCliente().setIdCliente(1L);
        venda.setTipoDePreco(TipoDePreco.VAREJO);
        venda.setItens(new ArrayList<>());
        if (venda.getIdFilial() <= 0L) {
            venda.setIdFilial(1l);
        }

        return venda;
    }

    public void lancaItem(Venda venda, VendaItem item) {
        item.setIdEmpresa(venda.getIdFilial());

        EstoqueDto estoqueDto = validaEstoqueDoProduto(item);
        validaEstoqueItensVendaCorrente(venda, item, estoqueDto);

        venda.getItens().add(item);
    }


    public void removeItem(Venda venda, VendaItem item) {
        if (venda.getItens().contains(item)) {
            venda.getItens().remove(item);
        }
    }

    public void removeItemLogicamente(Venda venda, VendaItem item) {
        if (venda.getItens().contains(item)) {
            venda.getItens().get(venda.getItens().indexOf(item)).setCancelado(true);
        }
    }

    public List aplicaRateioDeDescontoTotal(Venda venda, BigDecimal descontoTotal) {
        //TODO: melhorar esse metodo
        List<VendaItem> itensAtivos = venda.getItens().stream().filter(item -> !item.isCancelado()).collect(Collectors.toList());
        BigDecimal rateio = descontoTotal.divide(new BigDecimal(itensAtivos.size()));

        venda.getItens().stream().filter(item -> !item.isCancelado()).forEach(item -> {
            item.setValor(item.getValor().subtract(rateio));
        });
        venda.setValorTotalDeDescontos(descontoTotal);
        return venda.getItens();
    }

    public List aplicaRateioDeAcrescimoTotal(Venda venda, BigDecimal acrescimoTotal) {
        //TODO: melhorar esse metodo
        List<VendaItem> itensAtivos = venda.getItens().stream().filter(item -> !item.isCancelado()).collect(Collectors.toList());
        BigDecimal rateio = acrescimoTotal.divide(new BigDecimal(itensAtivos.size()));

        venda.getItens().stream().filter(item -> !item.isCancelado()).forEach(item -> {
            item.setValor(item.getValor().add(rateio));
        });
        venda.setValorTotalDeAcrescimos(acrescimoTotal);
        return venda.getItens();
    }

    public BigDecimal valorTotalVenda(Venda venda) throws BusinessVendaExpection {
        if (venda.getItens() == null || venda.getItens().size() < 1) {
            throw new BusinessVendaExpection("Não há Itens Lançados para essa Venda");
        }
        BigDecimal valorTotal = venda.getItens().stream().filter(item -> !item.isCancelado())
                .map(item -> item.getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        venda.setValorTotal(valorTotal);

        return venda.getValorTotal();
    }

    public Boolean cancelarVenda(Venda venda) {
        if (venda.getItens() != null && venda.getItens().size() >= 1) {
            cancelaTodosItensDaVenda(venda);
        }
        if (venda.getPagamentos() != null && venda.getPagamentos().size() >= 1) {
            cancelaTodosOsPagamentosDaVenda(venda);
        }
        venda.setVendaCancelada(true);
        return true;
    }

    public BigDecimal lancaPagamento(Venda venda, BigDecimal valor, TipoPagamento tipoPagamento, int parcelas)
            throws BusinessVendaExpection {
        int quantidadeParcelas = (parcelas < 1) ? parcelas = 1 : parcelas;

        if(venda.getCliente().getIdCliente() == 1){
            throw new BusinessVendaExpection(" Nao é possivel Parcelar cliente nao Identificado!");
        }

        calculaValorRestante(venda);

        if (venda.getValorRestante().compareTo(new BigDecimal(0)) == 1) {
            if (tipoPagamento.equals(TipoPagamento.PARCELADO_LOJA)) {
                this.vendaParceladoLoja(venda, quantidadeParcelas, valor);
                atribuiPagamento(venda, valor, tipoPagamento);
            }
            return retornaSaldoRestanteOuPreencheValorTroco(venda);
        } else {
            throw new BusinessVendaExpection("Venda sem saldo para efetuar Pagamento");
        }
    }

    public BigDecimal lancaPagamento(Venda venda, BigDecimal valor, TipoPagamento tipoPagamento)
            throws BusinessVendaExpection {

        calculaValorRestante(venda);

        if (venda.getValorRestante().compareTo(new BigDecimal(0)) == 1) {
            if (tipoPagamento.equals(TipoPagamento.PARCELADO_LOJA)) {
                throw new BusinessVendaExpection("VPara venda parcelada escolha a opcao correspondente");
            } else {
                atribuiPagamento(venda, valor, tipoPagamento);
            }
            return retornaSaldoRestanteOuPreencheValorTroco(venda);
        } else {
            throw new BusinessVendaExpection("Venda sem saldo para efetuar Pagamento");
        }
    }

    private BigDecimal retornaSaldoRestanteOuPreencheValorTroco(Venda venda) throws BusinessVendaExpection {
        this.calculaValorRestante(venda);
        if (venda.getValorRestante().compareTo(new BigDecimal(0)) == 1) {
            return retornaValorRestanteAposPagamento(venda);
        } else {
            atribuiValorDeTroco(venda);
            return BigDecimal.ZERO;
        }
    }

    public Venda atualizaPrecoConformeTipoDeVenda(Venda venda) throws ProdutoException {

        atualizaPrecosParaVendaVarejo(venda);

        atualizaPrecosParaVendaAtacado(venda);

        atualizaPrecosParaVendaParcelada(venda);

        atualizaPrecosParaVendaPromocional(venda);

        return venda;
    }

    public Venda identificaCliente(Venda venda, Cliente cliente) {
        venda.setCliente(cliente);
        return venda;
    }

    public Venda vendaParceladoLoja(Venda venda, int numeroParcelas, BigDecimal valor) {

        BigDecimal valorDaParcela = valor.divide(new BigDecimal(numeroParcelas));

        for (int i = 1; i <= numeroParcelas; i++) {
            venda.getParcelas().add(criaParcelas(venda, numeroParcelas, valor, valorDaParcela, DataUtil.PulaMesesDiaSemana(i)));

        }

        if (venda.getPagamentos() != null && venda.getPagamentos().size() >= 1) {
            venda.getPagamentos().add(criaPagamentoParceladoLoja(venda, valor));
        } else {
            venda.setPagamentos(Arrays.asList(criaPagamentoParceladoLoja(venda, valor)));
        }
        return venda;
    }

    public ProdutoDto buscaProdutoPorId(VendaItem item) throws ProdutoException {
        return produtoService.buscarProdutoPorId(item.getIdProduto());
    }

    public Venda atualizaEstoqueAposPagamento(Venda venda) {

        if (venda.getStatusPagamento().equals(StatusPagamento.PAGO)) {
            venda.getItens().stream()
                    .map(i -> {
                        preencheIdDoEstoqueDoItem(i);
                        return i;
                    })
                    .forEach(this::baixaEstoqueDoItem);
        }
        return venda;
    }

    private Pagamento criaPagamentoParceladoLoja(Venda venda, BigDecimal valorRestante) {
        return new Pagamento(venda.getIdVenda(), TipoPagamento.PARCELADO_LOJA, valorRestante,
                venda.getIdFilial(), StatusPagamento.PAGO);
    }

    private VendaParcelada criaParcelas(Venda venda, int numeroParcelas, BigDecimal valorRestante, BigDecimal valorDaParcela,
                              LocalDate vencimento) {

        VendaParcelada parcela = new VendaParcelada();
        parcela.setCliente(venda.getCliente());
        parcela.setIdVenda(venda.getIdVenda());
        parcela.setIdFilial(venda.getIdFilial());
        parcela.setValorVenda(venda.getValorTotal());
        parcela.setValorParcelado(valorRestante);
        parcela.setQuantidadeParcelas(numeroParcelas);
        parcela.setValorParcela(valorDaParcela);
        parcela.setStatusParcela(StatusPagamento.PENDENTE);
        parcela.setDataVenda(LocalDate.now());
        parcela.setDataVencimento(vencimento);

        return parcela;
    }

    private void atualizaPrecosParaVendaPromocional(Venda venda) {
        if (venda.getTipoDePreco().equals(TipoDePreco.PROMOCAO)) {
            PrecoDeVenda precoDeVenda = new PrecoDeVendaPromocional();
            venda.getItens().stream().forEach(vendaItem -> {
                try {
                    vendaItem.setValor(precoDeVenda
                            .getPrecoDeVenda(this.buscaProdutoPorId(vendaItem), venda.getIdFilial()));
                } catch (ProdutoException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void atualizaPrecosParaVendaParcelada(Venda venda) {
        if (venda.getTipoDePreco().equals(TipoDePreco.PARCELADO)) {
            PrecoDeVenda precoDeVenda = new PrecoDeVendaParcelada();
            venda.getItens().stream().forEach(vendaItem -> {
                try {
                    vendaItem.setValor(precoDeVenda
                            .getPrecoDeVenda(this.buscaProdutoPorId(vendaItem), venda.getIdFilial()));
                } catch (ProdutoException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void atualizaPrecosParaVendaAtacado(Venda venda) {
        if (venda.getTipoDePreco().equals(TipoDePreco.ATACADO)) {
            PrecoDeVenda precoDeVenda = new PrecoDeVendaAtacado();
            venda.getItens().stream().forEach(vendaItem -> {
                try {
                    vendaItem.setValor(precoDeVenda
                            .getPrecoDeVenda(this.buscaProdutoPorId(vendaItem), venda.getIdFilial()));
                } catch (ProdutoException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void atualizaPrecosParaVendaVarejo(Venda venda) {
        if (venda.getTipoDePreco().equals(TipoDePreco.VAREJO)) {
            PrecoDeVenda precoDeVenda = new PrecoDeVendaVarejo();
            venda.getItens().stream().forEach(vendaItem -> {
                try {
                    vendaItem.setValor(precoDeVenda
                            .getPrecoDeVenda(this.buscaProdutoPorId(vendaItem), venda.getIdFilial()));
                } catch (ProdutoException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void preencheIdDoEstoqueDoItem(VendaItem item) throws ProdutoException {

        ProdutoDto produtoDto = this.buscaProdutoPorId(item);

        Long id = produtoDto.getEstoques().stream().filter(p -> p.getIdFilial() == item.getIdEmpresa())
                .map(p -> p.getIdEstoque())
                .findFirst().orElseThrow(() -> new ProdutoException("Nao ha estoque cadastrado " +
                        "para esse Produto nesta Filial"));

        item.setIdEStoque(id);
    }

    private void baixaEstoqueDoItem(VendaItem item) throws ProdutoException {

        estoqueService.baixaEstoqueAposVenda(item.getIdEStoque(), item.getQuantidadeVendida());

    }

    private void cancelaTodosOsPagamentosDaVenda(Venda venda) {
        venda.getPagamentos().forEach(pagamento ->
                pagamento.setStatusPagamento(StatusPagamento.CANCELADO));
    }

    private void cancelaTodosItensDaVenda(Venda venda) {
        if (venda.getItens() != null) {
            venda.getItens().forEach(item -> item.setCancelado(true));
        }
    }

    private BigDecimal atribuiValorDeTroco(Venda venda) {
        venda.setTroco(new BigDecimal(venda.getValorRestante().toString()).abs());
        venda.setStatusPagamento(StatusPagamento.PAGO);
        return venda.getValorRestante();
    }

    private void calculaValorRestante(Venda venda) throws BusinessVendaExpection {
        if (venda.getValorRestante() == null) {
            if (venda.getValorTotal() == null) {
                venda.setValorTotal(this.valorTotalVenda(venda));
            }
            venda.setValorRestante(venda.getValorTotal());
        }
    }

    private BigDecimal retornaValorRestanteAposPagamento(Venda venda) {
        venda.setStatusPagamento(StatusPagamento.PENDENTE);
        return venda.getValorRestante();
    }

    private Pagamento atribuiPagamento(Venda venda, BigDecimal valor, TipoPagamento tipoPagamento) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(valor);
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setIdVenda(venda.getIdVenda());
        pagamento.setStatusPagamento(StatusPagamento.PAGO);
        if (venda.getPagamentos() == null) {
            venda.setPagamentos(new ArrayList<Pagamento>());
        }
        venda.getPagamentos().add(pagamento);
        venda.setValorRestante(venda.getValorRestante().subtract(valor));
        return pagamento;
    }


    private void validaEstoqueItensVendaCorrente(Venda venda, VendaItem item, EstoqueDto estoqueDto) {

        Double somaDoEstoqueDoItemConciderandoLancamentosAtuais = venda.getItens().stream()
                .filter(i -> !i.isCancelado() && i.getIdProduto() == item.getIdProduto()
                        && i.getIdEmpresa() == item.getIdEmpresa() && i.getIdVenda() == item.getIdVenda()).map((i -> i.getQuantidadeVendida()))
                .reduce((ac, i) -> ac + i).orElse(0D);

        if (somaDoEstoqueDoItemConciderandoLancamentosAtuais > estoqueDto.getQuantidade()) {
            throw new EstoqueException("Os Lancamentos desse Item " + "nessa venda Ultrapassam " +
                    "a Quantidade de" + " estoque existente. Estoque Existente = " + estoqueDto.getQuantidade() +
                    " estoque somado para esse item = " + somaDoEstoqueDoItemConciderandoLancamentosAtuais);
        }
    }

    private EstoqueDto validaEstoqueDoProduto(VendaItem item) {
        this.preencheIdDoEstoqueDoItem(item);
        EstoqueDto estoqueDto = estoqueService.buscaEstoqueporId(item.getIdEStoque());

        if (estoqueDto.getQuantidade() - item.getQuantidadeVendida() < 0L) {
            throw new EstoqueException("Saldo em estoque Insuficiente . Estoque Atual = "
                    + estoqueDto.getQuantidade());
        }
        return estoqueDto;
    }

}
