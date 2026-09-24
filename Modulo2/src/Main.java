import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GerenciadorUI ui = new GerenciadorUI();
        GerenciadorOrdens gerenciadorOrdens = new GerenciadorOrdens();

        int opcao = -1; 
        while (opcao != 4){
            opcao = ui.menu(scanner);
            ui.escolhaMenu(opcao, scanner, gerenciadorOrdens);
        }

        scanner.close();
    }
}
