package com.fleetops.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @NotNull(message = "O usuário que realizou a avaliação é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull(message = "O motorista avaliado é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_motorista", nullable = false)
    private Motorista motorista;

    @NotNull(message = "O manifesto avaliado é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_manifesto", nullable = false)
    private Manifesto manifesto;

    @Size(max = 500, message = "O feedback deve ter no máximo 500 caracteres")
    @Column(name = "feedback", length = 500)
    private String feedback;

    @Column(name = "nota_comentario")
    private Integer notaComentario;
}
