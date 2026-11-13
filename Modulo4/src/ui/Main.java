package ui;
import java.util.Scanner;

import excecoes.*;
import gerenciadores.*;

/**
 * Classe principal do sistema da oficina.
 * Inicializa a interface, o gerenciamento de ordens e mantém o loop de execução.
 */
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GerenciadorUI ui = new GerenciadorUI();
        GerenciadorOrdens gerenciadorOrdens;

    try {
        gerenciadorOrdens = new GerenciadorOrdens(20);
    } catch (ValorInvalidoException e) {
        System.out.println("Erro: " + e.getMessage());
        scanner.close();
        return;
    }

    int opcao = -1;
    while (opcao != 7){ // 7 = sair

            opcao = ui.menu(scanner);
            ui.escolhaMenu(opcao, scanner, gerenciadorOrdens);
        }

        scanner.close();
    }
}
