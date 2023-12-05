package com.unicauca.maestria.api.gestionarchivosms.respositories;

import com.unicauca.maestria.api.gestionarchivosms.domain.Oficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OficioRepository extends JpaRepository<Oficio, Long> {

    @Query(
            value = "SELECT * " +
                    "FROM oficios o INNER JOIN documentos_maestria dm ON o.id_doc_maestria = dm.id " +
                    "WHERE dm.estado = :estado",
            nativeQuery = true
    )
    public List<Oficio> findByEstado(@Param("estado") Boolean estado);

    public List<Oficio> findByNumeroOficio(Long numeroOficio);

    public List<Oficio> findByNumeroOficioAndFechaOficio(Long numeroOficio, Date fechaOficio);

}
