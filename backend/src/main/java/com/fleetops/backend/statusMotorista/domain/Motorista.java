package com.fleetops.backend.statusMotorista.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa o motorista.
 * Estruturada de forma isolada (pacote avaliacao) para facilitar
 * futura extração para um microsserviço.
 */
@Entity
@Table(name = "motorista")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motorista")
    private Long idMotorista;

    /**
     * Nome do motorista (obrigatório).
     */
    @NotBlank(message = "O nome do motorista é obrigatório")
    @Size(max = 100, message = "O nome do motorista deve ter no máximo 100 caracteres")
    @Column(name = "nome_motorista", length = 100, nullable = false)
    private String nomeMotorista;

    /**
     * Cpf do motorista (obrigatório).
     */
    @NotBlank(message = "O CPF do motorista é obrigatório")
    @Size(max = 11, message = "O CPF deve ter no máximo 11 dígitos numéricos")
    @Column(name = "cpf_motorista", length = 11, unique = true, nullable = false)
    private String cpfMotorista;

    /**
     * Contato do motorista, como telefone (obrigatório).
     */
    @Size(max = 20, message = "O contato deve ter no máximo 20 caracteres")
    @Column(name = "contato_motorista", length = 20, nullable = false)
    private String contatoMotorista;

    /**
     * Status atual do motorista (obrigatório).
     */
    @Size(max = 30, message = "O status deve ter no máximo 30 caracteres")
    @Column(name = "status", length = 30, nullable = false)
    @Builder.Default
    private Status status = Status.Disponivel;

    /**
     * Data da ultima da atualização de status.
     */
    @Column(name = "status_updated_at")
    private LocalDate statusUpdatedAt;

    /**
     * Data da ultima da atualização de manifesto.
     */
    @Column(name = "ultimo_manifesto")
    private LocalDate ultimoManifesto;

    /**
     * Quantidade de rotas feita pelo motorista.
     */
    @Column(name = "contador_rota_sp")
    @Builder.Default
    private Integer contadorRotaSp = 0;

    /**
     * Data da ultima da atualização de status.
     */
    @Column(name = "nota_media")
    @Builder.Default
    private Float notaMedia = 0.0f;

    /**
     * ID do veiculo ao qual o motorista está vinculado.
     * Mantido como Long (sem @OneToOne) para baixo acoplamento
     * e facilitar migração para microsserviço.
     */
    @Column(name = "veiculo_id", nullable = false, unique = true)
    private Long veiculoId;

    /**
     * ID do agregado ao qual o motorista está vinculado.
     * Mantido como Long (sem @OneToOne) para baixo acoplamento
     * e facilitar migração para microsserviço.
     */
    @NotNull(message = "O agregado ao qual o motorista pertence é obrigatório")
    @Column(name = "agregado_id", nullable = false)
    private Long agregadoId;

    public void atualizaStatus(Status status) {
        this.status = status;
        this.statusUpdatedAt = LocalDate.now();
    }
}
