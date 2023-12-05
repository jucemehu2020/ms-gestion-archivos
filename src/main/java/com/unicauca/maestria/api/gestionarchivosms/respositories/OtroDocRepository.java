package com.unicauca.maestria.api.gestionarchivosms.respositories;

import com.unicauca.maestria.api.gestionarchivosms.domain.OtroDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OtroDocRepository extends JpaRepository<OtroDoc, Long> {
    @Query(
            value = "SELECT * " +
                    "FROM otros_documentos od INNER JOIN documentos_maestria dm ON od.id_doc_maestria = dm.id " +
                    "WHERE dm.estado = :estado",
            nativeQuery = true
    )
    public List<OtroDoc> findByEstado(@Param("estado") Boolean estado);

    public List<OtroDoc> findByNombreDocumentoContainingIgnoreCase(String nombreDocumento);
}
