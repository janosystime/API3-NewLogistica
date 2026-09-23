package com.fleetops.backend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "permissao_gerente")
    private Boolean permissaoGerente;

    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(max = 100, message = "O nome de usuário deve ter no máximo 100 caracteres")
    @Column(name = "nome_usuario", length = 100, nullable = false)
    private String nomeUsuario;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
    @Column(name = "email_usuario", length = 150, unique = true, nullable = false)
    private String emailUsuario;

    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 255, message = "A senha deve ter no máximo 255 caracteres")
    @Column(name = "senha_usuario", length = 255, nullable = false)
    private String senhaUsuario;

    @Size(max = 50, message = "O perfil de acesso deve ter no máximo 50 caracteres")
    @Column(name = "perfil_acesso", length = 50)
    private String perfilAcesso;
}
