package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.Empresa;
import br.com.systemautoma.automasystem.mother.EmpresaMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmpresaRepositoryTest {

    @Autowired
    EmpresaRepository repository;

    @Test
    public void cadastrarEmpresa(){
        Empresa empresa = EmpresaMother.getEmpresa();
        repository.save(empresa);
    }
}
