package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.entity.Endereco;

public class EnderecoMother {

    public static Endereco getEndereco(){

        Endereco endereco = new Endereco();
        endereco.setRua("Ernesto Nazareth");
        endereco.setNumero("85");
        endereco.setBairro("pq das americas");
        endereco.setCep("11713190");
        endereco.setCidade("praia grande");
        endereco.setEstado("SP");
        endereco.setCodEstado(35);
        endereco.setCodPais(55);

        return endereco;
    }
}
