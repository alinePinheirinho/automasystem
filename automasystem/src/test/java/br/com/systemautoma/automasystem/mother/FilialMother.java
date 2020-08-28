package br.com.systemautoma.automasystem.mother;

import br.com.systemautoma.automasystem.entity.Filial;

public class FilialMother {

    public static Filial getFilial(){

        Filial filial  = new Filial();
        filial.setNomeFilal("Primeira Filial");
        filial.setEmpresa(EmpresaMother.getEmpresa());

        return filial;
    }
}
