# Projeto Filmes - Exercício Prático de JPA

## 📋 Sobre o Projeto


Um projeto educacional desenvolvido em **Spring Boot** com o objetivo de praticar **JPA (Java Persistence API)** com integração a banco de dados, testes unitários com **Mockito** *.


O projeto implementa um sistema de gerenciamento de filmes e atores com as seguintes funcionalidades:


- ✅ Buscar filmes por título
- ✅ Cadastrar novos filmes
- ✅ Listar todos os filmes
- ✅ Buscar atores por nome
- ✅ Cadastrar novos atores
- ✅ Listar todos os atores
- ✅ Relacionamento muitos-para-muitos entre filmes e atores (atuações)
- ✅ Testes automatizados de repositório e telas


---


## 🛠️ Tecnologias Utilizadas


### Core Framework
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.7** - Framework web e aplicação
- **Spring Data JPA** - Abstração para persistência de dados


### Banco de Dados
- **PostgreSQL** - Banco de dados relacional
- **Docker** - Containerização do PostgreSQL


### Testes
- **JUnit 5** - Framework de testes unitários
- **Mockito** - Mock de objetos para testes unitários


### Build & Development
- **Maven** - Gerenciador de dependências e build
- **Spring Boot DevTools** - Recarregamento automático durante desenvolvimento


---


## 🚀 Como Executar o Projeto


### Pré-requisitos


- Java 21+ instalado
- Maven instalado
- Docker e Docker Compose instalados (para executar PostgreSQL)


### Passo 1: Clonar o Repositório


```bash
cd /Users/alexandreaquiles/Documents/unipds/projetos-lives/live-jpa-exercicio-filmes
```


### Passo 2: Iniciar o PostgreSQL via Docker


```bash
docker-compose up -d
```


Isso iniciará um container PostgreSQL configurado com o database `filmes`.


### Passo 3: Executar o Projeto




```bash
cd app-filme
SPRING_DATASOURCE_PASSWORD=<senha do BD aqui> ./mvnw spring-boot:run
```


Isso iniciará a aplicação com um menu interativo no terminal para gerenciar filmes e atores.


### Passo 4: Acessar o Menu Principal


Após executar, você verá um menu com as seguintes opções:


```
**************
MENU PRINCIPAL
**************
1 - Buscar filme
2 - Incluir filme
3 - Listar filmes
4 - Buscar ator
5 - Incluir ator
6 - Listar atores
0 - Sair
```


### Parar o PostgreSQL


```bash
docker-compose down
```


---


## 📁 Estrutura do Projeto


```
live-jpa-exercicio-filmes/
│
├── docker-compose.yml              # Configuração do PostgreSQL
├── README.md                        # Este arquivo
│
└── app-filme/                       # Aplicação Spring Boot
   ├── pom.xml                      # Dependências Maven
   ├── mvnw / mvnw.cmd              # Maven Wrapper
   │
   ├── src/main/
   │   ├── java/br/com/unipds/filmes/
   │   │   ├── ProjetoFilmesApplication.java    # Classe principal
   │   │   │
   │   │   ├── model/                           # Entidades JPA
   │   │   │   ├── Filme.java                   # Entidade de Filme
   │   │   │   ├── Ator.java                    # Entidade de Ator
   │   │   │   ├── Atuacao.java                 # Entidade de relacionamento M:N
   │   │   │   ├── AtuacaoId.java               # Chave composta de Atuacao
   │   │   │   └── Diretor.java                 # Entidade de Diretor
   │   │   │
   │   │   ├── repository/                      # Repositórios (Data Access)
   │   │   │   ├── FilmeRepository.java         # CRUD para Filme
   │   │   │   └── AtorRepository.java          # CRUD para Ator
   │   │   │
   │   │   └── telas/                           # Camada de Apresentação
   │   │       ├── Tela.java                    # Interface abstrata
   │   │       ├── LoopPrincipal.java           # Loop principal do menu
   │   │       ├── TelaBuscarFilme.java         # Buscar filme
   │   │       ├── TelaCadastrarFilme.java      # Cadastrar filme
   │   │       ├── TelaListarFilmes.java        # Listar filmes
   │   │       ├── TelaBuscarAtor.java          # Buscar ator
   │   │       ├── TelaCadastrarAtor.java       # Cadastrar ator
   │   │       └── TelaListarAtores.java        # Listar atores
   │   │
   │   └── resources/
   │       └── application.properties           # Configurações da app
   │
   ├── src/test/
   │   ├── java/br/com/unipds/filmes/
   │   │   └── telas/ 
   │   │       ├── TelaCadastrarFilmeTest.java
   │   │       └── TelaBuscarFilmeTest.java
   │   │
   │   └── resources/
   │       └── application.properties           # Configurações para testes
   │
   └── target/                                  # Artefatos compilados (gerado)
```


---


## 📍 Mapa de Código


### Model (Entidades JPA)


- **[model/](app-filme/src/main/java/br/com/unipds/filmes/model/)** - Contém as entidades mapeadas ao banco de dados
 - `Filme.java` - Representa um filme com atributos como título, ano, duração, idioma e data de lançamento
 - `Ator.java` - Representa um ator com nome, sobrenome e sexo
 - `Atuacao.java` - Relacionamento muitos-para-muitos entre Filme e Ator (quem atuou em qual filme)
 - `Diretor.java` - Representa um diretor (relacionado a filmes via @ManyToMany)


### Repository (Data Access)


- **[repository/](app-filme/src/main/java/br/com/unipds/filmes/repository/)** - Interfaces de acesso a dados
 - `FilmeRepository.java` - Fornece operações CRUD para Filme
 - `AtorRepository.java` - Fornece operações CRUD para Ator


### Telas (Apresentação)


- **[telas/](app-filme/src/main/java/br/com/unipds/filmes/telas/)** - Camada de interface com usuário
 - `Tela.java` - Interface que define o contrato `void executar(Scanner entrada)`
 - `LoopPrincipal.java` - Menu principal com switch de opções
 - `TelaBuscarFilme.java` - Busca filme por título
 - `TelaCadastrarFilme.java` - Cadastra novo filme
 - `TelaListarFilmes.java` - Lista todos os filmes (candidata para testes com Mockito)
 - `TelaBuscarAtor.java` - Busca ator por nome (candidata para testes com Mockito)
 - `TelaCadastrarAtor.java` - Cadastra novo ator (candidata para testes com Mockito)
 - `TelaListarAtores.java` - Lista todos os atores (candidata para testes com Mockito)


### Application


- **[ProjetoFilmesApplication.java](app-filme/src/main/java/br/com/unipds/filmes/ProjetoFilmesApplication.java)** - Classe principal que implementa `CommandLineRunner` e executa o `LoopPrincipal`


---


## 🧪 Testes


### Tipos de Testes


- Testes com **Mockito** para as telas.


### Executar Todos os Testes


```bash
cd app-filme
./mvnw test
```


### Executar um Teste Específico


```bash
./mvnw test -Dtest=TelaBuscarFilmeTest
```


---


## 💡 Exercícios Propostos

Estes exercícios foram sugeridos durante a live para aprofundar o aprendizado:

### 1️⃣ Cobrir com Testes Unitários usando Mockito


Implementar testes unitários (com Mockito) para as seguintes telas:

- **TelaCadastrarFilme** - Testar o cadastro de novo filme
- **TelaListarFilmes** - Testar a exibição da lista de filmes
- **TelaBuscarAtor** - Testar a busca de atores
- **TelaCadastrarAtor** - Testar o cadastro de novo ator
- **TelaListarAtor** - Testar a listagem de atores


---


### 2️⃣ Refatorar LoopPrincipal: Remover Switch e Usar ENUM


**Objetivo:** Substituir o switch/case da classe `LoopPrincipal` por uma abordagem com ENUM.


**Motivação:** Melhor legibilidade, type-safety e facilita manutenção.


**Antes (atual):**
```java
switch (opcao) {
   case 1:
       nomeTela = "telaBuscarFilme";
       break;
   case 2:
       nomeTela = "telaCadastrarFilme";
       break;
   // ... mais cases
}
```


**Depois (proposto com ENUM):**
```java
public enum MenuOpcao {
   BUSCAR_FILME(1, "telaBuscarFilme", "Buscar filme"),
   CADASTRAR_FILME(2, "telaCadastrarFilme", "Incluir filme"),
   LISTAR_FILMES(3, "telaListarFilmes", "Listar filmes"),
   // ... mais opções
   SAIR(0, null, "Sair");


   // complete o restante do código...
}
```


Exempleo de utilização no `LoopPrincipal`:


```java
MenuOpcao opcaoSelecionada = MenuOpcao.porCodigo(opcao);
if (opcaoSelecionada != null && opcaoSelecionada != MenuOpcao.SAIR) {
   Tela tela = telas.get(opcaoSelecionada.getNomeTela());
   // executar...
}
```


**Benefícios:**
- ✅ Menos propenso a erros
- ✅ Fácil adicionar novas opções
- ✅ Type-safe
- ✅ Separação de responsabilidades
- ✅ Open/Closed Principle
