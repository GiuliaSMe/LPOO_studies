import java.util.Scanner;

public class GerenciadorUI{

    public String cadastroCliente(Scanner scanner){
        System.out.print("\nNome do Cliente: ");
        String cliente = scanner.nextLine();

        return cliente;
    }

    public Veiculo cadastroVeiculo(Scanner scanner){

        System.out.print("\nPlaca: ");
        String placa = scanner.nextLine();

        System.out.print("Modelo do Veiculo: ");
        String modelo = scanner.nextLine();

        System.out.print("ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        return new Veiculo(placa, modelo, ano);
    }

    public int menu(Scanner scanner){
        String textoMenu = """
        \n
        ================ OFICINA BOX 42 ================
        ===================== MENU =====================

            0. Cadastrar ordem de serviço
            1. Listar ordens de serviço 
            2. Buscar ordens pelo nome do cliente
            3. Buscar ordem pela placa do veículo 
            4. Sair

        Escolha uma opção: """;

        System.out.print(textoMenu);
        int escolhaOpcaoMenu = scanner.nextInt();
        scanner.nextLine();
        
        return escolhaOpcaoMenu;
    }

    public void escolhaMenu(int escolha, Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
        switch (escolha){
            case 0:
                OrdemServico novaOrdem = this.cadastroOrdemServico(scanner);
                gerenciadorOrdens.addOrdem(novaOrdem);
                break;
            case 1:
                System.out.print(gerenciadorOrdens.getOrdensServico());
                break;
            case 2:
                this.buscaPorCliente(scanner, gerenciadorOrdens);
                break;
            case 3:
                this.buscaPorPlaca(scanner, gerenciadorOrdens);
                break;
            case 4:
                System.out.println("Saindo... ");
                break;
            default:
                System.out.print("\nOpção inválida");
                break;
        }
    }

    public OrdemServico cadastroOrdemServico(Scanner scanner){
        String textoMaisServico = "\nAdicionar outro serviço? (s/n): ";
        String escolhaMais = " ";
        System.out.println("\n============= NOVA ORDEM DE SERVIÇO =============");
        String cliente = cadastroCliente(scanner);
        Veiculo veiculo = cadastroVeiculo(scanner);

        OrdemServico ordem = new OrdemServico(cliente, veiculo);

        while (!escolhaMais.equalsIgnoreCase("n")){
            Servico servico = cadastroServico(scanner); 
            ordem.addServico(servico);
            
            System.out.print(textoMaisServico);
            escolhaMais = scanner.nextLine();
        }

        return ordem;
    }

    public Servico cadastroServico(Scanner scanner){
        String textoOpcoesServicos = """
        \n
        ================ TIPOS DE SERVIÇO ================

            0. Serviço Simples;
            1. Serviço com Peça;
            2. Serviço com Horas;
            3. Serviço de Diagnóstico;

        Escolha uma opção: """;

        System.out.print(textoOpcoesServicos);
        int escolhaOpcaoTipoServico = scanner.nextInt();
        scanner.nextLine();//limpa buffer
        
        Servico novoServico = escolhaTipoServico(escolhaOpcaoTipoServico, scanner);

        return novoServico;
    }


    public Servico escolhaTipoServico(int escolha, Scanner scanner){
        switch (escolha){
            case 0:
                return cadastroServicoSimples(scanner);
            case 1:
                return cadastroServicoComPeca(scanner);
            case 2:
                return cadastroServicoComHora(scanner);
            case 3:
                return cadastroServicoDiagnostico(scanner);
            default:
                System.out.print("\nOpção inválida");
                return cadastroServico(scanner);
                
        }
    }

    public Servico cadastroServicoSimples(Scanner scanner){

        System.out.print("\nServiço Realizado: ");
        String servico = scanner.nextLine();

        System.out.print("Valor Cobrado: R$ ");
        double valorBase = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        return new Servico(servico, valorBase, descricao);
    }

    public Servico cadastroServicoComPeca(Scanner scanner){

        System.out.print("\nServiço Realizado: ");
        String servico = scanner.nextLine();

        System.out.print("Valor Base: R$ ");
        double valorBase = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Peça Utilizada: ");
        String peca = scanner.nextLine();

        System.out.print("Valor da Peça: R$");
        double valorPeca = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        return new ServicoComPecas(servico, valorBase, descricao, peca, valorPeca);
    }

    public Servico cadastroServicoComHora(Scanner scanner){

        System.out.print("\nServiço Realizado: ");
        String servico = scanner.nextLine();

        System.out.print("Valor Base: R$ ");
        double valorBase = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Valor da Hora: R$ ");
        Double valorHora = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Horas Trabalhadas: ");
        int horasTrabalhadas = scanner.nextInt();
        scanner.nextLine(); //limpar buffer

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        return new ServicoComHora(servico, valorBase, descricao, valorHora, horasTrabalhadas);
    }

    public Servico cadastroServicoDiagnostico(Scanner scanner){

        System.out.print("\nServiço Realizado: ");
        String servico = scanner.nextLine();

        System.out.print("Valor Base: R$");
        double valorBase = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Valor do Diagnóstico: R$ ");
        Double valorDiagnostico = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer

        System.out.print("Houve reparo? (s/n): ");
        String possuiReparo = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        return new ServicoDiagnostico(servico, valorBase, descricao, possuiReparo, valorDiagnostico);
    }

    public void buscaPorPlaca(Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
        System.out.print("\nPlaca: ");
        String placa = scanner.nextLine();

        String resultado = gerenciadorOrdens.findPorPlaca(placa);
        System.out.println(resultado);
    }

    public void buscaPorCliente(Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
        System.out.print("\nNome do Cliente: ");
        String cliente = scanner.nextLine();

        String resultado = gerenciadorOrdens.findPorCliente(cliente);
        System.out.println(resultado);
    }

    //*PATTERN
    public void escolha(int escolha){
        switch (escolha){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.print("Opção inválida");
                break;
                
        }
    }
}