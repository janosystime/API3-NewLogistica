# Organização de pacotes e configuração global (OpenApiConfig + Datasource)

Por que o OpenApiConfig e a configuração de banco ficam FORA do pacote avaliacao
# Organização de pacotes e OpenApiConfig

Por que o OpenApiConfig fica FORA do pacote avaliacao

--------------------------------------------------
CONTEXTO
--------------------------------------------------

Hoje o projeto ainda é um monorepo (um único Spring Boot).
Vários times vão criar serviços diferentes no mesmo backend
(avaliacao, motorista, frete, etc.).

No futuro (próxima sprint) a ideia é migrar para multi-repo /
microsserviços.

--------------------------------------------------
COMO FICOU A ESTRUTURA
--------------------------------------------------

com.fleetops.backend
├── config/                         ← GLOBAL (1 só para a aplicação)
│   └── OpenApiConfig.java
├── avaliacao/                      ← domínio de avaliações
│   ├── domain/
│   ├── repository/
│   ├── service/
│   ├── controller/
│   ├── dto/
│   └── exception/
├── motorista/                      ← futuro (outro time)
└── frete/                          ← futuro (outro time)

--------------------------------------------------
POR QUE O CONFIG FICA GLOBAL
--------------------------------------------------

1. Existe apenas UM Spring Boot rodando.
   Ter vários OpenApiConfig (um em cada pacote) gera conflito
   de bean (dois beans do tipo OpenAPI).

2. Outros times NÃO precisam criar outro config.
   Eles só criam o pacote do domínio deles e usam @Tag
   no controller. O Swagger pega tudo automaticamente.

3. O pacote avaliacao fica só com regra de negócio
   (entity, repo, service, controller, DTOs).
   Isso facilita extrair depois para um microsserviço.

--------------------------------------------------
O QUE CADA TIME FAZ
--------------------------------------------------

- Cria o próprio pacote (ex: com.fleetops.backend.motorista)
- Coloca entity, repository, service, controller e DTOs lá dentro
- Usa @Tag no controller para aparecer no Swagger
- Reaproveita o datasource MySQL já configurado em application.properties
- NÃO cria outro OpenApiConfig nem outro datasource

--------------------------------------------------
QUANDO VIRAR MICROSSERVIÇO
--------------------------------------------------

Aí cada serviço (repo separado) terá o seu próprio OpenApiConfig.
Até lá, um config global é o correto.

--------------------------------------------------
RESUMO
--------------------------------------------------

- config/                         → infraestrutura da aplicação (global)
- application.properties          → datasource (MySQL) + JPA + Swagger (global)
- avaliacao/                      → domínio de avaliações (pronto para extrair)
- outros/                         → cada time no seu pacote, sem criar novo config
