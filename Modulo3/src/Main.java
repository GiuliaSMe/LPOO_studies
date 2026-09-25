import java.util.Scanner;

/**
 * Classe principal do sistema da oficina.
 * Inicializa a interface, o gerenciamento de ordens e mantém o loop de execução.
 */
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GerenciadorUI ui = new GerenciadorUI();
        GerenciadorOrdens gerenciadorOrdens = new GerenciadorOrdens();
        
        int opcao = -1; 

        while (opcao != 4){ //4 é o valor para saída do sistema
            opcao = ui.menu(scanner);
            ui.escolhaMenu(opcao, scanner, gerenciadorOrdens);
        }

        scanner.close();
    }
}
