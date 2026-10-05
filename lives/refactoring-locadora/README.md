# Refactoring: Exemplo da Locadora

Este repositório contém o código de exemplo clássico do livro [Refactoring: Improving the Design of Existing Code](https://www.amazon.com.br/Refactoring-Improving-Design-Existing-Code/dp/0134757599), de Martin Fowler (1ª edição, 1999). O projeto demonstra como um código inicialmente complexo e de difícil manutenção pode ser aprimorado através de uma série de refatorações sistemáticas catalogadas no livro.

## Estrutura do Repositório

O código-fonte está em Java e simula um sistema de locadora de filmes, focando na funcionalidade de geração de recibos para os clientes.

### O código inicial

A branch `master` contém a versão original do código, antes de qualquer refatoração. Este é o ponto de partida, um exemplo de um código que, embora funcional, possui "code smells" (maus cheiros) que dificultam sua evolução e entendimento. Mas possui uma boa cobertura de testes!

### O desafio

O desafio é aplicar as seguintes técnicas de refatoração sobre o código original passo a passo para melhorar o design do código, incluindo:

*   **extract-variable**: a extração de expressões complexas para variáveis locais, melhorando a legibilidade.
*   **extract-method**: extrair trechos de código para novos métodos, tornando o método original mais coeso e curto.
*   **rename-local-variable**: renomear variáveis locais para nomes mais claros e expressivos.
*   **remove-assignment-to-parameter**: remover a reatribuição de valores a parâmetros de métodos.
*   **change-method-signature**: alterar a assinatura de um método para torná-la mais clara e adequada.
*   **inline-temp**: inverter a "extract-variable", eliminando uma variável temporária desnecessária.
*   **move-method**: mover um método para uma classe mais apropriada.
*   **move-method-with-delegate**: mover um método, mas mantendo uma delegação na classe original.
*   **rename-method**: renomear um método para que seu nome revele melhor seu propósito.
*   **extract-interface**: extrair uma interface de uma classe para reduzir o acoplamento.
*   **split-variable**: dividir uma variável que está sendo usada para múltiplos propósitos.

A branch `refactoring` contém uma resposta a esse desafio!
