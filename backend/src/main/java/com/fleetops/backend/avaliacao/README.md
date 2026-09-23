# Avaliações de Frete – SCRUM-27 / SCRUM-28 / SCRUM-48

Documentação das alterações feitas no backend para o módulo de avaliações de frete.

--------------------------------------------------
O QUE FOI IMPLEMENTADO
--------------------------------------------------

SCRUM-47 : Integração de mudança de status

Atualização: banco de dados padrão migrado de H2 para MySQL, alinhando o
módulo com a stack oficial do projeto. Nenhuma classe do pacote avaliacao
precisou mudar — a troca é só de configuração (application.properties),
já que a entidade usa tipos padrão (IDENTITY, TEXT) compatíveis com os
dois bancos.

Campos da avaliação:
- id            (Long, PK, gerado automaticamente)
- nota          (Integer 1 a 5, obrigatório)
- feedback      (TEXT, obrigatório)
- motoristaId   (Long, obrigatório – vínculo só por ID, sem FK forte)
- dataRegistro  (LocalDateTime, preenchida automaticamente)

Escala de notas:
1 = Péssimo
2 = Ruim
3 = Regular
4 = Bom
5 = Excelente

--------------------------------------------------
ESTRUTURA DE PASTAS (preparada para microsserviço)
--------------------------------------------------

com.fleetops.backend.avaliacao
├── domain/
│   ├── AvaliacaoFrete.java
│   └── EscalaNota.java
├── repository/
│   └── AvaliacaoFreteRepository.java
├── service/
│   └── AvaliacaoFreteService.java
├── controller/
│   └── AvaliacaoFreteController.java
├── dto/
│   ├── AvaliacaoRequestDTO.java
│   └── AvaliacaoResponseDTO.java
└── exception/
    └── GlobalExceptionHandler.java

Tudo isolado no pacote "avaliacao".
O vínculo com o motorista é só por motoristaId (Long), sem @ManyToOne.
Isso facilita a migração de monorepo para multi-repo / microsserviço na próxima sprint.

Arquivos de suporte:
- config/OpenApiConfig.java          → configuração do SpringDoc
- src/main/resources/application.properties → MySQL (padrão) + H2 (comentado)

--------------------------------------------------
COMO RODAR
--------------------------------------------------

Pré-requisitos:
- JDK 17 (ou 21)
- Maven Wrapper já incluso (mvnw)
- MySQL 8.x rodando localmente (ou em container)

1. Criar o banco no MySQL:

   CREATE DATABASE fleetops;

2. Conferir as credenciais em src/main/resources/application.properties:

   spring.datasource.url=jdbc:mysql://localhost:3306/fleetops?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=sua_senha

   Ajuste username/password para o usuário do seu MySQL local.
   A tabela avaliacoes_frete é criada automaticamente (ddl-auto=update).

3. Subir a aplicação:

   cd backend

   # Linux / macOS
   chmod +x mvnw
   ./mvnw spring-boot:run

   # Windows
   .\mvnw.cmd spring-boot:run

A aplicação sobe em: http://localhost:8080

4. Ver a documentação (SCRUM-48):

   Swagger UI    → http://localhost:8080/swagger-ui.html
   OpenAPI JSON  → http://localhost:8080/api-docs

No Swagger você consegue testar o endpoint diretamente.

--------------------------------------------------
EXEMPLO DE USO DA API
--------------------------------------------------

Request:

POST /api/avaliacoes
Content-Type: application/json

{
  "motoristaId": 42,
  "nota": 4,
  "feedback": "Motorista pontual e cuidadoso com a carga."
}

Response (201 Created):

{
  "mensagem": "Avaliação registrada com sucesso",
  "id": 1,
  "motoristaId": 42,
  "nota": 4,
  "descricaoNota": "Bom",
  "feedback": "Motorista pontual e cuidadoso com a carga.",
  "dataRegistro": "2026-09-17T14:00:00"
}

Exemplos com cURL:

# Sucesso
curl -X POST http://localhost:8080/api/avaliacoes \
  -H "Content-Type: application/json" \
  -d '{
    "motoristaId": 42,
    "nota": 5,
    "feedback": "Excelente serviço, entrega no prazo."
  }'

# Erro de validação (nota inválida)
curl -X POST http://localhost:8080/api/avaliacoes \
  -H "Content-Type: application/json" \
  -d '{
    "motoristaId": 42,
    "nota": 6,
    "feedback": "Teste"
  }'

Resposta de erro (400):

{
  "timestamp": "2026-09-17T14:01:00",
  "status": 400,
  "error": "Dados inválidos",
  "fields": {
    "nota": "A nota deve ser no máximo 5 (Excelente)"
  }
}

--------------------------------------------------
VOLTAR A USAR H2 (teste rápido sem MySQL)
--------------------------------------------------

Só faz sentido para testes locais rápidos, sem depender de um MySQL de pé.
Edite src/main/resources/application.properties:

1. Comente as linhas do MySQL
2. Descomente as do H2:

spring.datasource.url=jdbc:h2:mem:fleetops;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

Console H2: http://localhost:8080/h2-console
(JDBC URL: jdbc:h2:mem:fleetops | User: sa | Senha: vazia)

--------------------------------------------------
DEPENDÊNCIAS ENVOLVIDAS
--------------------------------------------------

- springdoc-openapi-starter-webmvc-ui (2.8.5) → documentação Swagger
- mysql-connector-j (runtime) → driver JDBC do banco padrão
- h2 (runtime) → banco em memória, mantido só para a alternativa de teste rápido acima

--------------------------------------------------
PRÓXIMOS PASSOS (sugestão)
--------------------------------------------------

- Consultar avaliações por motorista (GET /api/avaliacoes?motoristaId=42)
- Cálculo de ranking / média (Sprint 2/3)
- Extrair o pacote avaliacao para um microsserviço independente
