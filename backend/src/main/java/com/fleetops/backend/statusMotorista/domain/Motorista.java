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

  @NotBlank(message = "O nome do motorista é obrigatório")
  @Size(max = 100, message = "O nome do motorista deve ter no máximo 100 caracteres")
  @Column(name = "nome_motorista", length = 100, nullable = false)
  private String nomeMotorista;

  @NotBlank(message = "O CPF do motorista é obrigatório")
  @Size(max = 11, message = "O CPF deve ter no máximo 11 dígitos numéricos")
  @Column(name = "cpf_motorista", length = 11, unique = true, nullable = false)
  private String cpfMotorista;

  @Size(max = 20, message = "O contato deve ter no máximo 20 caracteres")
  @Column(name = "contato_motorista", length = 20)
  private String contatoMotorista;

  @Size(max = 30, message = "O status deve ter no máximo 30 caracteres")
  @Column(name = "status", length = 30)
  @Builder.Default
  private String status = "Disponível";

  @Column(name = "ultimo_manifesto")
  private LocalDate ultimoManifesto;

  @Column(name = "contador_rota_sp")
  @Builder.Default
  private Integer contadorRotaSp = 0;

  @Column(name = "nota_media")
  @Builder.Default
  private Float notaMedia = 0.0f;

  @Column(name = "veiculo_id", nullable = false, unique = true)
  private Long veiculoId;

  @NotNull(message = "O agregado ao qual o motorista pertence é obrigatório")
  @Column(name = "agregado_id", nullable = false)
  private Long agregadoId;
}