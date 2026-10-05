create table plano (
    id          SERIAL PRIMARY KEY,
    nome        VARCHAR(50) NOT NULL,
    preco       DECIMAL(10,2) NOT NULL,
    possui_propagandas  BOOLEAN NOT NULL DEFAULT false,
    limite_membros      INT NOT NULL DEFAULT 1,
    modo_offline        BOOLEAN NOT NULL DEFAULT false
);

create table endereco (
    codigo_postal  VARCHAR(8) PRIMARY KEY,
    logradouro     VARCHAR(150),
    bairro         VARCHAR(100)
);

create table assinatura (
    id                  SERIAL PRIMARY KEY,
    plano_id            INT NOT NULL REFERENCES plano(id),
    status_ativa        BOOLEAN NOT NULL DEFAULT true
);

create table usuario(
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    senha_hash      VARCHAR(60)  NOT NULL,
    titular         BOOLEAN NOT NULL DEFAULT true,
    cep             VARCHAR(8) REFERENCES endereco(codigo_postal),
    assinatura_id   INT REFERENCES assinatura(id)
);

create table usuario_telefone (
    id          SERIAL PRIMARY KEY,
    usuario_id  BIGINT NOT NULL REFERENCES usuario(id),
    numero      VARCHAR(20),
    tipo        VARCHAR(20) CHECK (tipo IN ('CELULAR','COMERCIAL','FIXO'))
);

ALTER TABLE usuario ADD COLUMN data_nascimento DATE;


