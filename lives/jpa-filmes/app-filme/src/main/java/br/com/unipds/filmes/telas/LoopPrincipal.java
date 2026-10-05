package br.com.unipds.filmes.telas;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class LoopPrincipal {

    private final Map<String, Tela> telas;

    public LoopPrincipal(Map<String, Tela> telas) {
        this.telas = telas;
    }

    public void loop() {
        int opcao;
        Scanner entrada = new Scanner(System.in);

        do {
            String nomeBeanTela = null;
            limparTela();
            
            System.out.println("**************");
            System.out.println("MENU PRINCIPAL");
            System.out.println("**************");
            System.out.println("1 - Buscar filme");
            System.out.println("2 - Incluir filme");
            System.out.println("3 - Listar filmes");
            System.out.println("4 - Buscar ator");
            System.out.println("5 - Incluir ator");
            System.out.println("6 - Listar atores");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine(); 

            switch (opcao) {
                case 1:
                    nomeBeanTela = "telaBuscarFilme";
                    break;
                case 2:
                    nomeBeanTela = "telaCadastrarFilme";
                    break;
                case 3:
                    nomeBeanTela = "telaListarFilmes";
                    break;
                case 4:
                    nomeBeanTela = "telaBuscarAtor";
                    break;
                case 5:
                    nomeBeanTela = "telaCadastrarAtor";
                    break;
                case 6:
                    nomeBeanTela = "telaListarAtores";
                    break;
                case 0:
                    System.out.println("Fim do programa!");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    voltarMenu(entrada);
                    break;
            }

            if(nomeBeanTela != null) {
                limparTela();
                Tela tela = telas.get(nomeBeanTela);
                tela.executar(entrada);
                voltarMenu(entrada);
            }
        } while(opcao != 0);

        entrada.close();
    }

    public static void limparTela() {
        try {
            // Limpa toda a tela do console
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
        } catch(Exception e) {}
    }

    private void voltarMenu(Scanner entrada) {
        System.out.print("\nPressione ENTER para continuar...");
        entrada.nextLine();

        limparTela();
        System.out.flush();
    }
}