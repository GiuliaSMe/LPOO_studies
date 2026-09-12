import java.util.Scanner;

public class GerenciadorUI{

    public int menu(Scanner scanner){
        String textoMenu = """
        === OFICINA BOX 42 ===

            0. Cadastrar ordem de serviço
            1. Listar ordens de serviço 
            2. Buscar ordens pelo início do nome do cliente (wip)
            3. Buscar ordem pela placa do veículo (wip)
            4. Sair

        Escolha uma opção: """;

        System.out.print(textoMenu);
        int escolhaOpcaoMenu = scanner.nextInt();
        scanner.nextLine();
        
        return escolhaOpcaoMenu;
    }

    public OrdemServico cadastroOrdemServico(Scanner scanner){
        String textoMaisServico = "Adicionar outro serviço? (s/n): ";
        String escolhaMais = " ";
        System.out.println("=== NOVA ORDEM DE SERVIÇO ===");
        String cliente = cadastroCliente(scanner);
        Veiculo veiculo = cadastroVeiculo(scanner);

        OrdemServico ordem = new OrdemServico(cliente, veiculo);

        while (!escolhaMais.equalsIgnoreCase("n")){
            Servico servico = this.cadastroServico(scanner);
            ordem.addServico(servico);
            
            System.out.println(textoMaisServico);
            escolhaMais = scanner.nextLine();
        }

        return ordem;
    }

    public Servico cadastroServico(Scanner scanner){
        String textoOpcoesServicos = """

        === OPÇÕES DE SERVIÇO ===

            0. troca de óleo;
            1. alinhamento;
            2. balanceamento;
            3. troca de pastilhas de freio;
            4. revisão elétrica.

        Escolha uma opção: """;

        String textoValor = "Valor cobrado: ";
        String textoDescricao = "Descrição: ";
        
        System.out.print(textoOpcoesServicos);
        int escolhaServicos = scanner.nextInt();
        scanner.nextLine();

        System.out.print(textoValor);
        double valor = scanner.nextDouble();
        scanner.nextLine();

        System.out.print(textoDescricao);
        String descricao = scanner.nextLine();
            

        return new Servico(escolhaServicos, valor, descricao);
    }

    public String cadastroCliente(Scanner scanner){
        System.out.print("Nome do Cliente: ");
        String cliente = scanner.nextLine();

        return cliente;
    }

    public Veiculo cadastroVeiculo(Scanner scanner){

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Modelo do Veiculo: ");
        String modelo = scanner.nextLine();

        System.out.print("ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        return new Veiculo(placa, modelo, ano);
    }

    public void escolhaMenu(int escolha, Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
        switch (escolha){
            case 0:
                OrdemServico novaOrdem = this.cadastroOrdemServico(scanner);
                gerenciadorOrdens.addOrdem(novaOrdem);
                break;
            case 1:
                gerenciadorOrdens.getOrdensServico();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println("Saindo... ");
                break;
            default:
                System.out.print("Opção inválida");
                break;
        }
    }

    //!PATTERN
    public void escolha(int escolha){
        switch (escolha){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                System.out.print("Opção inválida");
                
        }
    }
}