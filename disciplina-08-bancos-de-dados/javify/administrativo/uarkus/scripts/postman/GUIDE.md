# Guia de testes - Javify Administrativo

Este guia serve tanto para o **Postman** quanto para o **Insomnia**. Os arquivos estão na mesma pasta deste guia.

Arquivos disponíveis:

- `javify-administrativo.postman_collection.json` — coleção com todas as requisições.
- `javify-local.postman_environment.json` — environment do Postman com as variáveis padrão.

## 1. Configuração no Postman

### Importar a coleção

1. Abra o Postman.
2. Clique em **Import** > **File**.
3. Selecione `javify-administrativo.postman_collection.json`.
4. A coleção "Javify Administrativo" aparecerá com as pastas Planos, Endereços, Assinaturas, Cartões, Usuários e Telefones.

### Importar o environment

1. Clique no dropdown de environment (canto superior direito) e escolha **Import**.
2. Selecione `javify-local.postman_environment.json`.
3. Ative o environment **Javify Local**.

### Testar

As variáveis `{{baseUrl}}`, `{{planoId}}`, `{{assinaturaId}}`, etc. serão resolvidas automaticamente.

---

## 2. Configuração no Insomnia

### Importar a coleção

1. Abra o Insomnia.
2. No workspace desejado, clique no ícone de engrenagem ou no menu **Import/Export**.
3. Escolha **Import Data** > **From File**.
4. Selecione `javify-administrativo.postman_collection.json`.
5. As pastas (Planos, Endereços, Assinaturas, Cartões, Usuários, Telefones) e as requisições aparecerão no workspace.

### Criar o environment

1. No canto inferior esquerdo, clique no dropdown de environment (geralmente aparece "No Environment").
2. Escolha **Manage Environments**.
3. Clique em **Add** e nomeie como **Javify Local**.
4. Adicione as variáveis abaixo (todas como texto/string):

```json
{
  "baseUrl": "http://localhost:8080",
  "planoId": "1",
  "enderecoCodigo": "01001000",
  "assinaturaId": "1",
  "usuarioId": "1",
  "telefoneId": "1",
  "cartaoId": "1"
}
```

5. Salve e selecione **Javify Local** no dropdown de environment.

---

## 3. Ordem sugerida para testar

1. **Planos** — Criar plano
2. **Endereços** — Criar endereço (pode usar o CEP de exemplo `01001000`)
3. **Usuários > Criar usuário** — Envia `endereco` completo e `planoId`; o endereço é salvo se não existir e a assinatura é criada automaticamente.
4. **Usuários > Criar usuário com assinatura existente** — Envia `endereco` completo e `assinaturaId` para vincular o usuário a uma assinatura já existente (útil para membros de plano Family).
5. **Telefones** — Criar telefone para usuário
6. **Cartões** — Criar cartão para assinatura

---

## 4. Exemplos de payload do usuário

### Criar usuário com novo endereço e nova assinatura

```json
{
  "nome": "João Silva",
  "email": "joao.silva@example.com",
  "senhaHash": "$2a$10$hashhashhashhashhashhashhashhashhashhashhashhashh",
  "titular": true,
  "dataNascimento": "1990-05-15",
  "endereco": {
    "codigoPostal": "01001000",
    "logradouro": "Praça da Sé",
    "bairro": "Sé"
  },
  "planoId": 1
}
```

### Criar usuário vinculando assinatura existente

```json
{
  "nome": "Maria Santos",
  "email": "maria.santos@example.com",
  "senhaHash": "$2a$10$hashhashhashhashhashhashhashhashhashhashhashhashh",
  "titular": false,
  "dataNascimento": "1985-08-22",
  "endereco": {
    "codigoPostal": "01311000",
    "logradouro": "Av. Paulista",
    "bairro": "Bela Vista"
  },
  "assinaturaId": 1
}
```

---

## 5. Regras importantes

- `endereco` é obrigatório no cadastro/atualização de usuário.
- Se o CEP do endereço ainda não existir, ele será salvo automaticamente.
- Se o CEP já existir, o usuário será vinculado ao endereço existente.
- `planoId` tem prioridade: quando informado, cria uma nova assinatura para o usuário.
- `assinaturaId` é usado apenas se `planoId` não for informado, para vincular uma assinatura existente.
- A resposta do usuário retorna o endereço e a assinatura completos (não só os ids).

---

## 6. Teste via curl

Cada requisição da coleção já inclui o comando `curl` equivalente na descrição. Você também pode usar diretamente no terminal, substituindo `{{baseUrl}}` por `http://localhost:8080` e as outras variáveis pelos valores reais.

Exemplo de criação de usuário:

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H 'Content-Type: application/json' \
  -d '{
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "senhaHash": "$2a$10$hashhashhashhashhashhashhashhashhashhashhashhashh",
    "titular": true,
    "dataNascimento": "1990-05-15",
    "endereco": {
      "codigoPostal": "01001000",
      "logradouro": "Praça da Sé",
      "bairro": "Sé"
    },
    "planoId": 1
  }'
```
