package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.entity.Empresa;
import br.com.systemautoma.automasystem.entity.Endereco;

public class EmpresaMother {

    public static Empresa getEmpresa(){

           return new Empresa("empresa1", "empresa", "11709123","123456879","" +
                "","",EnderecoMother.getEndereco());
    }
}
