package br.com.systemautoma.automasystem.business.venda;

import br.com.systemautoma.automasystem.domain.TipoDePreco;
import br.com.systemautoma.automasystem.domain.converter.Converter;
import br.com.systemautoma.automasystem.entity.Estoque;
import br.com.systemautoma.automasystem.entity.Preco;
import br.com.systemautoma.automasystem.entity.Produto;
import br.com.systemautoma.automasystem.entity.VendaItem;
import br.com.systemautoma.automasystem.exceptions.ProdutoException;
import br.com.systemautoma.automasystem.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VendaItemProcessamento {

    @Autowired
    EstoqueService estoqueService;

    public VendaItem transfomaEmItemVenda(Produto produto, long quantidade, long filial, TipoDePreco tipoPreco){

        VendaItem item = new VendaItem();
        item.setIdProduto(produto.getIdProduto());
        item.setQuantidadeVendida(quantidade);
        item.setIdEStoque(buscaEstoque(produto, filial));

        Estoque estoque = Converter.INSTANCE.EstoqueDtoToEstoque(estoqueService
                .buscaEstoqueporId(item.getIdEStoque()));
        item.setEstoque(estoque);
        item.setPreco(buscaPreco(produto, filial));
        item.setValorUnitario(retornaPrecoConformeTipoVenda(tipoPreco, item));
        item.setValor(item.getValorUnitario().multiply(new BigDecimal(quantidade)));
        item.setIdEmpresa(filial);

        return item;

    }

    public VendaItem devolveVendaItem(Produto produto, long filial){

        VendaItem item = new VendaItem();
        item.setIdProduto(produto.getIdProduto());
        item.setIdEStoque(buscaEstoque(produto, filial));
        item.setEstoques(new ArrayList<>(produto.getEstoques()));
        item.setPrecos(new ArrayList<>(produto.getPrecos()));
        item.setIdEmpresa(filial);

        return item;
    }

   private long buscaEstoque(Produto produto, long filial) {
      return produto.getEstoques().stream().filter(p -> p.getIdFilial() == filial)
               .map(p -> p.getIdEstoque())
               .findFirst().orElseThrow(() -> new ProdutoException("Nao ha estoque cadastrado " +
                       "para esse Produto nesta Filial"));
   }

    private Preco buscaPreco(Produto produto, long filial) {
        return produto.getPrecos().stream().filter(p -> p.getIdFilial() == filial)
                .findFirst().orElseThrow(() -> new ProdutoException("Nao ha Preco cadastrado " +
                        "para esse Produto nesta Filial"));
    }

    private BigDecimal retornaPrecoConformeTipoVenda(TipoDePreco tipoPreco, VendaItem item){

        if (tipoPreco.equals(TipoDePreco.VAREJO)){
            return item.getPreco().getPrecoVendaVarejo();
        }
        if(tipoPreco.equals(TipoDePreco.ATACADO)) {
            return item.getPreco().getPrecoVendaAtacado();
        }
        if(tipoPreco.equals(TipoDePreco.PROMOCAO)) {
            return item.getPreco().getPrecoPromocional();
        }
        if(tipoPreco.equals(TipoDePreco.PARCELADO)) {
            return item.getPreco().getPrecoParcelado();
        }
        return BigDecimal.ZERO;
    }
}
