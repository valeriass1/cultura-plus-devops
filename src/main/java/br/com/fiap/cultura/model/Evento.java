package br.com.fiap.cultura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_evento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Evento {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_EVENTO"
    )
    @SequenceGenerator(
            name = "SEQ_EVENTO",
            sequenceName = "SEQ_EVENTO",
            allocationSize = 1
    )
    private Long eventoId;


    private String nome;

    @Column(name = "data_evento")
    private LocalDateTime data;

    private String local;

    private String descricao;

    @Column(name = "quantidade_limite")
    private Integer quantidadeLimite;
}

