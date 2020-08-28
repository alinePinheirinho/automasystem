package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.Filial;
import br.com.systemautoma.automasystem.mother.FilialMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilialRepositoryTest {

    @Autowired
    FilialRepository repository;

    @Test
    public void cadastrarFilial(){

        Filial filial = FilialMother.getFilial();
        repository.save(filial);
    }
}
