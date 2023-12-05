package com.unicauca.maestria.api.gestionarchivosms.respositories;

import com.unicauca.maestria.api.gestionarchivosms.domain.Acta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ActaRepository extends JpaRepository<Acta, Long> {

    @Query(
            value = "SELECT * " +
                    "FROM actas a INNER JOIN documentos_maestria dm ON a.id_doc_maestria = dm.id " +
                    "WHERE dm.estado = :estado",
            nativeQuery = true
    )
    public List<Acta> findByEstado(@Param("estado") Boolean estado);

    public List<Acta> findByNumeroActa(Long numeroActa);

    public List<Acta> findByNumeroActaAndFechaActa(Long numeroActa,Date fechaActa);
}
