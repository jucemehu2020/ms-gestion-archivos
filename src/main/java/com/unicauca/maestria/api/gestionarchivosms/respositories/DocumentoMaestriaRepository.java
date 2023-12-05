package com.unicauca.maestria.api.gestionarchivosms.respositories;

import com.unicauca.maestria.api.gestionarchivosms.domain.DocumentoMaestria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoMaestriaRepository extends JpaRepository<DocumentoMaestria, Long> {
}
