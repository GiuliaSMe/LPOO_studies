import java.util.Scanner;

/**
 * Responsável pela interação do usuário com o sistema da oficina.
 * Reúne os fluxos de cadastro, menu e consultas de ordens de serviço.
 */
public class GerenciadorUI{

    /**
     * Solicita e retorna o nome do cliente informado no terminal.
     *
     * @param scanner entrada de dados do usuário.
     * @return nome cadastrado do cliente.
     */
    public String cadastroCliente(Scanner scanner){
        System.out.print("\nNome do Cliente: ");
        String cliente = scanner.nextLine();

        return cliente;
    }

    /**
     * Coleta os dados do veículo e instancia um objeto Veiculo.
     *
     * @param scanner entrada de dados do usuário.
     * @return veículo preenchido com placa, modelo e ano.
     */
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

    /**
     * Exibe o menu principal da oficina e lê a opção escolhida.
     *
     * @param scanner entrada de dados do usuário.
     * @return número correspondente à ação selecionada.
     */
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

    /**
     * Executa a ação correspondente à opção escolhida no menu principal.
     *
     * @param escolha opção informada pelo usuário.
     * @param scanner entrada de dados do usuário.
     * @param gerenciadorOrdens estrutura responsavel por armazenar as ordens.
     */
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

    /**
     * Cria uma ordem de serviço completa a partir dos dados do cliente, veículo e serviços.
     *
     * @param scanner entrada de dados do usuário.
     * @return ordem finalizada com os serviços informados.
     */
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

    /**
     * Solicita o tipo de serviço que será registrado e chama o cadastro específico.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço instanciado de acordo com a escolha.
     */
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

    /**
     * Direciona a criação do serviço conforme a opção escolhida.
     *
     * @param escolha código do tipo de serviço.
     * @param scanner entrada de dados do usuário.
     * @return novo serviço cadastrado.
     */
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

    /**
     * Coleta dados de um serviço simples e cria a instância correspondente.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço com descrição e valor base.
     */
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

    /**
     * Coleta dados de um serviço que envolve peças e cria a instância correspondente.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço com valor de peça e descrição.
     */
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

    /**
     * Coleta dados de um serviço cobrado por hora de trabalho.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço com cálculo por tempo e valor por hora.
     */
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

    /**
     * Coleta dados de um serviço de diagnóstico e cria a instância correspondente.
     *
     * @param scanner entrada de dados do usuário.
     * @return serviço de diagnóstico com informação de reparo.
     */
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

    /**
     * Busca ordens pela placa informada pelo usuário.
     *
     * @param scanner entrada de dados do usuário.
     * @param gerenciadorOrdens referência ao armazenamento central.
     */
    public void buscaPorPlaca(Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
        System.out.print("\nPlaca: ");
        String placa = scanner.nextLine();

        String resultado = gerenciadorOrdens.findPorPlaca(placa);
        System.out.println(resultado);
    }

    /**
     * Busca ordens pelo nome do cliente informado pelo usuário.
     *
     * @param scanner entrada de dados do usuário.
     * @param gerenciadorOrdens referência ao armazenamento central.
     */
    public void buscaPorCliente(Scanner scanner, GerenciadorOrdens gerenciadorOrdens){
        System.out.print("\nNome do Cliente: ");
        String cliente = scanner.nextLine();

        String resultado = gerenciadorOrdens.findPorCliente(cliente);
        System.out.println(resultado);
    }

    /**
     * Estrutura auxiliar para seleção de opções genéricas do sistema.
     *
     * @param escolha opção selecionada.
     */
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