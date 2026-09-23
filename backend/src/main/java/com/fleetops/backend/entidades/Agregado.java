package com.fleetops.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agregado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agregado")
    private Long idAgregado;

    @NotBlank(message = "O nome do agregado é obrigatório")
    @Size(max = 150, message = "O nome do agregado deve ter no máximo 150 caracteres")
    @Column(name = "nome_agregado", length = 150, nullable = false)
    private String nomeAgregado;

    @NotBlank(message = "O CNPJ do agregado é obrigatório")
    @Size(max = 14, message = "O CNPJ deve ter no máximo 14 dígitos numéricos")
    @Column(name = "cnpj_agregado", length = 14, unique = true, nullable = false)
    private String cnpjAgregado;

    @Size(max = 20, message = "O contato deve ter no máximo 20 caracteres")
    @Column(name = "contato_agregado", length = 20)
    private String contatoAgregado;
}
