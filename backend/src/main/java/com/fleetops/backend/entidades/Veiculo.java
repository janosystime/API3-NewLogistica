package com.fleetops.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    @NotBlank(message = "A placa do veículo é obrigatória")
    @Size(max = 7, message = "A placa deve ter no máximo 7 caracteres")
    @Column(name = "placa_veiculo", length = 7, unique = true, nullable = false)
    private String placaVeiculo;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_veiculo", length = 30)
    private TipoVeiculos tipoVeiculo;

    @Size(max = 50, message = "O subtipo do veículo deve ter no máximo 50 caracteres")
    @Column(name = "subtipo_veiculo", length = 50)
    private String subtipoVeiculo;

    @NotNull(message = "O agregado proprietário é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_agregado", nullable = false)
    private Agregado agregado;
}
