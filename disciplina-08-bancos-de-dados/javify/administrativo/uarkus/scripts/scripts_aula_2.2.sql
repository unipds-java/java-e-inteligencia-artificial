INSERT INTO plano (nome, preco, possui_propagandas, limite_membros, modo_offline)
VALUES
    ('Free',    0.00, true,  1, false),
    ('Premium', 21.90, false, 1, true),
    ('Family',  34.90, false, 6, true),
    ('Student', 10.90, false, 1, true),
    ('Student Plus', 12.90, false, 2, true);

UPDATE plano
   SET preco = 24.90
 WHERE nome = 'Premium';

DELETE from plano 
  WHERE id = 5;


INSERT INTO endereco (codigo_postal, logradouro, bairro) VALUES
('01001000', 'Praça da Sé', 'Sé'),
('01311000', 'Av. Paulista', 'Bela Vista'),
('01402000', 'Rua Oscar Freire', 'Jardins'),
('04534000', 'Av. Brigadeiro Faria Lima', 'Itaim Bibi'),
('05424000', 'Rua Teodoro Sampaio', 'Pinheiros'),
('01239000', 'Av. Brigadeiro Luís Antônio', 'Bela Vista'),
('04014000', 'Rua da Consolação', 'Consolação'),
('05422000', 'Rua Pamplona', 'Bela Vista'),
('01415000', 'Rua Haddock Lobo', 'Jardins'),
('01508000', 'Rua da Glória', 'Bela Vista');


INSERT INTO usuario (id, nome, email, senha_hash, titular, cep, data_nascimento) VALUES
(1, 'João Silva', 'joao.silva@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '01001000', '1990-05-15'),
(2, 'Maria Santos', 'maria.santos@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '01311000', '1985-08-22'),  -- Titular Family
(3, 'Pedro Oliveira', 'pedro.oliveira@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '01402000', '1992-03-10'), -- Membro Family
(4, 'Ana Costa', 'ana.costa@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '04534000', '1988-11-30'),
(5, 'Carlos Ferreira', 'carlos.ferreira@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '05424000', '1995-07-08'), -- Membro Family
(6, 'Lucia Rodrigues', 'lucia.rodrigues@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '01239000', '1991-02-14'), -- Membro Family
(7, 'Ricardo Almeida', 'ricardo.almeida@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '04014000', '1987-09-25'),
(8, 'Fernanda Lima', 'fernanda.lima@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '05422000', '1993-12-03'),
(9, 'Marcos Pereira', 'marcos.pereira@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', false, '01415000', '1989-06-18'),
(10, 'Juliana Souza', 'juliana.souza@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MrqJqC3JqC3JqC3JqC3JqC3JqC3JqC3', true, '01508000', '1994-04-12');


INSERT INTO usuario_telefone (usuario_id, numero, tipo) VALUES
(1, '(11) 98765-4321', 'CELULAR'),
(1, '(11) 3456-7890', 'FIXO'),
(2, '(11) 91234-5678', 'CELULAR'),
(3, '(11) 92345-6789', 'CELULAR'),
(3, '(11) 4567-8901', 'COMERCIAL'),
(4, '(11) 93456-7890', 'CELULAR'),
(5, '(11) 94567-8901', 'CELULAR'),
(5, '(11) 5678-9012', 'FIXO'),
(6, '(11) 95678-9012', 'CELULAR'),
(7, '(11) 96789-0123', 'CELULAR'),
(8, '(11) 97890-1234', 'CELULAR'),
(8, '(11) 6789-0123', 'COMERCIAL'),
(9, '(11) 98901-2345', 'CELULAR'),
(10, '(11) 99012-3456', 'CELULAR');


INSERT INTO usuario_telefone (usuario_id, numero, tipo) VALUES
(9, '(11) 99999-5566', 'FIXO');

INSERT INTO usuario_telefone (usuario_id, numero, tipo) VALUES
(10, '(11) 99999-8888', 'FIXO');


INSERT INTO assinatura (plano_id, status_ativa) VALUES
(2, true),  
(3, true),  
(1, true),  
(2, true),  
(3, true),  
(4, true), 
(2, false), 
(3, true),  
(4, true),  
(2, true); 


UPDATE usuario SET assinatura_id = 1 WHERE id = 1;   
UPDATE usuario SET assinatura_id = 2 WHERE id = 2;   
UPDATE usuario SET assinatura_id = 2 WHERE id = 3;   
UPDATE usuario SET assinatura_id = 4 WHERE id = 4;  
UPDATE usuario SET assinatura_id = 2 WHERE id = 5;   
UPDATE usuario SET assinatura_id = 2 WHERE id = 6;   
UPDATE usuario SET assinatura_id = 7 WHERE id = 7;   
UPDATE usuario SET assinatura_id = 8 WHERE id = 8;   
UPDATE usuario SET assinatura_id = 9 WHERE id = 9;   
UPDATE usuario SET assinatura_id = 10 WHERE id = 10;  