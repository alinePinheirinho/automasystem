package br.com.systemautoma.automasystem.repository;

import br.com.systemautoma.automasystem.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository <Empresa, Long> {

}
