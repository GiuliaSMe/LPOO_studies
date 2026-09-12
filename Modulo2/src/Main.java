import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Veiculo carro1 = new Veiculo("qwe-1234", "renegade", 2021);
        //Veiculo carro2 = new Veiculo("qwu-1234", "minicooper", 2011);
        Scanner scanner = new Scanner(System.in);
        GerenciadorUI ui = new GerenciadorUI();
        GerenciadorOrdens gerenciadorOrdens = new GerenciadorOrdens();

        Servico servico1 = new Servico(2,100,"Sobrou uma porca quando remontamos, espero que não faça falta");
        Servico servico2 = new Servico(4,180,"O oléo estava verde tal qual merda de hulk");
        
        OrdemServico ordemServico1 = new OrdemServico("Joao", carro1);
        ordemServico1.addServico(servico1);
        ordemServico1.addServico(servico2);
 
        
        /*
        ordemServico1.showResumoOrdemServico();
        */
        int opcao = -1; 
        while (opcao != 4){
            opcao = ui.menu(scanner);
            ui.escolhaMenu(opcao, scanner, gerenciadorOrdens);
            
        }

        scanner.close();
    }
}
