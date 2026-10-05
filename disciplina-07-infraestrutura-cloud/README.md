# UniPDS

**UniPDI** é uma aplicação exemplo, desenvolvida para que os estudantes possam cadastrar e gerenciar seus **Planos de Desenvolvimento Individual (PDI)**.  
Este projeto foi criado com objetivo educacional, para auxiliar no aprendizado de **Docker**, **Kubernetes** e **Cloud Computing**.

---

## ✅ **Arquitetura da Aplicação**

A aplicação é composta por dois principais componentes:

- **Backend**  
  - Linguagem: **Java 21**  
  - Framework: **Spring Web**  
  - Banco de Dados: **MongoDB**  
  - Responsável pela API e persistência dos dados.  

- **Frontend**  
  - Framework: **React** com **Vite**  
  - Responsável pela interface web para interação com os usuários.  

---

## 📦 **Pré-requisitos**

Antes de iniciar, certifique-se de ter instalado:

- **Docker** 
- **Java 21**
- **Node.js (>=18)** e **npm** ou **yarn**

---

## ▶️ **Como Executar o Projeto**

A **branch `main`** é a branch inicial e **não contém automação completa para subir tudo via Docker**.  
Portanto, a execução deve ser feita em duas etapas:

### **1. Subir os serviços com Docker Compose**

O `docker-compose.yml` disponibiliza os containers necessários para a infraestrutura (ex.: MongoDB).

```bash
docker-compose up -d
```

Este comando irá:

- Subir o banco de dados **MongoDB**
- Expor as portas necessárias para a conexão, de acordo com o diretório especificado no application.properties do backend.

---

### **2. Executar o Backend**

No diretório do **backend**:

```bash
mvn spring-boot:run
```

---

### **3. Executar o Frontend**

No diretório do **frontend**:

Instale as dependências:

```bash
npm install
```

E execute a aplicação:

```bash
npm run dev
```

O frontend será iniciado, por padrão, em http://localhost:5173

---


## 🔗 **Fluxo de Acesso**

- **Frontend:** [http://localhost:5173](http://localhost:5173)  
- **Backend:** [http://localhost:8080](http://localhost:8080)  
- **MongoDB:** porta definida no `docker-compose.yml` (geralmente **27017**)

---

## 🎯 **Objetivo Educacional**

Este projeto faz parte do aprendizado sobre:

- **Docker**: conteinerização da aplicação
- **Docker Compose**: orquestração de múltiplos serviços
- **Kubernetes**: implantação em cluster
- **Cloud**: deploy em provedores como AWS, GCP ou Azure

A **branch `main`** é propositalmente simples para os alunos aprenderem a **construir imagens, criar seus próprios `Dockerfile` e manifestos do Kubernetes**.

---

