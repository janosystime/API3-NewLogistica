# Organização de pacotes e configuração global (OpenApiConfig + Datasource)

Por que o OpenApiConfig e a configuração de banco ficam FORA do pacote avaliacao

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

src/main/resources/application.properties → também é configuração global
(datasource, JPA, SpringDoc), mesmo não estando dentro do pacote config/.

--------------------------------------------------
POR QUE O CONFIG (E O DATASOURCE) FICAM GLOBAIS
--------------------------------------------------

1. Existe apenas UM Spring Boot rodando.
   Ter vários OpenApiConfig (um em cada pacote) gera conflito
   de bean (dois beans do tipo OpenAPI). O mesmo vale pro banco:
   só existe uma conexão/datasource para a aplicação inteira, então
   ela é configurada uma única vez em application.properties.

2. Outros times NÃO precisam criar outro config nem outro datasource.
   Eles só criam o pacote do domínio deles, usam @Tag no controller
   (Swagger pega tudo automaticamente) e reaproveitam o mesmo banco
   MySQL já configurado.

3. O pacote avaliacao fica só com regra de negócio
   (entity, repo, service, controller, DTOs).
   Isso facilita extrair depois para um microsserviço — inclusive
   se um dia esse serviço precisar de um banco próprio, a configuração
   dele também vai ficar isolada no repo separado.

--------------------------------------------------
BANCO DE DADOS: H2 → MySQL
--------------------------------------------------

application.properties foi atualizado para usar MySQL como banco padrão
(antes usava H2 em memória). A troca ficou só na configuração — nenhuma
classe de domínio, repository, service ou controller precisou mudar,
porque a entidade AvaliacaoFrete já usava tipos compatíveis com os dois
bancos (@GeneratedValue IDENTITY, columnDefinition TEXT).

O bloco de H2 continua no arquivo, comentado, como alternativa para quem
quiser subir a aplicação localmente sem precisar instalar/configurar o
MySQL. Ver instruções em avaliacao/README.md, seção "VOLTAR A USAR H2".

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

Aí cada serviço (repo separado) terá o seu próprio OpenApiConfig e,
se precisar, seu próprio banco/datasource. Até lá, uma configuração
global (Swagger + MySQL) é o correto.

--------------------------------------------------
RESUMO
--------------------------------------------------

- config/                         → infraestrutura da aplicação (global)
- application.properties          → datasource (MySQL) + JPA + Swagger (global)
- avaliacao/                      → domínio de avaliações (pronto para extrair)
- outros/                         → cada time no seu pacote, sem criar novo config
