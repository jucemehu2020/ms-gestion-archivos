package com.unicauca.maestria.api.gestionarchivosms.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Acta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_doc_maestria")
    private DocumentoMaestria idDocMaestria;

    @Column(name = "numero_actas")
    private Long numeroActa;

    @Column(name = "fecha_actas")
    @Temporal(TemporalType.DATE)
    private Date fechaActa;
}
