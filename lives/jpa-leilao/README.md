# Projeto Leilão - Prática de Mapeamento com JPA

Este é um projeto para praticar mapeamentos com Jakarta Persistence (JPA). 

## O que fazer?

O objetivo é fazer com que os testes passem apenas adicionando anotações do JPA
nas classes de modelo do pacote `br.com.unipds.leilao.modelo`.

Analise a estrutura do BD. Dica: pode olhar o script `leilao.sql` para ter uma ideia.

Verifique as classes Java. Como mapeá-las para que funcionem com esse BD?

**NÃO** vale mudar o conteúdo do BD, nem de atributos e métodos das classes Java.

Na branch `resposta` há uma solução para o desafio.

Anotações do JPA que você deverá usar:

- `@Entity`
- `@Id`
- `@GeneratedValue`
- `@Embeddable`
- `@Embedded`
- `@Inheritance`
- `@ManyToOne`
- `@OneToMany`
- `@ManyToMany`
- `@JoinTable`
- `@JoinColumn`
- `@OneToOne`
- `@NamedQuery`
- `@ElementCollection`
- `@CollectionTable`

Anotações do Bean Validation que você deverá usar:

- `@NotNull`
- `@Size`
- `@Future`

## Como executar o projeto

1.  Inicie o container do MySQL usando o Docker Compose:
    ```bash
    docker-compose up -d
    ```
2.  Abra o projeto em sua IDE de preferência.
3.  Execute os testes e veja-os falhar.
4.  Adicione as anotações JPA necessárias nas classes de modelo para fazer os testes passarem.

## Estrutura do Banco de Dados

O script `leilao.sql` contém a estrutura do banco de dados. As principais tabelas são:

*   `Leilao`
*   `Item`
*   `Usuario`
*   `Lance`
*   `FormaDePagamento`
*   `CartaoDeCredito`
*   `ContaBancaria`

## Tecnologias Utilizadas

*   Java 21
*   Maven
*   Jakarta Persistence (JPA)
*   Hibernate
*   MySQL
*   JUnit 5
*   Docker
