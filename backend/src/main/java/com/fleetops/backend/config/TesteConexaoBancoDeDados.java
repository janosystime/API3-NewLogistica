package com.fleetops.backend.config;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesteConexaoBancoDeDados {

    @Bean
    public CommandLineRunner testarConexao(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                if (connection != null && !connection.isClosed()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println(" SUCESSO! Conexão com o banco estabelecida com sucesso!");
                    System.out.println(" Banco: " + connection.getMetaData().getDatabaseProductName());
                    System.out.println(" URL: " + connection.getMetaData().getURL());
                    System.out.println("--------------------------------------------------");
                }
            } catch (Exception e) {
                System.err.println("--------------------------------------------------");
                System.err.println(" ERRO: Falha ao conectar com o banco de dados!");
                System.err.println(" Motivo: " + e.getMessage());
                System.err.println("--------------------------------------------------");
            }
        };
    }
}
