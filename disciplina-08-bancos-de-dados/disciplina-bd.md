**Instrutores:** Jacqueline Oliveira e Alexandre Aquiles.

---

## O projeto que atravessa a disciplina

Todas as aulas constroem o mesmo sistema: o **Javify**, um serviço de streaming de música.

A escolha não é decorativa. Cada banco de dados estudado resolve um problema **diferente da mesma plataforma** — e é a comparação entre eles, dentro de um domínio que você já conhece, que ensina quando usar cada um.

| Subsistema do Javify | Banco | Problema que resolve |
|---|---|---|
| Usuários, planos, assinaturas, pagamentos | **PostgreSQL** | Transações ACID e integridade referencial |
| Catálogo de álbuns e faixas | **MongoDB** | Schema flexível e leitura sem JOINs |
| Tela inicial e Top 50 | **Redis** | Latência abaixo de 1 ms |
| Histórico de reprodução | **Cassandra** | Bilhões de escritas por dia |
| Recomendações sociais | **Neo4J** | Relacionamentos profundos sem degradação |

---

## Para quem é

O material assume que você já cursou:

- **Fundamentos de Java**
- **Backend com Spring e Quarkus**
- **Software Design** (SOLID, DDD)
- **Arquitetura e System Design**

Por isso não explicamos o que é uma anotação, um bean ou como implementar uma API — usamos esses conceitos como base. Em compensação, cada decisão de modelagem é justificada pelo trade-off arquitetural que a motiva.

---

## O que tem em cada aula

Esta introdução é a Parte 0: leia-a inteira antes de começar a Aula 1 — sobretudo a seção "Como usar esta apostila", que explica por que o material é organizado do jeito que é.

### Parte I — Fundamentos e o mundo relacional

| # | Aula | Conteúdo |
|---|---|---|
| 1 | [Fundamentos de Bancos de Dados Relacionais](01-fundamentos-bancos-relacionais.md) | Por que SGBDs existem, MER, cardinalidades, normalização até 3FN |
| 2 | [SQL na Prática](02-sql-na-pratica.md) | Docker, DDL, DML, `SELECT`, agregações, JOINs e subqueries |

### Parte II — Persistência no ecossistema Java

| # | Aula | Conteúdo |
|---|---|---|
| 3 | [Persistência de Dados no Ecossistema Java](03-persistencia-java.md) | JPA, Hibernate, Spring Data, `EntityManager`, ciclo de vida, N+1, queries derivadas, `@Query` com JPQL e native queries e projections  — e vimos Jakarta Data com Quarkus: build-time × runtime, `@Find`, `StatelessSession` |
| 4 | [Ferramentas de Migração](04-ferramentas-migracao.md) | Flyway: checksums, migrações repetíveis. Liquibase: Changesets, ordem explícita, formato declarativo, rollback |

### Parte III — Avançando em SQL e JPA

| # | Aula | Conteúdo |
|---|---|---|
| 5 | [Transações e Controle de Concorrência](05-transacoes-concorrencia.md) | ACID, níveis de isolamento, locking pessimista e otimista, propagação do `@Transactional` e rollback |
| 6 | [Views, Funções, Triggers e Paginação](06-views-funcoes-triggers.md) | Materialized views, PL/pgSQL, auditoria por trigger, paginação com Spring Data JPA |

### Parte IV — NoSQL e persistência poliglota

| # | Aula | Conteúdo |
|---|---|---|
| 7 | [Introdução ao Mundo NoSQL](07-introducao-nosql.md) | Scale up × scale out, Teorema CAP, réplicas, sharding |
| 8 | [MongoDB](08-mongodb.md) | Documentos, padrão Subset, índices, aggregation pipelines |
| 9 | [Redis](09-redis.md) | Padrões de cache, invalidação, stampede, eviction |
| 10 | [Cassandra](10-cassandra.md) | Anel masterless, LSM-Tree, modelagem Query-First |
| 11 | [Neo4J](11-neo4j.md) | Grafos, index-free adjacency, Cypher, recomendações |

---

## O projeto que você constrói sozinho: o JFood

O Javify é construído **com** você, ao longo das aulas. O **JFood** — um aplicativo de delivery de comida — é construído **por** você, sozinho, ao final de cada aula.

A razão é de aprendizagem. Refazer o Javify exercita memória; aplicar as mesmas decisões em um domínio diferente exercita **transferência** — a habilidade que separa quem decorou anotações de quem entendeu os trade-offs. Por isso o JFood atravessa a disciplina inteira, com uma etapa por aula, e cada etapa assume a anterior pronta.

| Etapa (= aula) | O que você constrói |
|---|---|
| 1 | MER do delivery, entidade fraca, especialização e normalização até a 3FN |
| 2 | DDL, seed e as consultas que o app precisa — JOINs, agregações e *anti-join* |
| 3 | Mapeamento JPA, N+1 medido no log, projeções — e a versão Quarkus + Jakarta Data (opcional) |
| 4 | Linha do tempo de migrações no Flyway, com quebra de checksum reproduzida — e a versão Liquibase (opcional) |
| 5 | Confirmação de pedido com lock, fila de despacho com `SKIP LOCKED`, `@Version` |
| 6 | Views, materialized view de faturamento, função de taxa, auditoria por trigger e paginação medida |
| 7 | Contas de guardanapo, decisão CP/AP por subsistema e escolha da shard key |
| 8 | Catálogo de restaurantes e cardápios no MongoDB, com Subset Pattern |
| 9 | Cache do cardápio no pico do almoço, write-behind e stampede reproduzido |
| 10 | Tracking de entregadores no Cassandra: GPS a cada 5 s, bucketing e TTL |
| 11 | Recomendação cruzada por avaliações no Neo4J e o documento final de arquitetura |

| Subsistema do JFood | Banco | Problema que resolve |
|---|---|---|
| Usuários, pedidos, pagamentos | **PostgreSQL** | Transações ACID e integridade referencial |
| Catálogo de restaurantes e cardápios | **MongoDB** | Schema flexível (opcionais de cada prato) e leitura sem JOINs |
| Cardápio em cache e contadores de acesso | **Redis** | Pico concentrado no almoço e no jantar |
| Tracking de entregadores | **Cassandra** | Ingestão massiva de coordenadas GPS |
| Recomendação por avaliações | **Neo4J** | Filtragem colaborativa em três saltos |

---

## Imagens Docker utilizadas

Todos os bancos rodam em contêiner. Utilizamos as seguintes imagens:

| Banco | Imagem Docker |
|---|---|
| PostgreSQL | `postgres:16` |
| MongoDB | `mongo:8` |
| Redis | `redis:8-alpine` |
| Cassandra | `cassandra:5` |
| Neo4J | `neo4j:2026-trixie` |

Os bancos de dados são iniciados em cada aula.

### Ferramentas por aula

| Banco | Cliente |
|---|---|
| PostgreSQL | DBeaver |
| MongoDB | `mongosh` ou MongoDB Compass |
| Redis | `redis-cli` |
| Cassandra | `cqlsh` |
| Neo4J | Neo4J Browser (`http://localhost:7474`) |

### Microsserviços criados

| Serviço | Porta |
|---|---|
| Administrativo (PostgreSQL) | 8080 |
| Catálogo (MongoDB + Redis) | 8082 |
| Histórico de reprodução (Cassandra) | 8083 |
| Recomendações (Neo4J) | 8084 |
