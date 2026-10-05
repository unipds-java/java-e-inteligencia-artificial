select id, nome, email, data_nascimento
from usuario
where email like '%gmail.com'
order by data_nascimento;


select min(preco) as menor_preco from plano;

select max(preco) as menor_preco from plano;

select avg(preco) as menor_preco from plano;

select count(*) as total_usuarios from usuario;


SELECT usuario_id,
       COUNT(id) AS total_telefones
  FROM usuario_telefone
 GROUP BY usuario_id;


SELECT usuario_id,
       COUNT(id) AS total_telefones
  FROM usuario_telefone
 GROUP BY usuario_id
 HAVING COUNT(id) > 1
 ORDER BY total_telefones DESC;  


SELECT p.nome       AS plano,
       a.id         AS id_assinatura
  FROM plano p
  JOIN assinatura a ON p.id = a.plano_id;


INSERT INTO plano (nome, preco, possui_propagandas, limite_membros, modo_offline)  VALUES ('Duo',    25.90, false,  2, false);


SELECT p.nome       AS plano,
       a.id         AS id_assinatura
  FROM plano p
  LEFT JOIN assinatura a ON p.id = a.plano_id;


select u.nome as usuario, p.nome as plano, p.preco 
from plano p
JOIN assinatura a on p.id = a.plano_id 
join usuario u on u.assinatura_id  = a.id 
where p.preco > (select avg(preco) from plano);