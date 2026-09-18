-- Schema SQL Completo - FleetOps (NewLogística)
-- Baseado em docs/API3-NewLogistica.json e diagrama ER

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` INT PRIMARY KEY AUTO_INCREMENT,
  `permissao_gerente` BOOLEAN DEFAULT FALSE,
  `nome_usuario` VARCHAR(100) NOT NULL,
  `email_usuario` VARCHAR(150) UNIQUE NOT NULL,
  `senha_usuario` VARCHAR(255) NOT NULL,
  `perfil_acesso` VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `agregado` (
  `id_agregado` INT PRIMARY KEY AUTO_INCREMENT,
  `contato_agregado` VARCHAR(20),
  `cnpj_agregado` VARCHAR(14) UNIQUE NOT NULL,
  `nome_agregado` VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `veiculo` (
  `id_veiculo` INT PRIMARY KEY AUTO_INCREMENT,
  `placa_veiculo` VARCHAR(7) UNIQUE NOT NULL,
  `ano_fabricacao` INT,
  `tipo_veiculo` VARCHAR(50),
  `subtipo_veiculo` VARCHAR(50),
  `fk_id_agregado` INT NOT NULL,
  CONSTRAINT `fk_veiculo_agregado` FOREIGN KEY (`fk_id_agregado`) REFERENCES `agregado` (`id_agregado`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `motorista` (
  `id_motorista` INT PRIMARY KEY AUTO_INCREMENT,
  `nome_motorista` VARCHAR(100) NOT NULL,
  `cpf_motorista` VARCHAR(11) UNIQUE NOT NULL,
  `contato_motorista` VARCHAR(20),
  `status` VARCHAR(30) DEFAULT 'Disponível',
  `ultimo_manifesto` DATE,
  `contador_rota_sp` INT DEFAULT 0,
  `nota_media` FLOAT DEFAULT 0.0,
  `fk_id_veiculo` INT UNIQUE,
  `fk_id_agregado` INT NOT NULL,
  CONSTRAINT `fk_motorista_veiculo` FOREIGN KEY (`fk_id_veiculo`) REFERENCES `veiculo` (`id_veiculo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_motorista_agregado` FOREIGN KEY (`fk_id_agregado`) REFERENCES `agregado` (`id_agregado`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `manifesto` (
  `id_manifesto` INT PRIMARY KEY AUTO_INCREMENT,
  `data_manifesto` DATE,
  `destino` VARCHAR(100),
  `valor_recebido` DECIMAL(12,2),
  `frete` DECIMAL(12,2),
  `aereo` BOOLEAN DEFAULT FALSE,
  `fk_id_motorista` INT NOT NULL,
  `fk_id_agregado` INT NOT NULL,
  `fk_id_veiculo` INT NOT NULL,
  CONSTRAINT `fk_manifesto_motorista` FOREIGN KEY (`fk_id_motorista`) REFERENCES `motorista` (`id_motorista`) ON UPDATE CASCADE,
  CONSTRAINT `fk_manifesto_agregado` FOREIGN KEY (`fk_id_agregado`) REFERENCES `agregado` (`id_agregado`) ON UPDATE CASCADE,
  CONSTRAINT `fk_manifesto_veiculo` FOREIGN KEY (`fk_id_veiculo`) REFERENCES `veiculo` (`id_veiculo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `avaliacao` (
  `id_comentario` INT PRIMARY KEY AUTO_INCREMENT,
  `fk_id_usuario` INT NOT NULL,
  `fk_id_motorista` INT NOT NULL,
  `fk_id_manifesto` INT NOT NULL,
  `feedback` VARCHAR(500),
  `nota_comentario` INT,
  CONSTRAINT `fk_avaliacao_usuario` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE,
  CONSTRAINT `fk_avaliacao_motorista` FOREIGN KEY (`fk_id_motorista`) REFERENCES `motorista` (`id_motorista`) ON UPDATE CASCADE,
  CONSTRAINT `fk_avaliacao_manifesto` FOREIGN KEY (`fk_id_manifesto`) REFERENCES `manifesto` (`id_manifesto`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

