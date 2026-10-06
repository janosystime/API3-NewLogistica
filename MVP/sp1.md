# 📌 MVP - API 3º sem. DSM 2026/2

# Documentação (Sprint 1)

<p align="center">
      <img src="../docs/imagens/LogoJanos_vetor.png" alt="logo da Janos Logística Integrada" width="600">
</p>

<p align="center"> 📑 ÍNDICE </p>
<p align="center">
  |<a href="#objetivo"> Objetivo</a> |
  <a href="#backlog"> Backlog da Sprint 1</a> |
  <a href="#tasks"> Tasks da Sprint</a> |
  <a href="#pronto"> Definição de Pronto (Sprint 1)</a> |
  <a href="#proximos"> Próximos Passos</a> |
  <a href="#anexos"> Anexos</a> |
  <a href="#referencias"> Referências</a> |
</p>

---

## 🎯 Objetivo (Sprint 1 — 07/09 a 25/09/2026) <a id="objetivo"></a>

Permitir ao operador logístico importar o cadastro de motoristas/veículos, consultar disponibilidade por tipo de veículo, atualizar o status de disponibilidade do agregado e registrar a avaliação do frete realizado.

Isso ataca o principal problema do cliente: a falta de visibilidade centralizada sobre a disponibilidade real dos motoristas, que hoje gera contato duplicado (dois operadores ligando para o mesmo motorista ao mesmo tempo).

---

## 📋 Backlog da Sprint 1 <a id="backlog"></a>

| Rank | ID | Prioridade | Título | Estimativa | Épico |
| :--: | :-: | :-------: | :----: | :--------: | :---: |
| <span style="color:green">1</span> | <span style="color:green">US1 [Meta]</span> | <span style="color:green">ALTA</span> | <span style="color:green">CONSULTAR OS STATUS DOS MOTORISTAS</span> | <span style="color:green">6</span> | <span style="color:green">E1</span> |
| 2 | US02 | MÉDIA | IMPORTAÇÃO DE MOTORISTAS/TIPO DE VEÍCULOS (.csv) | 6 | E2 |
| 3 | US03 | MÉDIA | ALTERAR OS STATUS DOS MOTORISTAS | 5 | E1 |
| 4 | US04 | MÉDIA | REGISTRAR NOTA+FEEDBACK | 7 | E3 |

> Backlog completo do produto (todas as sprints e épicos): ver README do repositório.

---

## 🛠️ Tasks da Sprint <a id="tasks"></a>

| Rank | US · Título  | Estimativa (hr) |
| :--: | :---------- | :-------------: |
|  | **US1 · CONSULTAR OS STATUS DOS MOTORISTAS** |  |
| 1 | Criar busca no Banco para listar motoristas | 4 |
| 2 | Criar tela que exibe o Card dos Motoristas | 4 |
| 3 | Criar botões de Filtro | 4 |
| 4 | Criar Documentação da listagem dos motoristas | 4 |
| 5 | Integrar Tela de filtros a busca no banco | 4 |
|  | **US2 · IMPORTAÇÃO DE MOTORISTAS/TIPO DE VEÍCULOS (.csv)** |  |
| 6 | Criar Modelagem e tabelas para Motoristas Agregados e Veículos | 4 |
| 7 | Criar Rota para receber e ler o arquivo .csv | 4 |
| 8 | Criar Telas para interação do Operador | 4 |
| 9 | Integração do Componente de Upload com a Rota de importação | 4 |
| 10 | Documentar Retorno de rota | 4 |
|  | **US3 · ALTERAR OS STATUS DOS MOTORISTAS** |  |
| 11 | Criar Script para salvar a mudança de Status (Disponível/Em Rota/ Indisponível/Indesejado) no Banco de dados. | 4 |
| 12 | Criar Botões de Status | 4 |
| 13 | Documentação de mudança de Status | 4 |
| 14 | Criar prototipos navegáveis para as telas de Operador e Gerente | 4 |
| 15 | Integração de mudança de Status | 4 |
|  | **US4 · REGISTRAR NOTA+FEEDBACK** |  |
| 16 | Criar Tabela para Salvar Avaliações | 4 |
| 17 | Documentação do Registro de Avaliação | 4 |
| 18 | Fazer Integração do Backend para salvar as avaliações no Banco | 4 |
| 19 | Criar Modal para Avaliação dos motoristas | 4 |
| 20 | Integrar sistema de avaliação | 4 |

---

## ✔️ Definição de Pronto (Sprint 1) <a id="pronto"></a>
- Código revisado e integrado (merge) na branch principal;
- Testes manuais com CSV válido e CSV malformado realizados e documentados;
- Fluxo ponta a ponta testado: importar cadastro → consultar disponibilidade → alterar status → registrar avaliação;
- Critérios de aceite de cada User Story validados pelo Product Owner.


- Código desenvolvido conforme critérios de aceitação;
- Testes funcionais de usabilidade realizados com sucesso;
- Código versionado e revisado;
- Manual de Usuário;
- Manual da Aplicação;
- Documentação atualizada;
- A funcionalidade está disponível em ambiente de teste/homologação.
- Vídeo de entrega anexado à documentação.

---

## 🚀 Próximos Passos <a id="proximos"></a>

**Sprint 2 (previsão 23/10/2026):** login com perfis Gerência/Operacional; gestão de usuários; fila de espera ordenada; importação de manifesto.

**Sprint 3 (previsão 20/11/2026):** exportação de rankings; desenvolvimento do Dashboard; ranking de rentabilidade/utilização e demais itens do backlog.

**Feira de Soluções:** 03/12/2026.

---

## 📂 Anexos / Evidências <a id="anexos"></a>

- Prints de tela / Protótipo<br><br>    
![mvp1_print1](/docs/sp1/print-1.png)
![mvp1_print2](/docs/sp1/print-2.png)
![mvp1_print3](/docs/sp1/print-3.png)

---

## 🔗 Referências <a id="referencias"></a>

- **README do repositório** — desafio, tecnologias, backlog completo, estratégia de branches, DoR/DoD geral do projeto.
- **PRD – Sprint 1** — critérios de aceite detalhados (Dado/Quando/Então), regras de negócio, requisitos não-funcionais, identidade visual, glossário, riscos e premissas.