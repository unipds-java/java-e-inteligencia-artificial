# 🖥️ Live UniPDS — SDD na Prática

**SDD na Prática: Como Transformar uma Especificação em Código com IA**

Live realizada em **23/11/2026** sobre **Spec-Driven Development (SDD)** — uma abordagem de desenvolvimento assistido por IA em que uma especificação detalhada do problema guia a geração, revisão e validação do código, em vez de prompts isolados sobre o que se quer construir.

---

## 📁 Conteúdo

### `slides/` — Apresentação da live

- [`sdd_na_pratica.pdf`](./slides/sdd_na_pratica.pdf) — apresentação utilizada durante a live (12 slides).

### `demo/` — Projetos de demonstração

Dois estados do **CFP Manager** — API REST em **Quarkus** para gerenciar o ciclo de vida de Calls for Papers de eventos de tecnologia — incluídos como **git submodules**:

- [`cfp-manager/`](./demo/cfp-manager) — branch `live-2026-11-23`: estado do projeto durante a live. O pacote `proposal/` existe apenas como esqueleto (`package-info.java`) — um CFP OPEN ainda não aceita submissões.
- [`cfp-manager-proposal-implemented/`](./demo/cfp-manager-proposal-implemented) — branch `live-2026-11-23-solution`: estado final após o ciclo de SDD. Implementa a submissão de propostas por speakers (`POST /api/events/{eventId}/cfp/proposals`, `GET /api/proposals`, migration `V8__create_proposals.sql`) e inclui o fluxo OpenSpec completo: `openspec/` com o change arquivado `2026-11-23-speaker-proposal-submission` (proposal, design, tasks e spec) e as skills `openspec-*` em `.agents/skills/`.

> Os projetos da demo são git submodules. Ao clonar este repositório, use `git clone --recurse-submodules` (ou rode `git submodule update --init` após o clone) para baixá-los.

#### Sobre o CFP Manager

A aplicação conecta dois tipos de usuários:

- **Speakers** — criam conta e submetem propostas de palestras para CFPs abertas.
- **Coordinators** — registram eventos, abrem e gerenciam Calls for Papers e fazem a triagem das propostas recebidas.

O desenvolvimento orientado a especificação usa o **OpenSpec** (especificações versionadas em `openspec/`) e skills de agente em `.agents/skills/` — presentes na versão `cfp-manager-proposal-implemented`.

#### Stack

| Componente | Tecnologia |
| :--- | :--- |
| Linguagem | Java 25 |
| Framework | Quarkus 3.40 (`quarkus-rest`) |
| Persistência | Hibernate ORM com Panache |
| Banco de dados | PostgreSQL (Dev Services via Docker/Podman) |
| Migrations | Flyway |
| Testes | JUnit 5 + REST Assured |

#### Como executar

```shell
cd demo/cfp-manager                          # ou demo/cfp-manager-proposal-implemented
./mvnw quarkus:dev
```

Pré-requisitos: **JDK 25** e **Docker ou Podman** (o Quarkus Dev Services sobe um PostgreSQL automaticamente). A Dev UI fica disponível em `http://localhost:8080/q/dev/`.

> Documentação completa do projeto (idêntica nas duas versões) em [`demo/cfp-manager/README.md`](./demo/cfp-manager/README.md).

---

**UNIPDS — Pós-Graduação em Java + Inteligência Artificial**
